package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionInformation {
	private static String _DBurl = "jdbc:mysql://csserver2016.fu.campus/FuPoForce";
	private static String _DBuser = "jdewey";
	private static String _DBpassword = "csc353";
	
	public static Connection establishConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(_DBurl, _DBuser, _DBpassword);
			System.out.println("connected");
			return con;
		}
		catch (Exception e) {
			System.out.println("Did not connect");
			return null;
		}
	}
}
