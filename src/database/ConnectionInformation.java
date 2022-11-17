package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionInformation {
	private static String _url = "oagf?h|vti?**fvv`ws`w7543+cp+fdhupv*CpUjCjwf`";
	private static String _u = "oa`r`|";
	private static String _p = "fvf606";
	
	public static Connection establishConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(Utilities.format(_url), Utilities.format(_u), Utilities.format(_p));
			System.out.println("connected");
			return con;
		}
		catch (Exception e) {
			System.out.println("Did not connect");
			return null;
		}
	}
}
