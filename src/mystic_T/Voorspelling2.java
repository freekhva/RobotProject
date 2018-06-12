package mystic_T;

import java.util.ArrayList;
import java.util.List;

public class Voorspelling2 {

	int onderwerp;
	int type;
	ArrayList<Kaart> kaarten;
	final int SLANGKAART = 7;
	final int BEERKAART = 15;
	final int ROEDEKAART = 11;
	final int WOLKKAART = 6;
	final int RINGKAART = 25;
	final int[] WOLKNEGATIEF = new int[] {16, 18, 30, 27}; 
	
	// constructor
	public Voorspelling2(ArrayList<Kaart> kaarten){
	this.kaarten = kaarten;
	this.onderwerp = kiesOnderwerp();
	this.type = kiesType();
	}

	
	// maakt voorspelling

	public String toString() {
		String onderwerpVoorspelling = "Bla ";
		String polariteit = "Bliep ";
		 if(getOnderwerp() == 1) {
			 onderwerpVoorspelling = "Werk ";
		 } else if(getOnderwerp() == 2) {
			 onderwerpVoorspelling = "Liefde ";
		 } else if(getOnderwerp() == 3) {
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
	
	//getters
	public int getOnderwerp() {
		return onderwerp;
	}


	public int getType() {
		return type;
	}


	public ArrayList<Kaart> getKaarten() {
		return kaarten;
	}
	
	public int kiesOnderwerp(){
	 if(kaarten.get(0).getOnderwerp() != 0){
		return kaarten.get(0).getOnderwerp();
	}else if ( kaarten.get(1).getOnderwerp() != 0){
		return kaarten.get(1).getOnderwerp();
	} else if (kaarten.get(2).getOnderwerp() != 0){
		return kaarten.get(2).getOnderwerp();
		
	} else {
		return ((int) (Math.random() *3));
	}   
	}


	public int kiesType(){
	if(kaartAanwezig(SLANGKAART) == true || kaartAanwezig(ROEDEKAART) == true || kaartAanwezig(ROEDEKAART) == true){ //super negatieve kaarten
		return 1;
	}
	if (kaartAanwezig(WOLKKAART) == true && sumType() ==4){ //kijken of de wolk kaart aanwezig is
		return 2;
	}  
	 if (wolkGevoelig() == true && kaartAanwezig(WOLKKAART) == true){ //Kijken of de wolkkaart aanwezig is en er wolkgevoelige kaarten
		return 1;
	 }
	 
	 if (kaartAanwezig(RINGKAART) == true && kaarten.get(0).getNummer() == RINGKAART) { //ringkaart implementeren
		 return 1;
	 } else if (kaartAanwezig(RINGKAART) == true && kaarten.get(2).getNummer() == RINGKAART) {
		 return 2;
	 }
	 
	 return bepaalType();
	}

	public boolean kaartAanwezig (int nummer){
	if(kaarten.get(0).getNummer() == nummer){
		return true;
	}else if ( kaarten.get(1).getNummer() == nummer){
		return true;
	} else if (kaarten.get(2).getNummer() == nummer){
		return true;
	} else{
		return false;
	}   
	
	}

	public int sumType(){
	int sum = 0;
	for (Kaart kaart : kaarten){
	sum = sum + kaart.getType();
	}  
	return sum;
	}

	public boolean wolkGevoelig (){
		if (kaarten.get(0).getNummer() == WOLKNEGATIEF[0] ||kaarten.get(0).getNummer() == WOLKNEGATIEF[1]|| kaarten.get(0).getNummer() == WOLKNEGATIEF[2]) {
		return true;
	}else if (kaarten.get(1).getNummer() == WOLKNEGATIEF[0] ||kaarten.get(1).getNummer() == WOLKNEGATIEF[1]|| kaarten.get(1).getNummer() == WOLKNEGATIEF[2]) {
		return true;
	} else if (kaarten.get(2).getNummer() == WOLKNEGATIEF[0] ||kaarten.get(2).getNummer() == WOLKNEGATIEF[1]|| kaarten.get(2).getNummer() == WOLKNEGATIEF[2]) {
		return true;
	} else{
		return false;
	}   
	}

	public int bepaalType() {
		int positief = 0;
		int negatief = 0;
		for (Kaart kaart : kaarten) {
			if (kaart.getType() == 2) {
			positief++; 
			} else if (kaart.getType() == 1) {
				negatief++; 
			}
		}
		if (positief>negatief) {
			return 2;
		} else if (negatief> positief) {
			return 1;
		} else  {
		
		return ((int) (Math.random()*2)+1); 
		}
		}

}
