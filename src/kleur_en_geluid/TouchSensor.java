package kleur_en_geluid;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.SensorConstants;
import lejos.robotics.Touch;

public class TouchSensor implements SensorConstants, Touch {
	ADSensorPort port;
	
	/**
	 * Create a touch sensor object attached to the specified port.
	 * @param s2 an Analog/Digital port, e.g. SensorPort.S1
	 */
	public TouchSensor(ADSensorPort s2)
	{
	   this.port = s2;
//	   port.setTypeAndMode(TYPE_SWITCH, MODE_BOOLEAN);
	}
	
	/**
	 * Check if the sensor is pressed.
	 * @return <code>true</code> if sensor is pressed, <code>false</code> otherwise.
	 */
	public boolean isPressed()
	{
		return (port.readRawValue() < 600);  
	}
}