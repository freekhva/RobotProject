package javadb;



	import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;

import kleur_en_geluid.KleurNaarGeluid;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.remote.ev3.RemoteEV3;

//import basisoefeningen.Lcd;
//import lejos.hardware.Button;


	public class TestLauncher {
		Scanner input = new Scanner(System.in);

		private DBaccess db;
		private KaartDAO kaartdao;
		
//		 print alle kaarten
	
		

		

		public TestLauncher() {
			super();
			db = new DBaccess();
			kaartdao  = new KaartDAO(db);

		}

		public static void main(String[] args) {
			TestLauncher myself = new TestLauncher();
//			Lcd.print(1, "Druk om connectie met DB te maken");
//			Button.waitForAnyPress();
			myself.run();
			
			

			
		}

		private void run() {

			try {
				db.openConnection();
				System.out.println("Connection open");
			} catch (Exception e) {
				System.out.println("\nEr is iets fout gegaan\n");
				e.printStackTrace();
			}

//			Button.waitForAnyPress();
			
			
			// poging tot printen op ev3
			
			RemoteEV3 ev3 = null;
			try {
				ev3 = new RemoteEV3("10.0.1.1");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ev3.setDefault();
			Sound.beep();
			TextLCD display = ev3.getTextLCD();
			display.clear();

			List<DBKaart> kaartenlijst = kaartdao.getAllCards();
			
			display.drawString("MySQL DB Connectie open",0,1);
			
			
			// getTextWidth() toepassen om breedte van scherm te lezen, en deel van bericht string 
			
			for (int i = 0;i <2;i++) {
				// toon kaarten op ev3 console , 2e parameter = x positie, 3e parameter is y positie
			display.drawString(kaartenlijst.get(i).toString(),0,3+i);
			// toon kaarten in eclipse console
			System.out.println(kaartenlijst.get(i).toString());
			}
			
//			for (DBKaart kaart : kaartenlijst)
//				display.drawString(kaart.toString(),2,4);
			
			
//			ArrayList<DBKaart> kaartenlijst2 = new ArrayList<>();
//			
//			
//				
//				
//				
//				display.drawString(kaart[i].toString(),2,4);
				
//			}
				
			
			Button.waitForAnyPress();
		}

	}
