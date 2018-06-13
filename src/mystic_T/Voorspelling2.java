package mystic_T;

import java.util.ArrayList;

public class Voorspelling2 {

	int onderwerp;
	int type;
	ArrayList<Kaart> kaarten;
	final static int WERK = 1;
	final static int LIEFDE = 2;
	final static int GEZONDHEID = 3;
	final static int LEVEN = 4;
	final static int POSITIEF = 2;
	final static int NEGATIEF = 1;
	final static int SLANGKAART = 7;
	final static int BEERKAART = 15;
	final static int ROEDEKAART = 11;
	final static int WOLKKAART = 6;
	final static int RINGKAART = 25;
	final static int HEERKAART = 28;
	final static int[] WOLKNEGATIEF = new int[] { 2, 16, 18, 30, 27 };

	// constructor
	public Voorspelling2(ArrayList<Kaart> kaarten) {
		this.kaarten = kaarten;
		this.onderwerp = kiesOnderwerp();
		this.type = kiesType();
	}

	// maakt voorspelling

	public String toString() {
		String onderwerpVoorspelling = "Bla ";
		String polariteit = "Bliep ";
		if (getOnderwerp() == 1) {
			onderwerpVoorspelling = "Werk ";
		} else if (getOnderwerp() == 2) {
			onderwerpVoorspelling = "Liefde ";
		} else if (getOnderwerp() == 3) {
			onderwerpVoorspelling = "Gezondheid ";
		} else {
			onderwerpVoorspelling = "Leven ";
		}
		if (getType() == 2) {
			polariteit = "Positief";
		} else {
			polariteit = "Negatief";
		}

		return onderwerpVoorspelling + polariteit;
	}

	public void audioVoorspellingBasis() {

		if (getOnderwerp() == WERK) {
			// speel af geluid werk

		} else if (getOnderwerp() == LIEFDE) {
			// speel af geluid liefde

		} else if (getOnderwerp() == GEZONDHEID) {
			// speel af geluid gezondheid

		} else  if (getOnderwerp() == LEVEN ){
			// speel af geluid leven

		}
		if (getType() == POSITIEF) {
			// speel af positief

		} else  if (getType() == NEGATIEF){
			// speel af negatief

		}
	}

	public void audioVoorspellingRest() {
		if (getType() == POSITIEF) {
			switch (getOnderwerp()) {
			case WERK: // speel af ((int)Math.random()*werkpositiefnarray.length-1))
				break;
			case LIEFDE: // speel af ((int)Math.random()*liefdepositiefnarray.length-1))
				break;
			case GEZONDHEID: // speel af ((int)Math.random()*gezondheidpositiefnarray.length-1))
				break;
			case LEVEN: // speel af ((int)Math.random()*levenpositiefnarray.length-1))
				break;
			}
		} else if (getType() == NEGATIEF) {
			switch (getOnderwerp()) {
			case WERK: // speel af ((int)Math.random()*werknegatiefnarray.length-1))
				break;
			case LIEFDE: // speel af ((int)Math.random()*liefdenegatiefnarray.length-1))
				break;
			case GEZONDHEID: // speel af ((int)Math.random()*gezondheidnegatiefnarray.length-1))
				break;
			case LEVEN: // speel af ((int)Math.random()*levennegatiefnarray.length-1))
				break;
			}
		}
		if (kaartAanwezig(BEERKAART) == true) {
			// speel geluid jaloezie af Gollem?
		} else if ((wolkGevoelig() == true && kaartAanwezig(WOLKKAART) == true && sumType() <4)) {
			// speel geluid wolken af
		} else if (kaartAanwezig(HEERKAART) == true) {
			// speel geluid stranger af
		}
	}

	// getters
	public int getOnderwerp() {
		return onderwerp;
	}

	public int getType() {
		return type;
	}

	public ArrayList<Kaart> getKaarten() {
		return kaarten;
	}

	public int kiesOnderwerp() {
		if (kaarten.get(0).getOnderwerp() != 0) {
			return kaarten.get(0).getOnderwerp();
		} else if (kaarten.get(1).getOnderwerp() != 0) {
			return kaarten.get(1).getOnderwerp();
		} else if (kaarten.get(2).getOnderwerp() != 0) {
			return kaarten.get(2).getOnderwerp();

		} else {
			return ((int) (Math.random() * 4));
		}
	}

	public int kiesType() {
		if (kaartAanwezig(BEERKAART) == true) {
			return 2;
		} else if (kaartAanwezig(SLANGKAART) == true || kaartAanwezig(ROEDEKAART) == true) { // super negatieve kaarten
			return 1;
		} else if (wolkGevoelig() == true && kaartAanwezig(WOLKKAART) == true) { // Kijken of de wolkkaart aanwezig is
																					// en er wolkgevoelige kaarten
			return 2;
		} else if (kaartAanwezig(RINGKAART) == true && kaarten.get(0).getNummer() == RINGKAART) { // ringkaart
																									// implementeren
			return 1;
		} else if (kaartAanwezig(RINGKAART) == true && kaarten.get(2).getNummer() == RINGKAART) {
			return 2;
		} else {

			return bepaalType();
		}
	}

	public boolean kaartAanwezig(int nummer) {
		boolean aanwezig = false;
		for (Kaart kaart : kaarten) {
			if (kaart.getNummer() == nummer) {
				aanwezig = true;
			}
		}
		return aanwezig;
	}

	public int sumType() {
		int sum = 0;
		for (Kaart kaart : kaarten) {
			sum = sum + kaart.getType();
		}
		return sum;
	}

	public boolean wolkGevoelig() {
		boolean gevoelig = false;
		for (Kaart kaart : kaarten) {
			if (kaart.getNummer() == WOLKNEGATIEF[0] || kaart.getNummer() == WOLKNEGATIEF[1]
					|| kaart.getNummer() == WOLKNEGATIEF[2] || kaart.getNummer() == WOLKNEGATIEF[3]
					|| kaart.getNummer() == WOLKNEGATIEF[4]) {
				gevoelig = true;
			}
		}
		return gevoelig;
	}

	public int bepaalType() {
		int positief = 0;
		int negatief = 0;
		for (Kaart kaart : kaarten) {
			if (kaart.getType() == POSITIEF) {
				positief++;
			} else if (kaart.getType() == NEGATIEF) {
				negatief++;
			}
		}
		if (positief > negatief) {
			return POSITIEF;
		} else if (negatief > positief) {
			return NEGATIEF;
		} else {

			return ((int) (Math.random() * 2) + 1);
		}
	}

}
