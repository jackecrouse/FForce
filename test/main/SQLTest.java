package main;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;

import database.SQL;
import form.Incident;
import form.Officer;
import form.OfficerInfo;
import form.Subject;

class SQLTest {
	
	
	@Test
	void testIsUser() {
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
	void testInsertNewForm() {
		
		OfficerInfo info = new OfficerInfo(21, "Johnathan", "Tyler", "Dewey", "Male", "White", new Date(), new Date(), "Private", "Patrol");
		
		Officer officer = new Officer(info, false, false, false, false, false, "A", false, new Date());
		
		ArrayList<String> influence = new ArrayList<String>();
		influence.addAll(0, Arrays.asList(""));
		ArrayList<String> actions = new ArrayList<String>();
		actions.addAll(0, Arrays.asList(""));
		ArrayList<String> uofAgainst = new ArrayList<String>();
		uofAgainst.addAll(0, Arrays.asList(""));
		Subject subject = new Subject("John", "Alan", "Doe", "Other", "White", 1, false, false, false, false, false, "B", "C", influence, "D", actions, "E", uofAgainst, "F", 0);
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		subjects.add(subject);
		Incident incident = new Incident(officer, subjects, new Date(), "Your Moms House", "Excessive Force", "", false, new Date(), "G");
		
		try {
			SQL test = new SQL("jdewey", "csc353");
			assertTrue(test.insertNewForm(incident));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
}
