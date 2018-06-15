package sensoren;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.TextLCD;;

public class Lcd {
	private static Brick brick = BrickFinder.getLocal();
	private static TextLCD lcd = brick.getTextLCD(Font.getFont(0, 0, Font.SIZE_MEDIUM));

	public static final int MAXCHARS = lcd.getTextWidth();
	public static final int MAXLINES = lcd.getTextHeight();

	private Lcd() {
	}


	// TextLCD: geeft waarde aan TextLCD object
	
	public static TextLCD getTextLCD() {
		return lcd;
	}

	
	// clear: verwijdert text van de LCD
	public static void clear() {
		lcd.clear();
		lcd.refresh();
	}


	// scroll: laat text omhoog gaan per lijn
	
	public static void scroll() {
		lcd.scroll();
	}


	// clear line: verwijdert een lijn LCD text
	
	public static void clear(int line) {
		lcd.clear(line - 1);
		lcd.refresh();
	}

	
	// clear line specific: verwijderd specifieke tekst
	
	public static void clear(int line, int col, int len) {
		lcd.clear(col - 1, line - 1, len);
		lcd.refresh();
	}

	
	// print: print tekst naar specifieke lijn
	
	public static void print(int line, String message) {
		lcd.drawString(message, 0, line - 1);
		lcd.refresh();
	}

	
	// print String: naar specifieke lijn
	
	public static void print(int line, String message, Object... parms) {
		lcd.drawString(String.format(message, parms), 0, line - 1);
		lcd.refresh();
	}

	
	// print String kolom: print String naar specifieke kolom, 
	
	public static void print(int line, int col, String message) {
		lcd.drawString(message, col - 1, line - 1);
		lcd.refresh();
	}

	
	// print String param: print String naar bepaalde kolom met bepaalde parameters die overeenkomen
	
	public static void print(int line, int col, String message, Object... parms) {
		lcd.drawString(String.format(message, parms), col - 1, line - 1);
		lcd.refresh();
	}
}
