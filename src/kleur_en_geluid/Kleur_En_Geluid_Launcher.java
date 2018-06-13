package kleur_en_geluid;

import java.util.ArrayList;
import java.util.Iterator;
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
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		 kleurNaarGeluid();
		// duurTijd();
		// calibrateTest();
		// testKleurenSensor();
	}
	
	// KLeur naar geluid methode
	private static void kleurNaarGeluid() throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		/////// Init vars //////
		// Constants
		final int VOLUME_HOOG = 100;
		final int VOLUME_MID = 50;
		final int STANDAARD_DUUR = 200;
		final int MOTOR_POWER = 30; // 20
		
		// kleuren uit de db laden
		Lcd.print(1, "Laad database,");
		Lcd.print(2, "in array..." );

		ArrayList<KleurNaarGeluid> kleurennoten = new ArrayList<>();
		KleurNaarGeluidDAO kleurennotenDAO = new KleurNaarGeluidDAO();
		kleurennotenDAO.MakeConnection();
		kleurennoten = kleurennotenDAO.selecAlleNotenUitDB();
				
		// ArrayList<KleurNaarGeluid> kleurennoten = new KleurNaarGeluidARR().getArrKleurennoten();
		ArrayList<KleurNaarGeluid> muziekstuk = new ArrayList<>();
		
		Lcd.clear();

		String kleur = "Grijs";
		String kleurmeter;
		int frequentie = 0;
		
		long startTijd = System.currentTimeMillis(); // Set begintijd
		long stopTijd = 0;
		TouchSensor touch = new TouchSensor();
		ColorSensor color = new ColorSensor(SensorPort.S4);

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

		// set motors to power en rijden maar.
		motorL.setPower( MOTOR_POWER );
		motorR.setPower( MOTOR_POWER );
		
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
				Sound.playNote( Sound.XYLOPHONE, frequentie, STANDAARD_DUUR );
			}
			// Get kleur van colorsensor
			kleur = ColorSensor.colorName( color.getColorID() );
			
			// Show on screen...
			Lcd.clear();
			Lcd.print(1, "Kleur=%s", kleur );
			Lcd.clear(2);
			Lcd.print(2, "Druk op escape om te stoppen");

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
			Sound.setVolume( VOLUME_MID );
			Sound.playNote( Sound.XYLOPHONE, muziekstuk.get(i).getFrequentie(), (int)muziekstuk.get(i).getDuurMetExtraDuration() ); // (int)muziekstuk.get(i).getDuur()

//			strTotal += String.format("Kleur: %s Duur: %d \n", 
//					muziekstuk.get(i).getKleur(),
//					(int)muziekstuk.get(i).getDuur()
//					);
		}
		
		Lcd.clear();
		Lcd.print(1, "Klaar.");
		Button.waitForAnyPress();

		// free up resources.
		color.close();
		motorL.close(); 
		motorR.close();
		
		// klaar
		Sound.beepSequence();
		Button.LEDPattern(4);
	}
	
	// Test Calibratie
	public static void calibrateTest()
	{
		ColorSensor color = new ColorSensor(SensorPort.S4);

		// Calibrate sensor
		float black;
		float white;
		 while( Button.ESCAPE.isUp() )
		 {
			 System.out.println("Black?");
				Button.waitForAnyPress();
				black = color.getAmbient();
					System.out.println("Black " + black);

				System.out.println("White?");
				Button.waitForAnyPress();
				white = color.getAmbient();
					System.out.println("White " + white);
		 }
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
	
	// Test KLeur Sensor
	public static void testKleurenSensor()
	{
		ColorSensor color = new ColorSensor(SensorPort.S4);
		color.setColorIdMode();
		color.setFloodLight(Color.WHITE);

		//kleursensor een meting laten doen om hem te initialiseren
		System.out.println("Meet een kleur...");
		while ( true )
		{
			Button.waitForAnyPress();
			String kleur = ColorSensor.colorName(color.getColorID());
				System.out.println("Kleur: " + kleur);
		}
		
	}
}
