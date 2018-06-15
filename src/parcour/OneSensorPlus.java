package parcour;

import sensoren.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class OneSensorPlus implements Driveable {
	// Initialize variables
	int fullPower;
	int noPower;
	float kp;
	float ki;
	float kd;
	float midpoint;
	float midpoint2;
	float ambient = 0;
	float correction = 0;
	float whiteamount = 0;
	final int TURNING_POINT = 90;
	final float ADJUST = 0.75f;

	// declare left and right motor
	final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
	final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

	// declare sensor
	ColorSensor ambSensor = new ColorSensor(SensorPort.S4);

	public OneSensorPlus(int fullPower, int noPower, float kp, float ki, float kd) {
		super();
		this.fullPower = fullPower;
		this.noPower = noPower;
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}
	
	// calibration
	public void goCalibrate() {
		// This method calibrates the sensors and defines a calibrated midpoint

		// Initialize variables used only in this method
		float white;
		float black;

		// Turn on sensor
		// Red mode gives most distinct values
		ambSensor.setRedMode();

		// Get the white value for both sensors
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getRed();
		System.out.println("White value sensor: " + white);

		// get the black value for both sensors
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getRed();
		System.out.println("Black value sensor: " + black);

		// calculate midpoints
		midpoint = (white - black) / 2 + black;
		midpoint2 = midpoint * ADJUST;
	}
	
	// correction
	public float calculateCorrection(float lasterror) {
		float error = 0;
		float integral = 0;
		float derivative;

		integral *= 0.98;
		integral += error;
		derivative = error - lasterror;

		ambient = ambSensor.getRed();
		error = midpoint - ambient;

		correction = (kp * error) + (ki * integral) + (kd * derivative);
		correction = (correction * 100f);
		lasterror = error;

		return correction;
	}
	
	// go motor
	public void goMotor(int fullPower, int noPower, float correction, String modus) {
		// adjust motor power with correction
		int FullMotorSpeed = fullPower + (int) correction;
		int NoMotorSpeed = noPower - (int) correction;

		// create boundaries for motors
		if (FullMotorSpeed < 0)
			FullMotorSpeed = 0;
		if (NoMotorSpeed < 0)
			NoMotorSpeed = 0;

		// start motors
		if (modus == "right") {
			motorR.setPower(NoMotorSpeed);
			motorL.setPower(FullMotorSpeed);
		} else if (modus == "left") {
			motorL.setPower(NoMotorSpeed);
			motorR.setPower(FullMotorSpeed);
		}
	}
	
	// method in order to count white space 
	public void goSkipWhite() {

		if (ambient > midpoint) {
			whiteamount++;
		} else if (ambient < (midpoint2)) {
			whiteamount = 0;
		}

		while (whiteamount > TURNING_POINT) {
			motorR.setPower(50);
			motorL.setPower(50);
			Delay.msDelay(200);
			whiteamount = 0;
		}
	}
	
	public void goDrive(String modus) {
		float lasterror = 0;

		goCalibrate();

		while (Button.ESCAPE.isUp()) {
			float correction = calculateCorrection(lasterror);
			goSkipWhite();
			goMotor(fullPower, noPower, correction, modus);
		}
	}

	// these methods are used for a broken menu

	public void goDriveRight() {
		// this method is only correct for a right turning track
		// on the outside line

		// Initialize variables used only in this variable
		float ambient;
		float correction;
		float error = 0;
		float lasterror = 0;
		float integral = 0;
		float derivative;
		int whiteamount = 0;
		int turningpoint = 80;

		// Turn on sensors
		// ambSensor.setRedMode();

		// Print out message and wait for input
		System.out.println("PRESS TO START");
		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {

			ambient = ambSensor.getRed();
			error = midpoint - ambient;
			float midpoint2 = midpoint * 0.75f;

			integral *= 0.98;
			integral += error;
			derivative = error - lasterror;

			// calculate correction using the left sensor
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			correction = (correction * 100f);
			lasterror = error;

			if (ambient > midpoint) {
				whiteamount++;
			} else if (ambient < (midpoint2)) {
				whiteamount = 0;
			}

			// adjust motor speed
			int FullMotorSpeed = fullPower + (int) correction;
			int NoMotorSpeed = noPower - (int) correction;

			// adjust motor speed

			System.out.println(whiteamount);

			while (whiteamount > turningpoint) {
				motorR.setPower(50);
				motorL.setPower(50);
				Delay.msDelay(200);
				whiteamount = 0;
			}

			// set motor speed bracket prevents robot going backwards
			if (FullMotorSpeed < 0)
				FullMotorSpeed = 0;
			if (NoMotorSpeed < 0)
				NoMotorSpeed = 0;

			motorR.setPower(NoMotorSpeed);
			motorL.setPower(FullMotorSpeed);
		}
	}

	public void goDriveLeft() {
		// this method is only correct for a left turning track
		// on the outside line

		// Initialize variables used only in this variable
		float ambient;
		float correction;
		float error = 0;
		float lasterror = 0;
		float integral = 0;
		float derivative;
		int whiteamount = 0;
		int turningpoint = 80;

		// Turn on sensors
		// ambSensor.setRedMode();

		// Print out message and wait for input
		System.out.println("PRESS TO START");
		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {

			ambient = ambSensor.getRed();
			error = midpoint - ambient;
			float midpoint2 = midpoint * 0.75f;

			integral *= 0.98;
			integral += error;
			derivative = error - lasterror;

			// calculate correction using the left sensor
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			correction = (correction * 100f);
			lasterror = error;

			if (ambient > midpoint) {
				whiteamount++;
			} else if (ambient < (midpoint2)) {
				whiteamount = 0;
			}

			// adjust motor speed
			int FullMotorSpeed = fullPower + (int) correction;
			int NoMotorSpeed = noPower - (int) correction;

			// adjust motor speed

			System.out.println(whiteamount);

			while (whiteamount > turningpoint) {
				motorR.setPower(50);
				motorL.setPower(50);
				Delay.msDelay(200);
				whiteamount = 0;
			}

			// set motor speed bracket prevents robot going backwards
			if (FullMotorSpeed < 0)
				FullMotorSpeed = 0;
			if (NoMotorSpeed < 0)
				NoMotorSpeed = 0;

			motorL.setPower(NoMotorSpeed);
			motorR.setPower(FullMotorSpeed);
		}
	}
}
