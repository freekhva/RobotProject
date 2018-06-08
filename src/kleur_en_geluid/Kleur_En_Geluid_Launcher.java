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




public class Kleur_En_Geluid_Launcher implements SensorConstants{

	public static void main(String[] args) {
		// Aanmaken sensor en het opstarten riedeltje, motoren

				String kleur = "Grijs";
				Stopwatch stopwatch =  new Stopwatch();
				TouchSensor touch = new TouchSensor();
				
				
				final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
				final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

				ColorSensor color = new ColorSensor(SensorPort.S3);
				ArrayList<KleurNaarGeluid> kleurtoon = new ArrayList<>();
				

								
//				System.out.println("Kleur van geluid");
				Lcd.print(1, "Druk om te beginnen");

				Button.LEDPattern(4); // flash green led and
				Sound.beepSequenceUp(); // make sound when ready.

				Button.waitForAnyPress();
				Button.LEDPattern(0);

				// Starten van de sensor

				Delay.msDelay(1000);

				color.setColorIdMode();
				color.setFloodLight(Color.WHITE);
				
				//kleursensor een meting laten doen om hem te initialiseren
				kleur = ColorSensor.colorName(color.getColorID());
				Lcd.print(2,"Beginkleur =" + kleur);

		        
		        //Starten opname
		        Lcd.clear(3);
				Lcd.print(3, "Rijden maar!");
		        Button.waitForAnyPress();
		        
		        // set motors to 50% power en rijden maar.
		        motorL.setPower(20);
		        motorR.setPower(20);

		        // wait 2 seconds.
		        //Delay.msDelay(4000);
				
				// kleuren opslaan in ArrayList				
				do {
					if (!kleur.equals(ColorSensor.colorName(color.getColorID()))){
					kleurtoon.add(new KleurNaarGeluid(kleur,stopwatch.toonDuur()));
					Lcd.clear(4);
					kleur = ColorSensor.colorName(color.getColorID());
					stopwatch.setStartTijd(System.currentTimeMillis());
					Lcd.print(4, "Kleur=%s", kleur);
					Lcd.clear(5);
					Lcd.print(5, "Druk op escape om te stoppen");
					//nog een oplossing voor blijven afspelen
					Geluid(kleur);
					}
					
				}while (!touch.getTouchSensorAanUit() && Button.ESCAPE.isUp()); //  || !touch.getTouchSensorAanUit() Button.ESCAPE.isUp())
				
				
				//Stop motoren
				motorL.stop();
				motorR.stop();
				
				//afspelen van de ArrayList
				Lcd.clear(5);
				Lcd.print(5, "druk voor afspelen.");
				Button.waitForAnyPress();
				
				for(int i = 1; i<kleurtoon.size(); i++) {
				kleurtoon.get(i).arrayGeluid();
				Lcd.clear(3);
				Lcd.print(3, "%d is %s", i, kleurtoon.get(i).getKleur());
			
				}
					
				// free up resources.
				color.close();
				motorL.close(); 
				motorR.close();
				
				Sound.beepSequence(); // we are done.

				Button.LEDPattern(4);
				//Button.waitForAnyPress();

			}
		//afspelen van de juiste noten		
	public static void Geluid(String kleur){ 
		switch (kleur) {
		case "Red": Sound.playTone( 880, 80); //A5
			break;
		case "Yellow": Sound.playTone(988, 80); //B5
			break;	
		case "Brown": Sound.playTone(523, 80); //C5
			break;
		case "Green": Sound.playTone(587, 80); //D5
			break;
		case "White": Sound.playTone(659, 80); //E5
			break;	
		case "Black": Sound.playTone(698, 80); //F5
			break;
		case "Blue": Sound.playTone(784, 80); //G5
			break;
		default: Lcd.print(7, "Geen input");
			break;
		}
	}
		}
