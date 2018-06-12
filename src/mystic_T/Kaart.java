package mystic_T;

public class Kaart {
	//variabelen die we nodig hebben
	int nummer;
	String naamKaart;
	int rood;
	int blauw;
	int groen;
	int onderwerp;
	int type;
	
	//constructor
	public Kaart(int nummer, String naamKaart, int rood, int groen, int blauw, int onderwerp, int type) {
		super();
		this.nummer = nummer;
		this.naamKaart = naamKaart;
		this.rood = rood;
		this.blauw = blauw;
		this.groen = groen;
		this.onderwerp = onderwerp;
		this.type = type;
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
	
	public int getOnderwerp() {
		return onderwerp;
	}

	public int getType() {
		return type;
	}
	
	public int getNummer() {
		return nummer;
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
