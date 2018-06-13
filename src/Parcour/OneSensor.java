package Parcour;

import sensoren.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class OneSensor implements Driveable {
	// Initialize variables
	int fullPower;
	int noPower;
	float kp;
	float ki;
	float kd;
	float midpoint;
	float midpoint2;

	// declare left and right motor
	final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
	final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

	// declare left and right sensor
	// ambSensor is left sensor, ambSensorCor is right sensor
	ColorSensor ambSensor = new ColorSensor(SensorPort.S4);

	public OneSensor(int fullPower, int noPower, float kp, float ki, float kd) {
		super();
		this.fullPower = fullPower;
		this.noPower = noPower;
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}

	public float getKp() {
		return kp;
	}

	public float getKi() {
		return ki;
	}

	public float getKd() {
		return kd;
	}

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
	}

	public void goDrive() {
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
