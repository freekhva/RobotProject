package mystic_T; // hier zit een rare error

import sensoren.*;
import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import mystic_T.*; // freek toegevoegd, omdat anders Kaart niet meer herkend werd

public class Mystic_T_Launcher {

	public static void main(String[] args) {

		ColorSensor color = new ColorSensor(SensorPort.S1);
		// aanmaken variabelen en arraylists
		int rood;
		int blauw;
		int groen;
		final int INPUT = 3;

		ArrayList<Kaart> kaarten = new ArrayList<>();
		ArrayList<Kaart> tarotkaarten = new ArrayList<Kaart>();
		tarotkaarten.add(new Kaart(2,"Klaverbladen",61,70,59,0,1));
		tarotkaarten.add(new Kaart(3,"Schip",66,78,57,1,2));
		tarotkaarten.add(new Kaart(4,"Huis",63,62,40,0,2));
		tarotkaarten.add(new Kaart(6,"Wolken",49,50,27,0,1));
		tarotkaarten.add(new Kaart(7,"Slang",74,84,62,2,1));
		tarotkaarten.add(new Kaart(10,"Zeisen",53,58,39,0,1));
		tarotkaarten.add(new Kaart(12,"Vogels",46,56,24,0,0));
		tarotkaarten.add(new Kaart(11,"Roede",60,62,50,0,1));
		tarotkaarten.add(new Kaart(14,"Vosje",51,57,23,2,1));
		tarotkaarten.add(new Kaart(15,"Beer",33,41,20,0,2));
		tarotkaarten.add(new Kaart(16,"Ster",48,81,62,0,2));
		tarotkaarten.add(new Kaart(17,"Ooievaar",39,56,25,0,0));
		tarotkaarten.add(new Kaart(18,"Hond",55,61,37,2,2));
		tarotkaarten.add(new Kaart(19,"Toren",52,49,35,0,1));
		tarotkaarten.add(new Kaart(21,"Berg",45,46,21,0,1));
		tarotkaarten.add(new Kaart(23,"Muizen",41,46,23,1,2));
		tarotkaarten.add(new Kaart(25,"Ring",49,43,45,2,0));
		tarotkaarten.add(new Kaart(27, "Brief",82,83,55,0,2));
		tarotkaarten.add(new Kaart(28,"Heer",48,40,17,0,0));
		tarotkaarten.add(new Kaart(30, "Lelie",70,78,58,3,2));
		tarotkaarten.add(new Kaart(31,"Zonne",77,70,32,0,2));
		tarotkaarten.add(new Kaart(34,"Vissen",63,73,49,0,2));
		tarotkaarten.add(new Kaart(36,"Kruis",74,95,727,0,1));

		System.out.println("Mystic T");
		Lcd.print(2, "Druk een knop");

		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();
		Button.LEDPattern(0);

		// Instellen juiste sensor

		color.setRGBMode();
		color.setFloodLight(Color.WHITE);

		Color rgb;

		
		Lcd.clear(3);
		Lcd.print(3, "Scan een kaart");
		Button.waitForAnyPress();
		
		
		do {
			rgb = color.getColor();

			Lcd.clear(5);
			Lcd.clear(6);
			Lcd.print(4, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
			rood = rgb.getRed();
			groen = rgb.getGreen();
			blauw = rgb.getBlue();

			// checken of de kaart in de tarotarray voorkomt
			for (Kaart kaart : tarotkaarten) {
				if (kaart.testKleur(rood, kaart.getRood()) && kaart.testKleur(blauw, kaart.getBlauw())
						&& kaart.testKleur(groen, kaart.getGroen())) {
					if (kaarten.size() > 0) {
						if (checkDubbel(kaart, kaarten)) {
							Lcd.print(5, "%s is dubbel", kaart.getNaamKaart());
							Lcd.print(6, "Scan volgende kaart");
							Button.waitForAnyPress();
						} else {
							Lcd.clear(5);
							Lcd.print(5, "Dit is de %s", kaart.getNaamKaart());
							Sound.beep();
							kaarten.add(kaart);
							if (kaarten.size() < INPUT) {
								Lcd.print(6, "Scan volgende kaart");
								Button.waitForAnyPress();
							}
						}
					} else {
						Lcd.clear(5);
						Lcd.print(5, "Dit is de %s", kaart.getNaamKaart());
						Sound.beep();
						kaarten.add(kaart);
						Lcd.print(6, "Scan volgende kaart");
						Button.waitForAnyPress();
					}
					// Dit is niet meer nodig, maar laat maar even staan.
					// } else if (kaart.testKleur(groen, kaart.getGroen()) && kaart.testKleur(rood,
					// kaart.getRood())) {
					// Lcd.clear(5);
					// Lcd.print(5, "Dit is de %s", kaart.getNaamKaart());
					// Sound.beep();
					// kaarten.add(kaart));
					// if (kaarten.size() < INPUT) {
					// Lcd.print(6, "Scan volgende kaart");
					// Button.waitForAnyPress();
					// }
				}
			}
		} while (kaarten.size() < INPUT);

		// kaarten scannen is klaar
		Lcd.print(6, "Alle kaarten zijn gescand");
		Button.waitForAnyPress();
		Lcd.clear(6);

		//printen gescande kaartem
		for (int i = 0; i < kaarten.size(); i++) {
			Lcd.clear(i + 3);
			Lcd.print(i + 3, kaarten.get(i).getNaamKaart());
	
		}
		//maakt voorspelling
		Voorspelling2 zegwaar  =  new Voorspelling2(kaarten);
		
				//uitspugen voorspelling
		Lcd.clear(6);
		Lcd.print(6, zegwaar.toString());
		Button.waitForAnyPress();

		// Weet niet of deze nodig is
		Delay.msDelay(1000);

		// Einde
		color.close();

		Sound.beepSequence(); // we are done.

		Button.LEDPattern(4);
		Button.waitForAnyPress();

	}

	public static boolean checkDubbel(Kaart kaart, ArrayList<Kaart> kaarten) {
		if (kaarten.size()==1) {
			if (kaart.getNaamKaart() == kaarten.get(0).getNaamKaart()) {
				return true;
			}
		}
		if (kaarten.size()==2) {
			if (kaart.getNaamKaart() == kaarten.get(0).getNaamKaart() || kaart.getNaamKaart() == kaarten.get(1).getNaamKaart()) {
				return true;
			}
		}
		return false;
	}

}
