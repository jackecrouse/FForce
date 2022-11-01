package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * @author Johnathan Dewey
 * If you all want to test around with the database I have your "logins" for my persons table that contains 
 * only us and a test user:
 * 		Luke:
 * 			Username: lkvamme
 * 			Password: csc353-02
 * 		Jack:
 * 			Username: jcrouse
 * 			Password: csc353
 * 		Jake:
 * 			Username: shorj0
 * 			Password: csc353-03
 *
 */
public class Database {
	
	//creates a variable that references the connection link between Eclipse and the database
	protected static Connection _CON;
	//provides the url, user, and password for database connection
	protected static String _DBurl = "jdbc:mysql://csserver2016.fu.campus/FuPoForce";
	protected static String _DBuser = "jdewey";
	protected static String _DBpassword = "csc353";
	
	
	public static void main(String[] args) {

		//any code that contains SQL operations must have a try catch block
		try {
			//establishes connection to database
			Class.forName("com.mysql.jdbc.Driver");
			_CON = DriverManager.getConnection(_DBurl, _DBuser, _DBpassword);
			
			/*
			 * Now we can use SQL query language. Use pattern:
			 * 	String SQL_command = "";
			 * 	Statement commandAction = _CON.createStatement();
			 * 	ResultSet rs = commandAction.executeQuery(SQL_command);
			 * 	while (rs.next()){
			 * 		...
			 * 	}
			 */
			
			//simulates a login
			String username = "testuser";
			String password = "testPassword";
			isUser(username, password);
			
			//can change a password
			String newPassword = "newPassword";
			changePassword(username, password, newPassword);
			
			//change password back rq
			password = "newPassword";
			changePassword(username, newPassword, password);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean isUser(String username, String password) {
		try {
			ResultSet rs = getInformation(username, password);
			while (rs.next()) {
				System.out.println("Successsful log in for " + rs.getString("name"));
			}
			return true;
		}
		catch (Exception e) {
			System.out.println("Did not successfully log in");
			return false;
		}
		
	}
	
	public static ResultSet getInformation(String username, String password) {
		try {
			String SQL_command = "SELECT * FROM persons WHERE username='"+username+"' AND pass='"+password+"'";
			Statement isUser = _CON.createStatement();
			ResultSet rs = isUser.executeQuery(SQL_command);
			return rs;
		}
		catch (Exception e) {
			System.out.println("No rs output for getInformation()");
			return null;
		}
	}

	public static boolean changePassword(String username, String oldPassword, String newPassword) {
		try {
			isUser(username, oldPassword);
			//sets password
			String SQL_command = "UPDATE persons SET pass = '"+newPassword+"' WHERE username = '"+username+"' AND pass = '"+oldPassword+"'";
			Statement changePassword = _CON.createStatement();
			//Use execute() for void and boolean SQL statements, use executeQuery() for ResultSet statements
			changePassword.execute(SQL_command);
			
			//Relogin and see if new info is accurate
			ResultSet rs = getInformation(username,newPassword);
			while (rs.next()) {
				String retString = "Thank you " + rs.getString("name") + " your password has been successfully changed";
				System.out.println(retString);
			}
			
			return true;
		}
		catch (Exception e) {
			System.out.println("Did not change password");
			return false;
		}
	}

}
