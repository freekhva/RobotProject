package kleur_en_geluid;

import java.util.ArrayList;

public class KleurNaarGeluidARR 
{
	private ArrayList<KleurNaarGeluid> arrkleurennoten = new ArrayList<>();
	
	public ArrayList<KleurNaarGeluid> getArrKleurennoten()
	{
		arrkleurennoten.add( new KleurNaarGeluid( "Blue", "F6", 1397 ));
		arrkleurennoten.add( new KleurNaarGeluid( "White", "C6", 1046 ));
		arrkleurennoten.add( new KleurNaarGeluid( "Green", "AIS5", 932 ));
		arrkleurennoten.add( new KleurNaarGeluid( "Red", "F5", 698 ));
		arrkleurennoten.add( new KleurNaarGeluid( "Black", "A5", 880 ));
		
		// Moeilijke kleuren noten
		arrkleurennoten.add( new KleurNaarGeluid( "Yellow", "G6", 1568 ));
		arrkleurennoten.add( new KleurNaarGeluid( "Brown", "D6", 1175 ));
		
		return arrkleurennoten;
	}
}
