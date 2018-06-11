package Parcour;

import basisoefeningen.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class MitchParcourRijden {

	public static void main(String[] args) 
	{
		// Constants
		final int FULL_POWER = 50;
		final int NO_POWER = 50;	
		// linker en rechter motor definieren
		final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
		final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);
		
		// Sensors
		ColorSensor ambSensor = new ColorSensor(SensorPort.S3);
		ColorSensor ambSensorCor = new ColorSensor(SensorPort.S1);

		// ambSensor.setAmbientMode();
		
		ambSensor.setRedMode();
		ambSensorCor.setRedMode();

		// Init vars
		float ambient;
		float ambient2;
		float white;
		float white2;
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
		float midpoint;
		float midpoint2;
		
	
		// Calibrate
		System.out.println("White?");
		Button.waitForAnyPress();
		white = ambSensor.getRed();
		white2 = ambSensorCor.getRed();
		System.out.println("White value: " +white);

		
		System.out.println("Black?");
		Button.waitForAnyPress();
		black = ambSensor.getRed();
		System.out.println("Black value: " +black);
		
		midpoint = ( white - black ) / 2 + black;
		midpoint2 = ( white - black ) / 2 + black;
		kp = (float) 3; 
		ki = (float) 0; 
		kd = (float) 0;
		lasterror = 0;
		integral = 0;
		derivative = 0;
		
		System.out.println("Go do it! START: ");
		Button.waitForAnyPress();
		
		while( Button.ESCAPE.isUp() ) // stop == false )
		{ 	
			ambient = ambSensor.getRed();
			ambient2 = ambSensorCor.getRed();
			error = ambient - midpoint;
			error = midpoint - ambient;
			integral = integral + error;
			derivative = error - lasterror;
			correction = (kp * error) + (ki * integral) + (kd * derivative);
			correction = (correction * 100);
			
			if (ambient > midpoint && ambient2 > midpoint2) {
				motorL.setPower(75);
		        motorR.setPower(75);	
			}
			else if ( ambient < midpoint && ambient2 > midpoint) {
				motorR.setPower( (FULL_POWER + (int) correction));
				motorL.setPower( (NO_POWER - (int) correction));
			} else if (ambient > midpoint && ambient2 < midpoint) {
				motorL.setPower( (FULL_POWER + (int) correction));
				motorR.setPower( (NO_POWER - (int) correction));
			} else if (ambient < midpoint && ambient2 < midpoint) {
				motorL.setPower( (FULL_POWER + (int) correction));
				motorR.setPower( (FULL_POWER - (int) correction));
			}

//			if ( ambient >= (white) && ambient2 >= whiteArea) {
////		        Delay.msDelay(250);
//				motorL.setPower(30);
//		        motorR.setPower(30);
//		        Delay.msDelay(1000);
//		        if (ambient2 >= (black * 0.7))
//		        {
//		            motorL.setPower(20);
//		            motorR.setPower(10);
////		            Delay.msDelay(300);
//		        }
//		    }
//			else if ( ambient < midpoint ) 
//			{
//				motorL.setPower( (FULL_POWER + (int) correction));
//				motorR.setPower( (NO_POWER - (int) correction));
//			}
//			else if ( ambient > midpoint )
//			{
//				motorL.setPower( (NO_POWER - (-1 * (int) correction)));
//				motorR.setPower( (FULL_POWER + (-1 *(int) correction)));
//			}
//			else 
//			{
//				motorL.setPower( FULL_POWER );
//				motorR.setPower( FULL_POWER );
//			}
//			lasterror = error;
//		}
		}
	}
}
