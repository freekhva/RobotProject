package kleur_en_geluid;

public class KleurNaarGeluidRGB 
{
	private int rood;
	private int groen;
	private int blauw;
	
	private int frequentie;
	private String nootcode;
	
	public KleurNaarGeluidRGB(int _rood, int _groen, int _blauw, int _nootcode) 
	{
		this.rood = _rood;
		this.groen = _groen;
		this.blauw = _blauw;
		this.frequentie = _nootcode;
	}
	
	public int getFrequentie()
	{
		return this.frequentie;
	}

}
