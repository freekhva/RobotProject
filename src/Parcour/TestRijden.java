package Parcour;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.*;
import lejos.hardware.port.*;
import lejos.utility.Delay;
import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;

public class TestRijden {

	public static void main(String[] args) {
		ColorSensor color = new ColorSensor(SensorPort.S3);
		String currentColor;
		Boolean start = true;

		System.out.println("Drive Forward if black\nand Stop\n");
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

		Delay.msDelay(250);
		currentColor = ColorSensor.colorName(color.getColorID());
		while (start == true) {
			Lcd.clear(7);
			Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
			Delay.msDelay(250);
			currentColor = ColorSensor.colorName(color.getColorID());
			if (currentColor.equals("Black")) {
				Lcd.print(8, "%s is gevonden, lets go!", currentColor);
				motorC.setPower(20);
				motorB.setPower(20);
//				currentColor = ColorSensor.colorName(color.getColorID());
			} else {
				Delay.msDelay(250);
				motorC.stop();
				motorB.stop();
//				Lcd.clear(7);
//				Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
//				Delay.msDelay(250);
//				currentColor = ColorSensor.colorName(color.getColorID());
				start = false;
			}
		}
		
		
/*	
		do {
			Lcd.clear(7);
			Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
			Delay.msDelay(250);
			currentColor = ColorSensor.colorName(color.getColorID());
		} while (!currentColor.equals("Black"));
		Lcd.print(8, "%s is gevonden", currentColor);
		motorC.setPower(50);
		motorB.setPower(50);
		Sound.beepSequence();
		// wait 2 seconds.
		// Delay.msDelay(2000);
		// stop motors with brakes on.
		if (currentColor != "Black") {
			motorC.stop();
			motorB.stop();
		} else {
			motorC.forward();
			motorB.forward();
		}
		Sound.beepSequence();
		// we are done.
		/*
		 * motorC.stop(); motorB.stop(); motorC.close(); motorB.close();
		 */}
}
