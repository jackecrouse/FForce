package main;

import static org.junit.jupiter.api.Assertions.*;

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
		
		//assertThrows(SQLException.class, ()->{ new SQL("notAUser", "notAPassword"); });
		
	}
	
	@Test
	void testInsertNewForm() {
		//NO ' IN INPUT
		String [] officerArgs  = {"21", "Johnathan", "Tyler", "Dewey", "Male", "White", "", "", "Private", "Patrol", "false", "false", "false", "false", "false", "None"};
		String [] subjectArgs  = {"Joe","Damn","Mama", "Other", "White", "", "false", "false", "false", "false", "false", "None", "None", "None","None","None","0"};
		String [] incidentArgs = {"", "Your Moms House", "Excessive Force", "false", "", "false", "", "false"};
		Incident incident = new Incident(incidentArgs,officerArgs,subjectArgs);
		
		try {
			SQL test = new SQL("jdewey", "csc353");
			assertTrue(test.insertNewForm(incident));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void testAddOfficer() {
		try {
			SQL testWithAdmin = new SQL("jdewey", "csc353");
			assertTrue(testWithAdmin.addOfficer("test", "test", "test", "test", 1, "test@furman.edu", 1));
			testWithAdmin.deleteUser("test");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			SQL testWithPlainJane = new SQL("jsmith", "jsmith123");
			assertFalse(testWithPlainJane.addOfficer("test", "test", "test", "test", 1, "test@furman.edu", 1));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testDeleteUser() {
		try {
			SQL testWithAdmin = new SQL("jdewey", "csc353");
			testWithAdmin.addOfficer("test", "test", "test", "test", 1, "test@furman.edu", 1);
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

}
