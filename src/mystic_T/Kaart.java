package mystic_T;

public class Kaart {
	//variabelen die we nodig hebben
	String naamKaart;
	int rood;
	int blauw;
	int groen;
	
	//constructor
	public Kaart(String naamKaart, int rood, int groen, int blauw) {
		super();
		this.naamKaart = naamKaart;
		this.rood = rood;
		this.blauw = blauw;
		this.groen = groen;
	}

	public String getNaamKaart() {
		return naamKaart;
	}

	public int getRood() {
		return rood;
	}

	public int getBlauw() {
		return blauw;
	}

	public int getGroen() {
		return groen;
	}
	//kleuren testen van de kaart met de tarotwaarden
	public boolean testKleur(int kleur, int tarotkleur) {
		
			if( (kleur<= (tarotkleur+1) && kleur>=(tarotkleur-1))) {
				return true;	
			} else {
				
		return false;
			}
			}
	
}
