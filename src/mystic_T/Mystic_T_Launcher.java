package mystic_T;

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

public class Mystic_T_Launcher {

	public static void main(String[] args) {

		ColorSensor color = new ColorSensor(SensorPort.S3);
		int rood;
		int blauw;
		int groen;
		ArrayList<Kaart> kaarten = new ArrayList<>();
		ArrayList<Kaart> tarotkaarten = new ArrayList<Kaart>();
		tarotkaarten.add (new Kaart("Rood", 57,5,4));
		tarotkaarten.add (new Kaart("Groen", 11,35,8));
		tarotkaarten.add (new Kaart("Blauw", 8,38,38));
		
        System.out.println("Mystic T");
        Lcd.print(2, "Druk een knop");
        
        Button.LEDPattern(4);    // flash green led and
        Sound.beepSequenceUp();    // make sound when ready.

        Button.waitForAnyPress();
        Button.LEDPattern(0);
        
        // run until escape button pressed.
		
        color.setRGBMode();
        color.setFloodLight(Color.WHITE);
        
        Color rgb;
       
        Lcd.clear(3);
        Lcd.print(3, "Scan een kaart");
        Button.waitForAnyPress();
        
        do
       {
            rgb = color.getColor();
            Lcd.clear(4);
            Lcd.clear(5);
            Lcd.clear(6);
            Lcd.print(4, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
            rood = rgb.getRed();
            groen = rgb.getGreen();
            blauw = rgb.getBlue();
            
            //checken of de kaart in de tarotarray voorkomt
            for (Kaart kaart : tarotkaarten ) {
            	if (rood == kaart.getRood()) {
            		Lcd.print(5, "Dit is een rode kaart");
            		Sound.beep();
                    kaarten.add (new Kaart(kaart.getNaamKaart(),rood, blauw, groen));
                    Lcd.print(6, "Scan volgende kaart");
                    Button.waitForAnyPress();
            	} else if(blauw == kaart.getBlauw()) {
            		Lcd.print(5, "Dit is een blauwe kaart");
            		Sound.beep();
                    kaarten.add (new Kaart(kaart.getNaamKaart(), rood, blauw, groen));
                    Lcd.print(6, "Scan volgende kaart");
                    Button.waitForAnyPress();
            	} else if (groen == kaart.getGroen()) {
            		Lcd.print(5, "Dit is een groene kaart");
            		Sound.beep();
                    kaarten.add (new Kaart(kaart.getNaamKaart(), rood, blauw, groen));
                    Lcd.print(6, "Scan volgende kaart");
                    Button.waitForAnyPress();
            	}
            } 
       } while (kaarten.size()<3);
        
        //kaarten scannen is klaar
        Lcd.print(6, "Alle kaarten zijn gescand");
        Button.waitForAnyPress();
        
        for (int i =0; i<kaarten.size(); i++ ) {
         Lcd.clear(i+3);
         Lcd.print(i+3, kaarten.get(i).getNaamKaart());
        	
        }
        

        //Weet niet of deze nodig is
        Delay.msDelay(1000);
		
        //Deze forloop checkt de kleuren van de 3 kaarten met de al bestaande lijst)
        //for(int i = 0; i<kaarten.size(); i++) {
        	//if (kaarten.get(i).getRood() == kaarten.get(i+1).getRood()){
        		//Sound.beepSequence();
        	//}
        	//kaarten.get(i).getBlauw();
        	//kaarten.get(i).getGroen();
       // }
        
	
	
        //Einde
        color.close();
        
        Sound.beepSequence();    // we are done.

        Button.LEDPattern(4);
        Button.waitForAnyPress();
        
	}

}
