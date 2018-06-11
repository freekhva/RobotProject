package kleur_en_geluid;

import java.util.ArrayList;

public class KleurNaarGeluidRGBARR 
{
	private ArrayList<KleurNaarGeluidRGB> arrkleurennoten = new ArrayList<>();
	
	public ArrayList<KleurNaarGeluidRGB> getArrKleurennoten()
	{
		arrkleurennoten.add( new KleurNaarGeluidRGB( 8, 30, 2, "C7", 2093 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 74, 44, 12, "B6", 1975 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 63, 6, 4, "AIS6", 1865 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 28, 55, 9, "A6", 1760 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 71, 13, 5, "G6", 1568 ));
		
		return arrkleurennoten;
	}
}
