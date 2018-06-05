package Parcour;

import basisoefeningen.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class MitchParcourRijden {

	public static void main(String[] args) 
	{
		// Constants
		final int FULL_POWER = 60;
		final int NO_POWER = 10;		
		
		// Sensors
		ColorSensor ambSensor = new ColorSensor(SensorPort.S3);
		ambSensor.setAmbientMode();
		
		// Init vars
		float ambient;
		float white;
		float black;
		
		// create two motor objects to control the motors.
        UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
        UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);
		
		// Calibrate
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getAmbient();
		
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getAmbient();
		
		float midpoint;
		
		System.out.println("Go do it! START: ");
		// Button.waitForAnyPress();
		
		while( Button.ESCAPE.isUp() ) // stop == false )
		{
			ambient = ambSensor.getAmbient();
			midpoint = ( white - black ) / 2 + black;

			if ( ambient < midpoint ) 
			{
				motorL.setPower( FULL_POWER );
				motorR.setPower( NO_POWER );
			}
			else if( ambient > midpoint )
			{
				motorL.setPower( NO_POWER );
				motorR.setPower( FULL_POWER );
			}
			else
			{
				motorL.setPower( FULL_POWER );
				motorR.setPower( FULL_POWER );
			}
		}

//			function CALIBRATE
//			
//				print "WHITE?"
//				Wait for Touch Sensor to change
//				white = Read Light Sensor
//				
//				print "BLACK?"
//				Wait for Touch Sensor to change
//				black = Read Light Sensor
//				
//			done function
		
//		program LINE FOLLOWING
//
//		white = 0, black = 0
//		CALIBRATE()
//		
//		midpoint = ( white - black ) / 2 + black
//
//		repeat
//		
//			value = Read Light Sensor
//			
//			if value < midpoint then
//				motor B set power 50
//				motor C set power 25
//			else
//				motor B set power 25
//				motor C set power 50
//			done if
//			
//		done repeat
//		
//	done program	

	}

}
