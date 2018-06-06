package kleur_en_geluid;

public class Stopwatch {

	//Begin tijd
	private long startTijd;


	public Stopwatch(){
		startTijd = System.currentTimeMillis();
	}
	
	public long getStartTijd() {
		return startTijd;
	}



	public void setStartTijd(long startTijd) {
		this.startTijd = System.currentTimeMillis();
	}

	
	
	// duur bepalen
	public double toonDuur() {
		long now = System.currentTimeMillis();
        return (now - startTijd) / 1000.0;
	}
}
