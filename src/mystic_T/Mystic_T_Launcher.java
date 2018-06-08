package mystic_T; // hier zit een rare error

import sensoren.*;
import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
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

		ArrayList<GekozenKaart> kaarten = new ArrayList<>();
		ArrayList<Kaart> tarotkaarten = new ArrayList<Kaart>();
		tarotkaarten.add(new Kaart("Rood", 57, 5, 4));
		tarotkaarten.add(new Kaart("Groen", 11, 35, 8));
		tarotkaarten.add(new Kaart("Blauw", 8, 38, 38));
		tarotkaarten.add(new Kaart("Moordenaar", 27, 22, 12));
		tarotkaarten.add(new Kaart("Predikant", 32, 24, 15));
		tarotkaarten.add(new Kaart("Koopman", 22, 18, 10));
		tarotkaarten.add(new Kaart("Bouwmeester", 23, 18, 10));
		tarotkaarten.add(new Kaart("Koning", 21, 16, 12));
		tarotkaarten.add(new Kaart("Magier", 27, 29, 18));
		tarotkaarten.add(new Kaart("Condotierre", 16, 16, 10));
		tarotkaarten.add(new Kaart("Dief", 20, 17, 9));

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
							kaarten.add(new GekozenKaart(kaart.getNaamKaart(), rood, groen, blauw));
							if (kaarten.size() < INPUT) {
								Lcd.print(6, "Scan volgende kaart");
								Button.waitForAnyPress();
							}
						}
					} else {
						Lcd.clear(5);
						Lcd.print(5, "Dit is de %s", kaart.getNaamKaart());
						Sound.beep();
						kaarten.add(new GekozenKaart(kaart.getNaamKaart(), rood, groen, blauw));
						Lcd.print(6, "Scan volgende kaart");
						Button.waitForAnyPress();
					}
					// Dit is niet meer nodig, maar laat maar even staan.
					// } else if (kaart.testKleur(groen, kaart.getGroen()) && kaart.testKleur(rood,
					// kaart.getRood())) {
					// Lcd.clear(5);
					// Lcd.print(5, "Dit is de %s", kaart.getNaamKaart());
					// Sound.beep();
					// kaarten.add(new GekozenKaart(kaart.getNaamKaart(), rood, groen, blauw));
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
		//uitspuge voorspelling
		Lcd.clear(6);
		Lcd.print(6, voorspelling(kaarten));
		Button.waitForAnyPress();

		// Weet niet of deze nodig is
		Delay.msDelay(1000);

		// Einde
		color.close();

		Sound.beepSequence(); // we are done.

		Button.LEDPattern(4);
		Button.waitForAnyPress();

	}

	public static boolean checkDubbel(Kaart kaart, ArrayList<GekozenKaart> kaarten) {
		for (int i = 0; i < kaarten.size(); i++) {
			if (kaart.getNaamKaart() == kaarten.get(i).getNaamKaart()) {
				return true;
			}
		}
		return false;
	}

	public static String voorspelling(ArrayList<GekozenKaart> kaarten) {
		int werk = kaarten.get(0).getToekomstwaarde() * 100;
		int liefde = kaarten.get(1).getToekomstwaarde() * 10;
		int gezondheid = kaarten.get(2).getToekomstwaarde();
		int voorspelling = werk + liefde + gezondheid;
		Voorspelling eindvoorspelling = new Voorspelling(voorspelling);

		return eindvoorspelling.maakVoorspelling();
	}
}
