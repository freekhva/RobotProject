package kleur_en_geluid;

import java.util.ArrayList;
import java.util.List;

import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.TextLCD;;

public class Kleur_En_Geluid_Launcher {

	public static void main(String[] args) {
		// Aanmaken sensor en het opstarten riedeltje

				String kleur;
				int arraylengte = 3;

				ColorSensor color = new ColorSensor(SensorPort.S3);

				System.out.println("Kleur van geluid");
				Lcd.print(2, "Druk op een toets");

				Button.LEDPattern(4); // flash green led and
				Sound.beepSequenceUp(); // make sound when ready.

				Button.waitForAnyPress();
				Button.LEDPattern(0);

				// Starten van de sensor

				Delay.msDelay(1000);

				color.setColorIdMode();
				color.setFloodLight(false);

				ArrayList<String> test2 = new ArrayList<String>();;
				// kleuren opslaan in ArrayList
				
				do {
					Lcd.clear(3);
					Lcd.print(3, "Kies een kleur en druk een toets.");
					Button.waitForAnyPress();
					Lcd.clear(4);
					kleur = ColorSensor.colorName(color.getColorID());
					Lcd.print(4, "Kleur=%s", kleur);
					test2.add(kleur);
					Lcd.clear(5);
					Lcd.print(5, "Druk op escape om te stoppen");
				}while (Button.ESCAPE.isUp());
				
				Lcd.clear(5);
				Lcd.print(5, "druk voor afspelen.");
				Button.waitForAnyPress();
				
				for(int i = 0; i<test2.size(); i++) {
					Geluid(test2.get(i));
					Lcd.clear(3);
					Lcd.print(3, " %d isKleur=%s", i, test2.get(i));
					Delay.msDelay(1000);
				}
					
					
				// free up resources.
				color.close();

				Sound.beepSequence(); // we are done.

				Button.LEDPattern(4);
				Button.waitForAnyPress();

			}
				
	public static void Geluid(String kleur){
		switch (kleur) {
		case "Red": Sound.beep();
			break;
		case "Blue": Sound.beepSequenceUp();
		break;	
		case "Brown": Sound.buzz();
		break;
		default: Sound.beepSequence();
		break;
		}
	}
		}
