package kleur_en_geluid;

import java.util.ArrayList;
import java.util.List;
import Kleurherkennen.*;
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
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.UnregulatedMotor;

public class Kleur_En_Geluid_Launcher implements SensorConstants{

	public static void main(String[] args) {
		// Aanmaken sensor en het opstarten riedeltje, motoren

				String kleur = "Grijs";
				Stopwatch stopwatch =  new Stopwatch();
				
				
				final UnregulatedMotor motorL = new UnregulatedMotor(MotorPort.B);
				final UnregulatedMotor motorR = new UnregulatedMotor(MotorPort.C);

				ColorSensor color = new ColorSensor(SensorPort.S3);
				TouchSensor touch = new TouchSensor((ADSensorPort) SensorPort.S2);

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

		        
		        //Starten opname
		        Lcd.clear(3);
				Lcd.print(3, "Rijden maar!");
		        Button.waitForAnyPress();
		        
		        // set motors to 50% power en rijden maar.
		        motorL.setPower(20);
		        motorR.setPower(20);

		        // wait 2 seconds.
		        //Delay.msDelay(4000);
				
				ArrayList<KleurNaarGeluid> test2 = new ArrayList<>();
				// kleuren opslaan in ArrayList
				
				
				do {
					if (!kleur.equals(ColorSensor.colorName(color.getColorID()))){
					test2.add(new KleurNaarGeluid(kleur,stopwatch.toonDuur()));
					Lcd.clear(4);
					kleur = ColorSensor.colorName(color.getColorID());
					stopwatch.setStartTijd(System.currentTimeMillis());
					Lcd.print(4, "Kleur=%s", kleur);
					Lcd.clear(5);
					Lcd.print(5, "Druk op escape om te stoppen");
					//nog een oplossing voor blijven afspelen
					Geluid(kleur, 1);
					}
					//Delay.msDelay(1000);
				}while (Button.ESCAPE.isUp() || touch.isPressed());
				
				//Stop motoren
				motorL.stop();
				motorR.stop();
				
				//afspelen van de ArrayList
				Lcd.clear(5);
				Lcd.print(5, "druk voor afspelen.");
				Button.waitForAnyPress();
				
				for(int i = 1; i<test2.size(); i++) {
					Geluid(test2.get(i).getKleur(),test2.get(i).getDuur() );
					Lcd.clear(3);
					Lcd.print(3, " %d isKleur=%s", i, test2.get(i).getKleur());
					Delay.msDelay(1000);
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
	public static void Geluid(String kleur, double duur){
		 duur = duur*1000; 
		switch (kleur) {
		case "Red": Sound.playTone(880, (int)duur); //A5
			break;
		case "Blue": Sound.playTone(988, (int)duur); //B5
			break;	
		case "Brown": Sound.playTone(523, (int)duur); //C5
			break;
		case "Green": Sound.playTone(587,(int)duur); //D5
		break;
	case "White": Sound.playTone(659, (int)duur); //E5
		break;	
	case "Black": Sound.playTone(698, (int)duur); //F5
		break;
	case "Yellow": Sound.playTone(784, (int)duur); //G5
		break;
		default: Lcd.print(7, "Geen input");
		break;
		}
	}
		}
