package connexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	Connection connection;

	public Connexion() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://P13B-100-LOIC:49172;databaseName=resto;user=sa;password=pass";
			connection = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String connectionUrl = "jdbc:sqlserver://P13B-100-LOIC:49172;databaseName=resto;user=sa;password=pass";
				connection = DriverManager.getConnection(connectionUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
