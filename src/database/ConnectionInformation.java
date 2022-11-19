package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionInformation {
	private static String _url = "൅ಅ౅౥݅ඥ༥๥ลඅ݅ץץ౥๥๥ಥๅ໅ಥๅم؅إۅׅ೅ລׅ౥థඥฅລ๥ץࣅລਅ෥ࣅ෥ๅ౥ಥ";
	private static String _u = "൅ಅಥ໥ಥ༥";
	private static String _p = "౥๥౥٥ڥ٥";
	
	public static String get_url() {
		return _url;
	}
	public static String get_u() {
		return _u;
	}
	public static String get_p() {
		return _p;
	}
	
	public static Connection establishConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(Utilities.format(_url), Utilities.format(_u), Utilities.format(_p));
			return con;
		}
		catch (Exception e) {
			return null;
		}
	}
}
