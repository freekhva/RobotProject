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
		// TODO Auto-generated method stub

		ColorSensor color = new ColorSensor(SensorPort.S3);
		int rood;
		int blauw;
		int groen;
		ArrayList<Kaart> kaarten = new ArrayList<>();
		
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
        
        do
       {
            rgb = color.getColor();
            Lcd.clear(3);
            Lcd.print(3, "r=%d g=%d b=%d", rgb.getRed(), rgb.getGreen(), rgb.getBlue());
            Lcd.print(4, "Scan een kaart");
            Button.waitForAnyPress();
            rood = rgb.getRed();
            groen = rgb.getGreen();
            blauw = rgb.getBlue();
            kaarten.add (new Kaart(rood, blauw, groen));
            Delay.msDelay(250);
       } while (kaarten.size()<3);
            //}while (Button.ESCAPE.isUp());

        //Weet niet of deze nodig is
        Delay.msDelay(1000);
		
        for(int i = 0; i<kaarten.size(); i++) {
        	if (kaarten.get(i).getRood() == kaarten.get(i+1).getRood()){
        		Sound.beepSequence();
        	}
        	//kaarten.get(i).getBlauw();
        	//kaarten.get(i).getGroen();
        }
        
        //Einde
        color.close();
        
        Sound.beepSequence();    // we are done.

        Button.LEDPattern(4);
        Button.waitForAnyPress();
        
	}

}
