package kleur_en_geluid;

import lejos.hardware.port.BasicSensorPort;

public interface ADSensorPort extends BasicSensorPort {

	public boolean readBooleanValue();
	
	public int readRawValue();
	
	public int readValue();
}