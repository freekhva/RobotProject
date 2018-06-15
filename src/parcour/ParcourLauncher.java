package parcour;

import lejos.hardware.Button;

public class ParcourLauncher {

	public static void main(String[] args) {
		
		// LEFT TURNING LINE FOLLOWER
		
		System.out.println("Drive Left");
		Button.waitForAnyPress();
		Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
//		robot.goDrive("Left");
		robot.goCalibrate();
		robot.goDriveLeft();
		
		
		// RIGHT TURNING LINE FOLLOWER
		
//		System.out.println("Drive Right");
//		Button.waitForAnyPress();
//		Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
//		robot.goDrive("Right");		
//		robot.goCalibrate();
//		robot.goDriveRight();
		
		// LEFT TURNING LINE FOLLOWER PLUS WHITE SKIP
		
//		System.out.println("Drive Left + White Skip");
//		Button.waitForAnyPress();
//		robot.goDrive("Left");
//		Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
//		robot.goCalibrate();
//		robot.goDriveLeft();
		
		
		
		// RIGHT TURNING LINE FOLLOWER PLUS WHITE SKIP
		
//		System.out.println("Drive Right + White Skip");
//		Button.waitForAnyPress();
//		robot.goDrive("Left");
//		Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
//		robot.goDrive("Right");		
//		robot.goCalibrate();
//		robot.goDriveRight();
		
		
		
		
		
		// TODO make a menu in order to create driveable robots using input from user

		// currently not working 
		
		//		Boolean start = true;
				
		//		if (start == true) {	
		//		System.out.println("Press button:");
		//		System.out.println("UP for lefturning track");
		//		System.out.println("DOWN for rightturning track");
		//		System.out.println("PLUS MODE");
		//		System.out.println("UP for lefturning track");
		//		System.out.println("DOWN for rightturning track");

		//		int choice = Button.getButtons();
		//		if (choice == Button.ID_RIGHT) {
		//			Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
		//			robot.goDrive("right");
		//		} else if (choice == Button.ID_LEFT) {
		//			Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
		//			robot.goDrive("left");
		//		}  else if (choice == Button.ID_UP) {
		//			Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
		//			robot.goDrive("right");
		//		}	else if (choice == Button.ID_DOWN) {
		//			Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
		//			robot.goDrive("left");
		//		}	
		//	}
		
	}

}
