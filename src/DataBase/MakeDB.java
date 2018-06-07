package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeDB {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		DBConnector tarotDB = new DBConnector();
		tarotDB.MakeConnection();
		// tarotDB.createTable();
		
		// tarotDB.insertKaartInTable(00, 00, 01, "Kaart 2", "Kaart twee", "Goed", 0);
		// ResultSet rs = tarotDB.selectUitDB();
		// tarotDB.updateKaartInTable(00, 00, 00, "Kaart 1", "Kaart is een leuke kaart", "Goed", -1);
		tarotDB.selecKaarttUitDB(00, 00, 01);
	}
}
