package Parcour;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


public class HoofdTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		RegulatedMotor motorKlein = new EV3MediumRegulatedMotor(MotorPort.D);
		for (int i = 0; i < 12; i++) {
			motorKlein.rotate(-120, false);
//			Delay.msDelay(100);
			motorKlein.rotate(120, false);
//			Delay.msDelay(500);
			motorKlein.rotate(-120, false);
//			Delay.msDelay(500);
			motorKlein.rotate(120, false);
			i++;
		}
	}
}
