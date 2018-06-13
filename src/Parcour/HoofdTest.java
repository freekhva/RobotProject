package Parcour;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class HoofdTest {

	public static void main(String[] args) {
		// @SuppressWarnings("resource")
		// RemoteEV3 ev3 = new RemoteEV3("192.168.0.9");
		// RMIRegulatedMotor m = ev3.createRegulatedMotor("D");

		UnregulatedMotor motorKlein = new UnregulatedMotor(MotorPort.D);

		motorKlein.setPower(-20);
		Delay.msDelay(200);
		motorKlein.setPower(20);
		Delay.msDelay(200);
		motorKlein.setPower(-20);
		
		motorKlein.close();
	}
}
