package Parcour;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import java.util.ArrayList;

import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;

public class TestRijden {

	public static void main(String[] args) {
		ColorSensor color = new ColorSensor(SensorPort.S3);
		String currentColor;
		
		System.out.println("Drive Forward on Black line\nand Stop\n");
		System.out.println("Press any key to start");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();

		// create two motor objects to control the motors.
		UnregulatedMotor motorC = new UnregulatedMotor(MotorPort.C);
		UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

		// colorsensor starten
		Delay.msDelay(1000);

		color.setColorIdMode();
		color.setFloodLight(false);
		
		// zwarte lijn herkennen?
		// color.fetchSample(, 0);
		
		
//		while(color =! black) {
//			
//			
//			zoeken naar color ID
//			
//			if(color = black) {
//			
//			rijden	
//				
//			} else 
//			}
//			
//			
//		}
//	
//		do {	
//			Lcd.clear(7);
//			Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
//			Delay.msDelay(250);
//			currentColor = ColorSensor.colorName(color.getColorID());
//		} while (!currentColor.equals("Black"));
//		
//		if (currentColor.equals("Black")) {
//		Lcd.print(8, "%s is gevonden", currentColor);
//		motorC.setPower(50);
//		motorB.setPower(50);
//		Sound.beepSequence();
//		
//		// wait 2 seconds.
//		Delay.msDelay(1000);
//		} else {		
//		// stop motors with brakes on.
//		motorC.stop();
//		motorB.stop();
//
//		// free up motor resources.
//		motorC.close();
//		motorB.close();

		Sound.beepSequence(); // we are done.
	}
}
