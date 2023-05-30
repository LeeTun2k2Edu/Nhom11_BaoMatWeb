//
//package Connection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class ConnectJDBC {
//	private final String serverName = "LAPTOP-RE3ABHT6";
//	private final String dbName = "BookStore";
//	private final String portNumber = "1433";
//	private final String instance = " ";
//	private final String userID = "sa";
//	private final String password = "123456";
//
//		public Connection getConnection() throws Exception {
//			String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName+";encrypt=true;trustServerCertificate=true;";
//			if (instance == null || instance.trim().isEmpty())
//				url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName+";encrypt=true;trustServerCertificate=true;";
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			return DriverManager.getConnection(url, userID, password);
//		}
//
//		public static void main(String[] args) {
//			try {
//			System.out.println(new ConnectJDBC().getConnection());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}



package Connection;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectJDBC {
	private final String serverName = "DESKTOP-C5UBOF2";
	private final String dbName = "BookStore";
	private final String portNumber = "1433";
	private final String instance = "SQLEXPRESS";
	private final String userID = "sa";
	private final String password = "geniuslynkha";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}

	public Statement createStatement() {
		return null;
	}
	public static void main(String[] args) {
		try {
			System.out.println(new ConnectJDBC().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

