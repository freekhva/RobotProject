package kleur_en_geluid;

public class KleurVergelijk 
{
	private final int CORRECTION = 3;

	public boolean vergelijkKleur(int kleurLeft, int kleurRight) 
	{
		if( (kleurLeft <= ( kleurRight + CORRECTION ) && kleurLeft >= ( kleurRight - CORRECTION ) ) ) 
		{
			return true;	
		} 
		else
		{
			return false;
		}
	}
}
