package kleur_en_geluid;

import basisoefeningen.Lcd;
import lejos.hardware.Sound;




public class KleurNaarGeluid{
	//dit voegt weinig toe op dit moment, maar het bestaat

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

	
	public void arrayGeluid(){
		duur =  this.duur*1000; 
		switch (kleur) {
		case "Red": Sound.playNote(Sound.PIANO, 880, (int)duur); //A6
			break;
		case "Blue": Sound.playNote(Sound.PIANO, 988, (int)duur); //B5
			break;	
		case "Brown": Sound.playNote(Sound.PIANO, 523, (int)duur); //C5
			break;
		case "Green": Sound.playNote(Sound.PIANO, 587,(int)duur); //D5
			break;
		case "White": Sound.playNote(Sound.PIANO, 659, (int)duur); //E5
			break;	
		case "Black": Sound.playNote(Sound.PIANO, 698, (int)duur); //F5
			break;
		case "Yellow": Sound.playNote(Sound.PIANO, 784, (int)duur); //G5
			break;
		default: Lcd.print(7, "Geen input");
			break;
		}
	}
	
}
