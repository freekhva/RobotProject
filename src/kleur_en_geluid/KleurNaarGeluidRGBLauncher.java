package kleur_en_geluid;

import lejos.robotics.Color;

import java.util.ArrayList;

import basisoefeningen.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;

public class KleurNaarGeluidRGBLauncher {

	public static void main(String[] args) 
	{
		// Get de kleuren uit de db
		 ArrayList<KleurNaarGeluidRGB> kleurengeluid = new KleurNaarGeluidRGBDAO().selecAlleKaartentUitDB();
		
		// Sound.playNote(Sound.PIANO, 587, 5);
		
		KleurNaarGeluidRGB colorSound = new KleurNaarGeluidRGB(22, 22, 22, Noten.A5);
		System.out.println( "Geef een kleur: " );
		
		// Instellen juiste sensor
		ColorSensor colorsensor = new ColorSensor(SensorPort.S1);

		colorsensor.setRGBMode();
		colorsensor.setFloodLight(Color.WHITE);

		Color rgb;
		
		Button.waitForAnyPress();
		
		System.out.println("Get Color Waarde....");
		while( Button.ESCAPE.isUp() )
		{
			rgb = colorsensor.getColor();
			
			System.out.println( String.format("Rood: %d, Groen: %d, Blauw: %d", rgb.getRed(),
					rgb.getGreen(),
					rgb.getBlue()
					));
		}
	}

}
