package parcour;

import sensoren.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class OneSensor implements Driveable {
	// Initialize variables
	private int fullPower;
	private int noPower;
	private float kp;
	private float ki;
	private float kd;
	private float midpoint;
	private float midpoint2;

	// declare left and right motor
	UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
	UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

	// declare sensor
	ColorSensor ambSensor = new ColorSensor(SensorPort.S4);

	public OneSensor(int fullPower, int noPower, float kp, float ki, float kd) {
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

		// Get the white value sensors
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getRed();
		System.out.println("White value sensor: " + white);

		// get the black value for sensor
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getRed();
		System.out.println("Black value sensor: " + black);

		// calculate midpoint by calculating average
		midpoint = (white - black) / 2 + black;
	}

	//calibration
	
	public float calculateCorrection(float lasterror) {
		final float INTEGRAL_CORRECTION = 0.98f;
		float ambient;
		float correction;
		float error = 0;
		float integral = 0;
		float derivative;

		integral *= INTEGRAL_CORRECTION;
		integral += error;
		derivative = error - lasterror;

		ambient = ambSensor.getRed();
		error = midpoint - ambient;

		correction = (kp * error) + (ki * integral) + (kd * derivative);
		correction = (correction * 100f);
		lasterror = error;

		return correction;
	}

	// turn on motor 
	
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

	// driving motor using correction and modus
	
	public void goDrive(String modus) {
		float lasterror = 0;

		goCalibrate();

		while (Button.ESCAPE.isUp()) {
			float correction = calculateCorrection(lasterror);
			
			goMotor(fullPower, noPower, correction, modus);
			
		}
	}

	// these methods below are used for a broken menu

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

		// Turn on sensors
		// ambSensor.setRedMode();

		// Print out message and wait for input
		System.out.println("PRESS TO START");
		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {

			ambient = ambSensor.getRed();
			error = midpoint - ambient;

			integral *= 0.98;
			integral += error;
			derivative = error - lasterror;

			// calculate correction using the left sensor
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			correction = (correction * 100f);
			lasterror = error;

			// adjust motor speed
			int FullMotorSpeed = fullPower + (int) correction;
			int NoMotorSpeed = noPower - (int) correction;

			// set motor speed bracket prevents robot going backwards
			if (FullMotorSpeed < 0)
				FullMotorSpeed = 0;
			if (NoMotorSpeed < 0)
				NoMotorSpeed = 0;

			motorL.setPower(NoMotorSpeed);
			motorR.setPower(FullMotorSpeed);
		}
	}

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

		// Turn on sensors
		// ambSensor.setRedMode();

		// Print out message and wait for input
		System.out.println("PRESS TO START");
		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {

			ambient = ambSensor.getRed();
			error = midpoint - ambient;

			integral *= 0.98;
			integral += error;
			derivative = error - lasterror;

			// calculate correction using the left sensor
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			correction = (correction * 100f);
			lasterror = error;

			// adjust motor speed
			int FullMotorSpeed = fullPower + (int) correction;
			int NoMotorSpeed = noPower - (int) correction;

			// set motor speed bracket prevents robot going backwards
			if (FullMotorSpeed < 0)
				FullMotorSpeed = 0;
			if (NoMotorSpeed < 0)
				NoMotorSpeed = 0;

			motorR.setPower(NoMotorSpeed);
			motorL.setPower(FullMotorSpeed);
		}
	}
}
