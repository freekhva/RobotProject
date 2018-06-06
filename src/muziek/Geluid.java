package Kleurherkennen;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.Sounds;
import lejos.utility.Delay;

public class Geluid extends java.lang.Object implements Sounds {

	public static void main(String[] args) {

		System.out.println("Geluid demo");
		// Lcd.print(2, "Press to start");

		Button.waitForAnyPress();

		// Plays a tone, given its frequency and duration.
//		Sound.playTone(40, 4000);
//		System.out.println("Geluidje speelt af");
//		//
//		Delay.msDelay(1000);
		// // plays Upward tones.
//		Sound.beepSequenceUp();
//
//		Sound.beepSequence();

		 Jukebox player = new Jukebox();
		
		 player.play(Jukebox.ATEAM_THEME, false);

		// afspelen stoppen
		// player.off();
	}
}
