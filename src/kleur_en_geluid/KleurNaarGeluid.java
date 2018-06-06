package kleur_en_geluid;

import basisoefeningen.Lcd;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import java.util.ArrayList;
import java.util.List;

public class KleurNaarGeluid {
	//dit voegt weinig toe op dit moment, maar het bestaat
	public final static int[] PIANO = new int[]{4, 25, 500, 7000, 5};
	private String kleur;
	private double duur;
	
	public KleurNaarGeluid(String kleur, double duur) {
		this.kleur = kleur;
		this.duur = duur;
	}

	public String getKleur() {
		return kleur;
	}

	public double getDuur() {
		return duur;
	}
	
	
	public void Geluid(){
		duur =  this.duur*1000; 
		switch (kleur) {
		case "Red": Sound.playNote(PIANO, 880, (int)duur); //A5
			break;
		case "Blue": Sound.playNote(PIANO, 988, (int)duur); //B5
			break;	
		case "Brown": Sound.playNote(PIANO, 523, (int)duur); //C5
			break;
		case "Green": Sound.playNote(PIANO, 587,(int)duur); //D5
			break;
		case "White": Sound.playNote(PIANO, 659, (int)duur); //E5
			break;	
		case "Black": Sound.playNote(PIANO, 698, (int)duur); //F5
			break;
		case "Yellow": Sound.playNote(PIANO, 784, (int)duur); //G5
			break;
		default: Lcd.print(7, "Geen input");
			break;
		}
	}
	
}
