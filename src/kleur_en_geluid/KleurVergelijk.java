package kleur_en_geluid;

public class KleurVergelijk 
{
	private final int CORRECTION = 3;

	public boolean vergelijkKleur(int kleurInput, int kleurBasis) 
    {
        if( (kleurInput <= ( kleurBasis + CORRECTION ) && kleurInput >= ( kleurBasis - CORRECTION ) ) ) 
        {
            return true;	
        } 
        else
        {
            return false;
        }
    }
}
