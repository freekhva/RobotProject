package kleur_en_geluid;

public class KleurNaarGeluidRGB 
{
	private final int XTRA_DURATION = 500;
	private int rood;
	private int groen;
	private int blauw;
	
	private int frequentie;
	private String nootcode;
	private double duur;
	
	public int getRood() {
		return this.rood;
	}

	public int getGroen() {
		return this.groen;
	}

	public int getBlauw() {
		return this.blauw;
	}
	
	public int getFrequentie()
	{
		return this.frequentie;
	}
	
	public double getDuur()
	{
		return this.duur;
	}
	
	public KleurNaarGeluidRGB(int _rood, int _groen, int _blauw, String _nootcode, int _frequentie) 
	{
		this.rood = _rood;
		this.groen = _groen;
		this.blauw = _blauw;
		this.nootcode = _nootcode;
		this.frequentie = _frequentie;
	}
	
	public KleurNaarGeluidRGB(int _rood, int _groen, int _blauw, int _frequentie) 
	{
		this.rood = _rood;
		this.groen = _groen;
		this.blauw = _blauw;
		this.frequentie = _frequentie;
	}
	
	public KleurNaarGeluidRGB(int _frequentie, double _duur)
	{
		this.frequentie = _frequentie;
		this.duur = _duur * XTRA_DURATION;
	}
}
