package Parcour;

import sensoren.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class TwoSensor implements Driveable {
	
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
	ColorSensor ambSensor = new ColorSensor(SensorPort.S3);
	ColorSensor ambSensorCor = new ColorSensor(SensorPort.S1);


	public TwoSensor(int fullPower, int noPower, float kp, float ki, float kd) {
		super();
		this.fullPower = fullPower;
		this.noPower = noPower;
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}

	public void goCalibrate() {
		// This method calibrates the sensors and defines a calibrated midpoint

		// Initialize variables used only in this method
		float white;
		float white2;
		float black;
		float black2;

		// Turn on sensor
		// Red mode gives most distinct values
		ambSensor.setRedMode();
		ambSensorCor.setRedMode();

		// Get the white value for both sensors
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getRed();
		white2 = ambSensorCor.getRed();
		System.out.println("White value left sensor: " + white);
		System.out.println("White value right sensor: " + white2);

		// get the black value for both sensors
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getRed();
		black2 = ambSensorCor.getRed();
		System.out.println("Black value left sensor: " + black);
		System.out.println("Black value right sensor: " + black2);

		// calculate midpoints
		midpoint = (white - black) / 2 + black;
		midpoint2 = (white2 - black2) / 2 + black2;
	}

	public void goDrive() {
		// Initialize variables used only in this variable
		float ambient;
		float ambient2;
		float correction;
		float correction2;
		float error = 0;
		float error2 = 0;
		float lasterror = 0;
		float lasterror2 = 0;
		float integral = 0;
		float integral2 = 0;
		float derivative;
		float derivative2;

		// Turn on sensors
		ambSensor.setRedMode();
		ambSensorCor.setRedMode();

		// Print out message and wait for input
		System.out.println("Go do it! PRESS START: ");
		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {
		
			ambient = ambSensor.getRed();
			ambient2 = ambSensorCor.getRed();
			error = ambient - midpoint;
			error2 = ambient2 - midpoint2;
			
//			error = midpoint - ambient;
			integral = integral + error;
			integral2 = integral2 + error;
			derivative = error - lasterror;
			derivative2 = error2 - lasterror2;
			
			// calculate correction using the left sensor
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			correction = (correction * 100);
			
			// calculate correction using right sensor
			correction2 = (kp * error2) + (ki * integral2) + (kd * derivative2);
			correction2 = (correction2 * 100);
			lasterror = error;
			lasterror2 = error2;
			
			// adjust motor speed
			int FullMotorSpeed = fullPower + (int) correction;
			int NoMotorSpeed = noPower - (int) correction;
			int FullMotorSpeed2 = fullPower + (int) correction2;
			int NoMotorSpeed2 = noPower - (int) correction2;
			
			// set motor speed bracket prevents robot going backwards
			//for left sensor
			if (FullMotorSpeed < 0) FullMotorSpeed = 0;
			if (NoMotorSpeed < 0) NoMotorSpeed = 0;
			//for right sensor
			if (FullMotorSpeed2 < 0) FullMotorSpeed2 = 0;
			if (NoMotorSpeed2 < 0) NoMotorSpeed2 = 0;
			
			// this if statement is only correct for a right turning track
			// if (ambient > midpoint); read white
			// if (ambient < midpoint); read black
			// read: left sensor white, right sensor white 
			// make robot go forward
			if (ambient > midpoint && ambient2 > midpoint2) {
				motorL.setPower(75);
				motorR.setPower(75);
			// read: left sensor black, right sensor white 
			} else if (ambient < midpoint && ambient2 > midpoint2) {
				motorR.setPower(FullMotorSpeed);
				motorL.setPower(NoMotorSpeed);
			// read: left sensor white, right sensor black
			} else if (ambient > midpoint && ambient2 < midpoint2) {
				motorL.setPower(FullMotorSpeed2);
				motorR.setPower(NoMotorSpeed2);
			// read: left sensor black, right sensor black
			// for a very sharp/90 degrees turn
			} else if (ambient < midpoint && ambient2 < midpoint2) {
				motorL.setPower(FullMotorSpeed);
				motorR.setPower(NoMotorSpeed);
			}
		}
	}

	@Override
	public void goDrive(String modus) {
		// TODO Auto-generated method stub
	}

	@Override
	public void goDriveRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goDriveLeft() {
		// TODO Auto-generated method stub
		
	}
}
