package kleur_en_geluid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class KleurNaarGeluidRGBDAO 
{
	private static String dbNaam = "kleurgeluid";
	private static String dbDirRobot = "/home/root/";
	// private static String dbDir = "/Users/guidoleen/Robot-C12-A/src/kleur_en_geluid/";
	public static String dbURL = "jdbc:derby:" + dbDirRobot + dbNaam; // + ";" // create=true"; 
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement prepstmt = null;
	    
	public KleurNaarGeluidRGBDAO()
	{
	}
	
	// Make the connection with db
	public void MakeConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		try 
		{
	        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			this.conn = DriverManager.getConnection(this.dbURL);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Select alle uit de db en stop ze in een arrayList....
	public ArrayList<KleurNaarGeluidRGB> selecAlleKaartentUitDB()
	{
		ArrayList<KleurNaarGeluidRGB> kleurenNoten = new ArrayList<>();
		ResultSet rs = null;
		String strSql = "SELECT * FROM kleurnoot";
		try 
		{
			this.prepstmt = this.conn.prepareStatement( strSql );
			rs = this.prepstmt.executeQuery();

			// Vul arrayList
			while( rs.next() )
			{

				kleurenNoten.add( new KleurNaarGeluidRGB(
						rs.getInt("rood"),
						rs.getInt("groen"),
						rs.getInt("blauw"),
						rs.getInt("frequentie")
						) );
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return kleurenNoten;
	}

}
