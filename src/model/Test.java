package model;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import sensoren.*;

public class Test 
{
	public static void main(String[] args) 
	{
		TouchSensor touch = new TouchSensor();
		
		 // Button.waitForAnyPress();
		while (true)
		{
			 if( touch.getTouchSensorAanUit() )
				System.out.println("Button aan!!! ");
		}
	}
}
