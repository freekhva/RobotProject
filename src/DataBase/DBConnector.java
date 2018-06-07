package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mystic_T.Kaart;

public class DBConnector 
{
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	    
	public DBConnector()
	{
		
	}
	
	// Make the connection with db
	public void MakeConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try 
		{
	        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			this.conn = DriverManager.getConnection(DBConnectionVAR.dbURL);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Create Table(s) // Eénmalig en is al klaar....
	public void createTable()
	{
    	String strSql = "CREATE TABLE kaart(rood INT NOT NULL, "
    			+ "groen INT NOT NULL, "
    			+ "blauw INT NOT NULL, "
    			+ "naamkaart VARCHAR(128), "
    			+ "omschrijving VARCHAR(128), "
    			+ "betekenis VARCHAR(128), "
    			+ "waarde INT NOT NULL, "
    			+ "PRIMARY KEY(rood, groen, blauw)"
    			+ ")";
    	try 
    	{
			this.stmt = this.conn.createStatement();
			this.stmt.execute( strSql );
			
			System.out.println("Tabel aangemaakt....");
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
	}
	
	// Insert into nieuwe kaart in de DataBase
	public void insertKaartInTable(int _rood, int _groen, int _blauw, String _naamkaart, String _omschrijving, String _betekenis, int _waarde)
	{
    	String strSql = "INSERT INTO kaart(rood, groen, blauw, naamkaart, omschrijving, betekenis, waarde) VALUES (?,?,?,?,?,?,?)";
    	try 
    	{
			this.prepstmt = conn.prepareStatement( strSql );
			this.prepstmt.setInt(1, _rood);
			this.prepstmt.setInt(2, _groen);
			this.prepstmt.setInt(3, _blauw);
			this.prepstmt.setString(4, _naamkaart);
			this.prepstmt.setString(5, _omschrijving);
			this.prepstmt.setString(6, _betekenis);
			this.prepstmt.setInt(7, _waarde);
			
				this.prepstmt.executeUpdate();
			
			System.out.println("Kaart toegevoegd....");
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
		
	}
	
	// Update Bestaande kaart in de DataBase
	public void updateKaartInTable(int _rood, int _groen, int _blauw, String _naamkaart, String _omschrijving, String _betekenis, int _waarde)
	{
		String strSql = "UPDATE kaart SET naamkaart = ?, omschrijving = ?, betekenis = ?, waarde = ? WHERE rood = ? AND groen = ? AND blauw = ?";
		try 
		{
			this.prepstmt = conn.prepareStatement( strSql );

			this.prepstmt.setString(1, _naamkaart);
			this.prepstmt.setString(2, _omschrijving);
			this.prepstmt.setString(3, _betekenis);
			this.prepstmt.setInt(4, _waarde);
			
			this.prepstmt.setInt(5, _rood);
			this.prepstmt.setInt(6, _groen);
			this.prepstmt.setInt(7, _blauw);

			this.prepstmt.executeUpdate();

			System.out.println("Kaart toegevoegd....");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	
	// Select alle uit de db en stop ze in een arrayList....
	public ArrayList<Kaart> selecAlleKaartentUitDB()
	{
		ArrayList<Kaart> kaarten = new ArrayList<>();
		ResultSet rs = null;
		String strSql = "SELECT * FROM kaart";
    	try 
    	{
			this.prepstmt = this.conn.prepareStatement( strSql );
			rs = this.prepstmt.executeQuery();
			
			// Vul arrayList
			while( rs.next() )
			{
				
				kaarten.add( new Kaart(rs.getString("naamkaart"), 
						rs.getInt("rood"),
						rs.getInt("groen"),
						rs.getInt("blauw")
						) );
			}
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    	return kaarten;
	}
	
	// Select alle uit de db
	public Kaart selecKaarttUitDB(int _rood, int _groen, int _blauw)
	{
		Kaart kaart = null;
		String strSql = "SELECT * FROM kaart WHERE rood = ? AND groen = ? AND blauw = ?";
		try 
		{
			this.prepstmt = this.conn.prepareStatement( strSql );
			this.prepstmt.setInt(1, _rood);
			this.prepstmt.setInt(2, _groen);
			this.prepstmt.setInt(3, _blauw);
			
			ResultSet rs = this.prepstmt.executeQuery();
			
			while( rs.next() )
			{
				  System.out.println( "Rood : " + rs.getInt("rood") );
				  System.out.println( "groen : " + rs.getInt("groen") );
				  System.out.println( "blauw : " + rs.getInt("blauw") );
				  System.out.println( "NaamKaart: " + rs.getString("naamkaart") );
				  System.out.println( "Omschrjving : " + rs.getString("omschrijving") );
				  System.out.println( "Betekenis : " + rs.getString("betekenis") );
				  System.out.println( "Waarde : " + rs.getInt("waarde") );
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return kaart;
	}
}

///// OLD STUFF
//System.out.println( "Rood : " + rs.getInt("rood") );
//System.out.println( "groen : " + rs.getInt("groen") );
//System.out.println( "blauw : " + rs.getInt("blauw") );
//System.out.println( "NaamKaart: " +  getString("naamkaart"));
//System.out.println( "Omschrjving : " + rs.getString("omschrijving") );
//System.out.println( "Betekenis : " + rs.getString("betekenis") );
//System.out.println( "Waarde : " + rs.getInt("waarde") );
