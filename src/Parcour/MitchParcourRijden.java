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
		final int FULL_POWER = 70;
		final int NO_POWER = -10;	
		// linker en rechter motor definieren
		final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
		final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);
		
		// Sensors
		ColorSensor ambSensor = new ColorSensor(SensorPort.S3);
		// ambSensor.setAmbientMode();
		
		ambSensor.setRedMode();
		
		// Init vars
		float ambient;
		float white;
		float black;
		float test;
//		float midpointLeft;
//		float midpointRight;
		float correction;
		float kp;
		float ki;
		float kd;
		float error;
		float lasterror;
		float integral;
		float derivative;
		
	
		// Calibrate
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getAmbient();
		System.out.println("White value: " +white);

		
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getAmbient();
		System.out.println("Black value: " +black);
		
		float midpoint;
		midpoint = ( white - black ) / 2 + black;
		kp = (float) 0.500; 
		ki = (float) 0.250; 
		kd = (float) 0.00;
		lasterror = 0;
		integral = 0;
		
		
		System.out.println("Go do it! START: ");
		// Button.waitForAnyPress();
		
		while( Button.ESCAPE.isUp() ) // stop == false )
		{ 	
			ambient = ambSensor.getAmbient();
			error = midpoint - ambient;
			integral = error + integral;
			derivative = error - lasterror;
			
			correction = (kp * error) + (ki * integral) + (kd * derivative); 
			
//			midpointLeft = (white - black ) / 4 ;
//			midpointRight = ((white - black ) / 4)*3; 
			
//			if (ambient > midpointLeft && ambient < midpointRight ) {
//				motorL.setPower( FULL_POWER );
//				motorR.setPower( FULL_POWER );
//			}
		
			if ( ambient < midpoint ) 
			{
				motorL.setPower( FULL_POWER + (int) correction );
				motorR.setPower( NO_POWER - (int) correction);
			}
			else if( ambient > midpoint )
			{
				motorL.setPower( NO_POWER + (int) correction);
				motorR.setPower( FULL_POWER - (int) correction);
			}
			else 
			{
				motorL.setPower( FULL_POWER );
				motorR.setPower( FULL_POWER );
			}
			lasterror = error;
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
