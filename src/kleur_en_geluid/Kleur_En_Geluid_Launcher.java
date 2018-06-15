package kleur_en_geluid;

import lejos.hardware.sensor.SensorConstants;

public class Kleur_En_Geluid_Launcher implements SensorConstants
{
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		KLeurNaarGeluidAPP app = new KLeurNaarGeluidAPP();
		app.kleurNaarGeluid();
		// app.duurTijd();
		// app.calibrateTest();
		// app.testKleurenSensor();
	}
}
