package mystic_T;

public class Kaart {
	//variabelen die we nodig hebben
	String naamKaart;
	int rood;
	int blauw;
	int groen;
	
	//constructor
	public Kaart(String naamKaart, int rood, int blauw, int groen) {
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
	
	
}
