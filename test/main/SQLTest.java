package main;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import database.SQL;
import form.Incident;

class SQLTest {
	
	
	@Test
	void testLogin() {
		try {
			SQL test = new SQL("jdewey", "csc353");
			assertTrue(test.isUser("jdewey","csc353"));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	@Test
	void testGetForm() {
		try {
			SQL normalTest = new SQL("jdewey", "csc353");
			int caseID = 19;
			int badgeNumber = 21;
			ResultSet rs = normalTest.getForm(caseID, badgeNumber);
			assertEquals(rs.getString("CaseID"), caseID);
			assertEquals(rs.getString("BadgeNumber"), badgeNumber);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddUser() {
		try {
			SQL testWithAdmin = new SQL("jdewey", "csc353");
			assertTrue(testWithAdmin.addUser("test", "test", "test", "test", 1, "test@furman.edu", 1));
			testWithAdmin.deleteUser("test");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			SQL testWithPlainJane = new SQL("jsmith", "jsmith123");
			assertFalse(testWithPlainJane.addUser("test", "test", "test", "test", 1, "test@furman.edu", 1));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetOfficer() {
		
	}
	
	@Test
	void testInsertNewForm() throws SQLException {
		String [] officerArgs  = {"21", "Johnathan", "Tyler", "Dewey", "Male", "White", "", "", "Private", "Patrol", "false", "false", "false", "false", "false", "A", "false", ""};
		String [] subjectArgs  = {"Joe","Damn","Mama", "Other", "White", "1", "false", "false", "false", "false", "false", "B", "C", "", "D", "", "E", "", "F", "0"};
		String [] incidentArgs = {"", "Your Moms House", "Excessive Force", "false", "", "G"};
		Incident incident = new Incident(incidentArgs,officerArgs,subjectArgs);
		SQL test = new SQL("jdewey", "csc353");
		try {
			assertTrue(test.insertNewForm(incident));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		test.deleteForm(test.getLastCaseID());
	}
	
	@Test
	void testDeleteUser() {
		try {
			SQL testWithAdmin = new SQL("jdewey", "csc353");
			testWithAdmin.addUser("test", "test", "test", "test", 1, "test@furman.edu", 1);
			assertTrue(testWithAdmin.deleteUser("test"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			SQL testWithPlainJane = new SQL("jsmith", "jsmith123");
			assertFalse(testWithPlainJane.deleteUser("test"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	void testIsUser() {
		
	}
}
