package javadb;

import java.util.Scanner;

public class DBKaart {
	Scanner input = new Scanner(System.in);
	
	private int idKaart;
	String kaartNaam;
	String bericht;


	public DBKaart(String kaartNaam) {
		super();
		this.kaartNaam = kaartNaam;
	}
	
	public DBKaart(int idKaart,String kaartNaam, String bericht) {
		super();
		this.idKaart = idKaart;
		this.kaartNaam = kaartNaam;
		this.bericht = bericht;
	}

	
	
    public int getIdKaart() {
		return idKaart;
	}

	public void setIdKaart(int idKaart) {
		this.idKaart = idKaart;
	}

	public String getKaartNaam() {
		return kaartNaam;
	}

	public void setKaartNaam(String kaartNaam) {
		this.kaartNaam = kaartNaam;
	}

	public String getBericht() {
		return bericht;
	}

	public void setBericht(String bericht) {
		this.bericht = bericht;
	}

	@Override
    public String toString() {
        return String.format("%s, %s",this.kaartNaam,this.bericht);
    }
	

	

}
