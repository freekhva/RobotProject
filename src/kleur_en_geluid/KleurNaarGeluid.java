package kleur_en_geluid;

import lejos.hardware.Sound;

public class KleurNaarGeluid {
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

	
}
