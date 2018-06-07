package javadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class KaartDAO {

	private DBaccess db;

	public KaartDAO(DBaccess db) {
		super();
		this.db = db;
	}

//	public void storeRecept(Recept recept) {
//		// Deze methode moet worden aangevuld. Je hoeft geen rekening te houden met
//		// autonummering.
//		String sql = "insert into Recept(receptnaam) values(?)";
//		try {
//			PreparedStatement ps = db.getStatementWithKey(sql);
//			// id niet nodig vanwege autoincrement
//			ps.setString(1, recept.getReceptNaam());
//
//			db.executeUpdatePreparedStatement(ps);
//			
//			ResultSet rs = ps.getGeneratedKeys(); // geeft resultset terug
//			int id = 0;
//			// lees resultaat waar ID in staat
//			while (rs.next()) {
//				id = rs.getInt(1); // uitlezen op basis van kolomindex, er is geen naam in de resultset
//			}
//
//			recept.setIdRecept(id);  
//			
//		} catch (SQLException e) {
//			System.out.println("SQL error " + e.getMessage());
//		}
//	}
	
	 public List<DBKaart> getAllCards() {
		 // Deze methode haalt alle medewerkers van een bepaald land uit de
//		 database.Vul
//		 // deze methode aan.
//		 // Vervolgens maakt de methode van alle medewerkers een object Official en
//		 voegt
		 // die toe aan de lijst.
		
		 String sql = "SELECT * FROM kaarten;";
		 List<DBKaart> resultlist = new ArrayList<DBKaart>();
		 try {
		 PreparedStatement ps = db.getStatement(sql);
		 ResultSet rs = db.executeSelectPreparedStatement(ps);
		 while (rs.next()) {
		 int id = rs.getInt("kaartid");
		 String name = rs.getString("kaartnaam");
		 String bericht = rs.getString("bericht");
		 
		 DBKaart resultElement = new DBKaart(id,name,bericht);
		 resultlist.add(resultElement);
		
		 }
		 } catch (SQLException e) {
		 System.out.println("SQL error " + e.getMessage());
		 }
		 return resultlist;
		 }
	 
	 
////	 public List<Artikel> getArtikelenPerReceptId(int inputId) {
//		 // Deze methode haalt alle medewerkers van een bepaald land uit de
////		 database.Vul
////		 // deze methode aan.
////		 // Vervolgens maakt de methode van alle medewerkers een object Official en
////		 voegt
//		 // die toe aan de lijst.
//		 
//		
//
//		
//		 String sql = "SELECT * FROM recept JOIN recept_has_artikel ON receptid = Recept_receptid JOIN artikel ON Artikel_artikelid = artikelid JOIN vindplaats ON vindplaatsid = Vindplaats_vindplaatsid WHERE receptid = " + inputId ;
//		 List<Artikel> resultlist = new ArrayList<Artikel>();
//		 try {
//		 PreparedStatement ps = db.getStatement(sql);
//		 ResultSet rs = db.executeSelectPreparedStatement(ps);
//		 while (rs.next()) {
//		 String artikelnaam = rs.getString("artikelnaam");
//		 int hoeveelheidPerUnit = rs.getInt("hoeveelheidinrecept");
//		 String eenheid = rs.getString("eenheid");
//		 String vindplaats = rs.getString("vindplaatsnaam");
//		 double unitPrijs = rs.getDouble("unitprijs");
//		 
//		 Artikel resultElement = new Artikel(artikelnaam,hoeveelheidPerUnit,eenheid,vindplaats,unitPrijs);
//		 resultlist.add(resultElement);
//		
//		 }
//		 } catch (SQLException e) {
//		 System.out.println("SQL error " + e.getMessage());
//		 }
//		 return resultlist;
//		 }
//	 
//	 


	

}
