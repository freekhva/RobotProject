package kleur_en_geluid;

//import basisoefeningen.Lcd;
//import lejos.hardware.Sound;
//import lejos.hardware.Sounds;


public class KleurNaarGeluid // implements Sounds
{
	private final long XTRA_DURATION = 500;
	private String kleur;
	private int frequentie;
	private String nootcode;
	private long duur;
	
	public String getKleur() 
	{
		return kleur;
	}

	public long getDuur() 
	{
		return this.duur = this.duur;
	}
	
	public long getDuurMetExtraDuration() 
	{
		return this.duur = this.duur + XTRA_DURATION;
	}

	public int getFrequentie() 
	{
		return frequentie;
	}

	public String getNootcode() 
	{
		return nootcode;
	}
	
	public KleurNaarGeluid(String _kleur, int _frequentie, long _duur) 
	{
		this.kleur = kleur;
		this.frequentie = _frequentie;
		this.duur = duur;
	}
	
	public KleurNaarGeluid(String _kleur, String _nootcode, int _frequentie ) 
	{
		this.kleur = _kleur;
		this.nootcode = _nootcode;
		this.frequentie = _frequentie;
	}
}

//////// OLD STUFF //////////
//public void arrayGeluid(){
//	this.duur = duur*500;
//	switch (kleur) {
//	case "Red": Sound.playNote(Sound.PIANO, 880, (int)duur); //A6
//		break;
//	case "Yellow": Sound.playNote(Sound.PIANO, 988, (int)duur); //B5
//		break;	
//	case "Brown": Sound.playNote(Sound.PIANO, 523, (int)duur); //C5
//		break;
//	case "Green": Sound.playNote(Sound.PIANO, 587,(int)duur); //D5
//		break;
//	case "White": Sound.playNote(Sound.PIANO, 659, (int)duur); //E5
//		break;	
//	case "Black": Sound.playNote(Sound.PIANO, 698, (int)duur); //F5
//		break;
//	case "Blue": Sound.playNote(Sound.PIANO, 784, (int)duur); //G5
//		break;
//	default: Lcd.print(7, "Geen input");
//		break;
//	}
//}

