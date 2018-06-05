package Parcour;

import basisoefeningen.ColorSensor;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;

public class TestAmbientSensor 
{
	public static void main(String[] args) 
	{
		ColorSensor ambSensor = new ColorSensor(SensorPort.S3);
		ambSensor.setAmbientMode();
		
		float ambient;
		
		System.out.println("Press any key to start");
		Button.waitForAnyPress();
		
		while( true )
		{
			ambient = ambSensor.getAmbient();
			System.out.println("Ambient value: " + ambient);
		}
	}
}
