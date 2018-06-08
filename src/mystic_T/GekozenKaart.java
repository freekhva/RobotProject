package mystic_T;


public class GekozenKaart extends Kaart{

	int toekomstwaarde;
	
	public GekozenKaart(String naamKaart, int rood, int groen, int blauw) {
		super(naamKaart, rood, groen, blauw);
		if (this.naamKaart.equals("Moordenaar")||this.naamKaart.equals("Dief")||this.naamKaart.equals("Predikant")||this.naamKaart.equals("Rood")) {
			//negatief
			this.toekomstwaarde = 1;
		} else {
			//postief
			this.toekomstwaarde = 2;
		}
		
	}

	public int getToekomstwaarde() {
		return toekomstwaarde;
	}
	

}
