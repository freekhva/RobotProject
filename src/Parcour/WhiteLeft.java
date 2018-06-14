package Parcour;

import lejos.hardware.Button;

public class WhiteLeft {
	public static void main(String[] args) {
		System.out.println("Drive Left + White Skip");
		Button.waitForAnyPress();
		Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
		robot.goCalibrate();
		robot.goDriveLeft();
		
		
		// 30, 30, 1, 0, 0 = smooth
		// de KD? wat is het verschil
		
		
		// DRAFT CODE VOOR EEN MENU
		
		
		// boolean start = true;
		
//		Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
//		robot.goDrive("right");
		
		
//		if (start == true) {
			
//			System.out.println("Press button:");
//			System.out.println("UP for lefturning track");
//			System.out.println("DOWN for rightturning track");
//			System.out.println("PLUS MODE");
//			System.out.println("UP for lefturning track");
//			System.out.println("DOWN for rightturning track");
			
//			Button.waitForAnyPress();
//			Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
//			robot.goDrive("right");
//			
//			Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
//			robot.goCalibrate();
//			robot.goDrive("RIGHT");
//			
			
//			
//			int choice = Button.getButtons();
//			if (choice == Button.ID_RIGHT) {
//				Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
//				robot.goDrive("right");
//			} else if (choice == Button.ID_LEFT) {
//				Driveable robot = new OneSensor(40, 40, 1.2f, 0f, 5f);
//				robot.goDrive("left");
//			}  else if (choice == Button.ID_UP) {
//				Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
//				robot.goDrive("right");
//			}	else if (choice == Button.ID_DOWN) {
//				Driveable robot = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
//				robot.goDrive("left");
//			}	
//		}
//		
		
//		Driveable test1 = new OneSensorPlus(40, 40, 1.2f, 0f, 5f);
//		OneSensor test1 = new OneSensor(40, 40, 1f, 0f, 5f);
		
//		float one = test1.getKp();
//		float two = test1.getKi();
//		float three = test1.getKd();
		
//		System.out.println("KP = " +one +"KI = " +two +"KD = " +three);
//		test1.goDrivePlus();
//		test1.goCalibrate();
//		test1.goDrive();
//		
		
	}
}