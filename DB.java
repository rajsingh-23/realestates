package valorVistaEstates;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
	static Connection connection = null;
	
	public static Connection connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String userName = "root";
			String password = "root";
			String dataBaseName = "RealEstates";
			
			connection = DriverManager.getConnection(url + dataBaseName, userName, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
