package kleur_en_geluid;

import java.util.ArrayList;

public class KleurNaarGeluidRGBARR 
{
	private ArrayList<KleurNaarGeluidRGB> arrkleurennoten = new ArrayList<>();
	
	public ArrayList<KleurNaarGeluidRGB> getArrKleurennoten()
	{
		arrkleurennoten.add( new KleurNaarGeluidRGB( 9, 4, 56, "C7", 2093 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 32, 70, 18, "B6", 1975 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 68, 8, 6, "AIS6", 1865 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 75, 52, 21, "A6", 1760 ));
		arrkleurennoten.add( new KleurNaarGeluidRGB( 78, 16, 10, "G6", 1568 ));
		
		return arrkleurennoten;
	}
}
