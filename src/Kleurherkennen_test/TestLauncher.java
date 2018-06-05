package Kleurherkennen_test;

import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class TestLauncher {

	public static void main(String[] args) {

		// Aanmaken sensor en het opstarten riedeltje

		String test;

		ColorSensor color = new ColorSensor(SensorPort.S3);

		System.out.println("Color Demo");
		Lcd.print(2, "Press to start");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();
		Button.LEDPattern(0);

		// Starten van de sensor

		Delay.msDelay(1000);

		color.setColorIdMode();
		color.setFloodLight(false);

		// voorwaarde
		do {
			Lcd.clear(7);
			Lcd.print(7, "id=%s", ColorSensor.colorName(color.getColorID()));
			Delay.msDelay(250);
			test = ColorSensor.colorName(color.getColorID());
		} while (!test.equals("Red"));
		Lcd.print(8, "%s is gevonden", test);
		Sound.beepSequence();

		// free up resources.
		color.close();

		Sound.beepSequence(); // we are done.

		Button.LEDPattern(4);
		Button.waitForAnyPress();

	}

}
