package kleur_en_geluid;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.Color;
import lejos.utility.Delay;
import sensoren.ColorSensor;
import sensoren.Lcd;
import sensoren.TouchSensor;

public class KLeurNaarGeluidAPP 
{
	// KLeur naar geluid methode
	public void kleurNaarGeluid() throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		/////// Init variabelen //////
		// Constanten
		final int VOLUME_HOOG = 100;
		final int VOLUME_MID = 50;
		final int STANDAARD_DUUR = 200;
		final int MOTOR_POWER = 30; // 20
		final int FREQ_CORRECTIE = 0;

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
		int frequentie = 0;
		long startTijd = System.currentTimeMillis(); // Set begintijd
		long stopTijd = 0;
		TouchSensor touch = new TouchSensor();
		ColorSensor color = new ColorSensor(SensorPort.S4);

		final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
		final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

		Lcd.print(1, "Start het programma...");
		Button.LEDPattern(4);
		Sound.beepSequenceUp(); // Even geluid wanneer klaar

		Button.waitForAnyPress();
		Button.LEDPattern(0);
		Lcd.clear(); // Clear the display

		Delay.msDelay(1000);

		color.setColorIdMode();
		color.setFloodLight(Color.WHITE);

		// kleursensor een meting laten doen om hem te initialiseren
		kleur = ColorSensor.colorName(color.getColorID());
		Lcd.print(2,"Beginkleur =" + kleur);

		// Starten het opnemen
		Lcd.print(3, "Rijden maar!");
		Button.waitForAnyPress();
		Lcd.clear(); // Clear the display

		// Motoren aan en rijden maar.
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

				// Set Starttijd opnieuw
				startTijd = System.currentTimeMillis();
			}
			// Get kleur van colorsensor
			kleur = ColorSensor.colorName( color.getColorID() );

			// Laat op scherm zien...
			Lcd.clear();
			Lcd.print(1, "Kleur=%s", kleur );
			Lcd.clear(2);
			Lcd.print(2, "Druk op escape om te stoppen");
		}
		while ( !touch.getTouchSensorAanUit() && Button.ESCAPE.isUp() ); //  || !touch.getTouchSensorAanUit() Button.ESCAPE.isUp())

		//Stop motoren
		motorL.stop();
		motorR.stop();

		// Afspelen van de ArrayList.
		Lcd.clear();
		Lcd.print(1, "Druk voor afspelen...");
		Button.waitForAnyPress();

		Sound.setVolume( VOLUME_MID );
		for(int i = 1; i  < muziekstuk.size(); i++) 
		{
			Sound.playNote( Sound.XYLOPHONE, ( muziekstuk.get(i).getFrequentie() - FREQ_CORRECTIE ), (int)muziekstuk.get(i).getDuurMetExtraDuration() ); // (int)muziekstuk.get(i).getDuur()
			if( Button.RIGHT.isDown() )
				break;
		}

		Lcd.clear();
		Lcd.print(1, "Klaar.");
		Button.waitForAnyPress();

		// Sluit de motoren en de kleurensensor.
		color.close();
		motorL.close(); 
		motorR.close();

		// klaar
		Sound.beepSequence();
		Button.LEDPattern(4);
	}
	
	// Test calibratie
	public void calibrateTest()
	{
		ColorSensor color = new ColorSensor(SensorPort.S4);

		// Calibratietest sensor
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

	// Test huidige tijd
	public void duurTijd()
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
	public void testKleurenSensor()
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

	// Toon ArrayList op het scherm
	public void toonArrayList( ArrayList<KleurNaarGeluid> muziekstuk)
	{
		String strTotal = "";
		for (int j = 0; j < muziekstuk.size(); j++) 
		{
			strTotal += String.format("Kleur: %s Duur: %d \n", 
					muziekstuk.get(j).getKleur(),
					(int)muziekstuk.get(j).getDuur()
					);
		}
	}
}
