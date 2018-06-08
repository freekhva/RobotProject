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
		final int POWER = 50;
		final int NO_POWER = 50;	
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
		float test2;
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
		int offset;
		
	
		// Calibrate
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getRed();
		System.out.println("White value: " +white);

		
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getRed();
		System.out.println("Black value: " +black);
		
		float midpoint;
		midpoint = ( white - black ) / 2 + black;
		kp = (float) 2; 
		ki = (float) 0.5; 
		kd = (float) 1;
		offset = 45;
		kp = (float) 0.5; 
		ki = (float) 0.2; 
		kd = (float) 0;
		lasterror = 0;
		integral = 0;
		derivative = 0;
		
		System.out.println("Go do it! START: ");
		// Button.waitForAnyPress();
		
		while( Button.ESCAPE.isUp() ) // stop == false )
		{ 	
			ambient = ambSensor.getRed();
//			System.out.println("This is current ambient:" +ambient);
//			System.out.println("This is current ambient:" +ambient);
//			System.out.println("This is current ambient:" +ambient);
			error = ambient - midpoint;
	//		System.out.println("This is current ambient:" +ambient);
	//		System.out.println("This is current ambient:" +ambient);
	//		System.out.println("This is current ambient:" +ambient);
			error = midpoint - ambient;
			integral = integral + error;
			derivative = error - lasterror;
			
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			
//			System.out.println("This is current correction:" +correction);
//			System.out.println("This is current correction:" +correction);
//			System.out.println("This is current correction:" +correction);
	//		System.out.println("This is current correction:" +correction);
	//		System.out.println("This is current correction:" +correction);
	//		System.out.println("This is current correction:" +correction);
//			midpointLeft = (white - black ) / 4 ;
//			midpointRight = ((white - black ) / 4)*3; 
			
//			if (ambient > midpointLeft && ambient < midpointRight ) {
//				motorL.setPower( FULL_POWER );
//				motorR.setPower( FULL_POWER );
//			}
			correction = (correction * 100);
			
			double fpc = (POWER + correction);
			double npc = (POWER - correction);
			
			if (npc < 0 ) {
				npc = 0;
			}
			
			if (ambient < (midpoint - 0.3)) {
				motorL.setPower( (int) fpc);
				motorR.setPower( (int) npc);
			} else if (ambient > (midpoint + 0.3)) {
				motorL.setPower( (int) npc);
				motorR.setPower( (int) fpc);
			} else if (ambient < (midpoint + 0.3) && ambient > (midpoint - 0.3))
			motorL.setPower( (int) fpc);
			motorR.setPower( (int) npc);
			
//			if ( ambient < midpoint) 
//			{	
//				motorL.setPower( (int) fpc);
//				motorR.setPower( (int) npc);
//			}
//			else if( ambient > midpoint)
//			{
//				motorL.setPower( (int) npc);
//				motorR.setPower( (int) fpc);
//			}
//			else 
//			{
//				motorL.setPower( FULL_POWER );
//				motorR.setPower( FULL_POWER );
//			}
			
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
