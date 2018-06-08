package mystic_T;

import java.util.ArrayList;

public class Voorspelling {
	// declareren variabelen
	int voorspelling;
	String voorspellingtekst;

	public Voorspelling(int voorspelling) {
		super();
		this.voorspelling = voorspelling;
		this.voorspellingtekst = maakVoorspelling();
	}

	public String maakVoorspelling() {
		if (this.voorspelling < 200) {
			if (this.voorspelling < 120) {
				if (this.voorspelling < 112) {
					// waarde 111
					return "Dood & Verderf";
				} else {
					// waarde 112
					return "Verderf, niet dood";
				}
			} else {
				if (this.voorspelling < 122) {
					// waarde 121
					return "Dood, maar niet alleen";
				} else {
					// waarde 122
					return "Zoek andere baan";
				}

			}
		} else {
			if (this.voorspelling < 220) {
				if (this.voorspelling < 212) {
					// waarde 211
					return "Je werkt je dood en alleen";
				} else {
					// waarde 212
					return "Liefde of werk?";
				}

			} else {
				if (this.voorspelling < 122) {
					// waarde 221
					return "Blij, maar wel dood";
				} else {
					// waarde 222
					return "Pais en Vree";
				}
			}
		}
	}
}
