package Parcour;

public class ParcourLauncher {
	public static void main(String[] args) {
		OneSensor test1 = new OneSensor(75, 75, 1, 0, 0);
		test1.goCalibrate();
		test1.goDrive();
	}
}
