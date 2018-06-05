package Kleurherkennen;

import basisoefeningen.Lcd;
import lejos.hardware.Sounds;
import lejos.utility.Delay;

public class Sound extends java.lang.Object implements Sounds {

	public static void main(String[] args) {
		
		System.out.println("Geluid demo");
		Lcd.print(2, "Press to start");
		
		// Plays a tone, given its frequency and duration.
		playTone(523,2000);
		
		Delay.msDelay(1000);
		// plays Upward tones.
		beepSequenceUp();
		
//		Jukebox player = new Jukebox();
//		
//		player.play(Jukebox.STARWARS_INTRO, false);
		
		// afspelen stoppen
//		player.off();
			
		}
	
	static void playTone(int freq, int duration) {

	}
	
	static void beepSequenceUp() {

	}
	


}
