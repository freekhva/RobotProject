package kleur_en_geluid;

import java.util.ArrayList;
import java.util.List;

import basisoefeningen.ColorSensor;
import basisoefeningen.Lcd;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorConstants;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
import muziek.*;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.UnregulatedMotor;
import sensoren.*;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;




public class Kleur_En_Geluid_Launcher implements SensorConstants
{
	public static void main(String[] args) 
	{
		kleurNaarGeluid();
		// duurTijd();
	}
	
	// KLeur naar geluid methode
	private static void kleurNaarGeluid() 
	{
		/////// Init vars //////
		// Constants
		final int VOLUME_HOOG = 100;
		final int STANDAARD_DUUR = 200;
		
		// kleuren uit de db
		// ArrayList<KleurNaarGeluid> kleurennoten = new KleurNaarGeluidDAO().selecAlleNotenUitDB();
		ArrayList<KleurNaarGeluid> kleurennoten = new KleurNaarGeluidARR().getArrKleurennoten();
		ArrayList<KleurNaarGeluid> muziekstuk = new ArrayList<>();

		String kleur = "Grijs";
		String kleurmeter;
		int frequentie = 0;
		
		long startTijd = System.currentTimeMillis(); // Set begintijd
		long stopTijd = 0;
		TouchSensor touch = new TouchSensor();
		ColorSensor color = new ColorSensor(SensorPort.S3);

		final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
		final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

		Lcd.print(1, "Start het programma...");
		Button.LEDPattern(4); // flash green led and
		Sound.beepSequenceUp(); // make sound when ready.

		Button.waitForAnyPress();
		Button.LEDPattern(0);
		Lcd.clear(); // Clear the display

		Delay.msDelay(1000);
		
		color.setColorIdMode();
		color.setFloodLight(Color.WHITE);

		//kleursensor een meting laten doen om hem te initialiseren
		kleur = ColorSensor.colorName(color.getColorID());
		Lcd.print(2,"Beginkleur =" + kleur);

		//Starten opname
		Lcd.print(3, "Rijden maar!");
		Button.waitForAnyPress();
		Lcd.clear(); // Clear the display

		// set motors to 50% power en rijden maar.
		motorL.setPower(20);
		motorR.setPower(20);
		
		// kleuren opslaan in ArrayList				
		do 
		{
			Delay.msDelay(10);
			if ( !kleur.equals( ColorSensor.colorName( color.getColorID() ) ) )
			{
				stopTijd = System.currentTimeMillis();
				
				for (int i = 0; i < kleurennoten.size(); i++) 
				{
					if( kleurennoten.get(i).getKleur().equals( kleur ) )
					{
						muziekstuk.add( new KleurNaarGeluid( kleur, kleurennoten.get(i).getFrequentie(), (stopTijd - startTijd ) ) );
						frequentie = kleurennoten.get(i).getFrequentie();
						Sound.setVolume( VOLUME_HOOG );
					}
				}
				// Speel noot 1 maal
				Sound.playNote( Sound.PIANO, frequentie, STANDAARD_DUUR );
				
				// Show on screen...
				Lcd.clear();
				Lcd.print(1, "Kleur=%s", kleur );
				Lcd.clear(2);
				Lcd.print(2, "Druk op escape om te stoppen");
			}
			// Get kleur van colorsensor
			kleur = ColorSensor.colorName( color.getColorID() );

			// Set stopwatch
			startTijd = System.currentTimeMillis();
		}
		while ( !touch.getTouchSensorAanUit() && Button.ESCAPE.isUp() ); //  || !touch.getTouchSensorAanUit() Button.ESCAPE.isUp())

		//Stop motoren
		motorL.stop();
		motorR.stop();

		// Afspelen van de ArrayList
		Lcd.clear();
		Lcd.print(1, "Druk voor afspelen...");
		Button.waitForAnyPress();

		String strTotal = "";
		for(int i = 1; i  < muziekstuk.size(); i++) 
		{
			Sound.setVolume( VOLUME_HOOG );
			Sound.playNote( Sound.PIANO, muziekstuk.get(i).getFrequentie(), (int)muziekstuk.get(i).getDuurMetExtraDuration() ); // (int)muziekstuk.get(i).getDuur()

			strTotal += String.format("Kleur: %s Duur: %d \n", 
					muziekstuk.get(i).getKleur(),
					(int)muziekstuk.get(i).getDuur()
					);
		}
		
		Lcd.clear();
		Lcd.print(1, "Klaar." + strTotal);
		Button.waitForAnyPress();

		// free up resources.
		color.close();
		motorL.close(); 
		motorR.close();
		
		// klaar
		Sound.beepSequence();
		Button.LEDPattern(4);
	}
	
	// Test CurrentTijd
	public static void duurTijd()
	{
		long begintTijd = System.currentTimeMillis();
		long eindTijd = 0;
		while( Button.ESCAPE.isUp() )
		{
			eindTijd = ( System.currentTimeMillis() - begintTijd );
			System.out.println( (int)eindTijd );
		}		
	}
}
