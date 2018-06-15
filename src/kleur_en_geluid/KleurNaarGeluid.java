package kleur_en_geluid;

public class KleurNaarGeluid
{
	private final long XTRA_DURATION = 400;
	private String kleur;
	private int frequentie;
	private String nootcode;
	private long duur;
	
	public String getKleur() 
	{
		return kleur;
	}

	public long getDuur() 
	{
		return this.duur = this.duur;
	}
	
	public long getDuurMetExtraDuration() 
	{
		return this.duur = this.duur + XTRA_DURATION;
	}

	public int getFrequentie() 
	{
		return frequentie;
	}

	public String getNootcode() 
	{
		return nootcode;
	}
	
	public KleurNaarGeluid(String _kleur, int _frequentie, long _duur) 
	{
		this.kleur = kleur;
		this.frequentie = _frequentie;
		this.duur = duur;
	}
	
	public KleurNaarGeluid(String _kleur, String _nootcode, int _frequentie ) 
	{
		this.kleur = _kleur;
		this.nootcode = _nootcode;
		this.frequentie = _frequentie;
	}
}
