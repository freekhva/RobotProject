package Parcour;

public class ParcourLauncher {
	public static void main(String[] args) {
		// 30, 30, 1, 0, 0 = smooth
		// de KD? wat is het verschil
		
		OneSensor test1 = new OneSensor(40, 40, 1f, 0f, 5f);
		
		float one = test1.getKp();
		float two = test1.getKi();
		float three = test1.getKd();
		
		System.out.println("KP = " +one +"KI = " +two +"KD = " +three);
		test1.goCalibrate();
		test1.goDrive();
	}
}
