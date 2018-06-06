package sensoren;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;

public class TouchSensor 
{
	private EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S2); // = new EV3TouchSensor(SensorPort.S2);
	private float[] sample = new float[ this.touch.sampleSize() ]; // Sample Meter
	private final float touchWaarde = (float) 0.5;
	
	// Get Sample waarde in een float
	public float getSampleWaarde()
	{
		this.touch.fetchSample(sample, 0);
		return sample[0];
	}
	
	// Constructor
	public TouchSensor()
	{
	}
	
	// De Touch Sensor
	public EV3TouchSensor getTouchSensor()
	{
		return this.touch;
	}
	
	// Aan Uit meten te gebruiken in een while loop
	public boolean getTouchSensorAanUit()
	{
		this.touch.fetchSample(sample, 0);
		if( this.sample[0] > this.touchWaarde )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
