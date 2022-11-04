package main;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import database.SQL;

class SQLTest {
	
	@Test
	void testInsetNewForm() {
		//Without Section C2
		String [] noC2 = {"2022-11-02","17:00:00","Wednesday","ITSC","Excessive Force",
						  "John Smith","21","Private","Patrol","10", "Male","White","28","0","0","1","1",
						  "Jane Doe","0","Female","White", "19","0","1","None","0","None","None","None",
						  "None","0","None","0",
						  "Signed","2022-11-02","Signed","2022-11-02", "0"};

		SQL connection = new SQL();
		assertTrue(connection.insertNewForm(noC2));
		
		//With Section C2
		String [] C2 = {"2022-11-02","17:00:00","Wednesday","ITSC","Excessive Force",
						"John Smith","21","Private","Patrol","10", "Male","White","28","0","0","1","1",
						"Jane Doe","0","Female","White", "19","0","1","None","0","None","None","None",
						"Jane Doe","0","Female","White", "19","0","1","None","0","None","None","None",
						"None","0","None","0",
						"Signed","2022-11-02","Signed","2022-11-02", "0"};
		
		connection = new SQL();
		//assertTrue(connection.insertNewForm(C2));
		
		//Null Values [without C2] (Should Fail)
		
		//Null Values [with C2] (Should Fail)
	}
	
	@Test
	void testChangePassword() {
		//Passes
		String username = "jdewey";
		String password = "csc353";
		//SQL connection = new SQL(username, password);
		//Fails
		
	}
	
	@Test 
	void logIn() {
		
	}
	
	@Test
	void testIsUser() {
		//Passes
		
		//Fails
		
	}
	
	@Test
	void testDeleteForm() {
		int CASE_NUMBER = 1;
		SQL connection = new SQL();
		assertTrue(connection.deleteForm(CASE_NUMBER));
		//Passes
		
		//Fails
	}
	
	
/**
 * date = "2022-11-02";
		time = "13:00:00";
		dayOfWeek = "Wednesday";
		location = "RH-202";
		typeOfIncident = "Excessive Force";
		
		officerName = "A cop";
		officerBadgeNumber = 22;
		officerRank = "Private";
		dutyAssignment = "Patrol";
		yearsOfService = 1;
		officerSex = "Female";
		officerRace = "White";
		officerAge = 22;
		officerInjured = 0;
		officerKilled = 0;
		officerOnDuty = 1;
		officerHadUniform = 1;
		
		subject1Name = "A subject";
		subject1HadWeapon = 0;
		subject1Sex = "Female";
		subject1Race = "White";
		subject1Age = 22;
		subject1Injured = 0;
		subject1Killed = 0;
		subject1UnderInfluence = "Mental Illness";
		subject1Arrested = 0;
		subject1Charges = "None";
		subject1Actions = "None";
		subject1Treatement = "None";
		
		officerInjuries = "None";
		officerHospitalized = 0;
		subjectInjuries = "None";
		subjectHospitalized = 0;
		
		officerSignature = "Signed";
		officerSignDate = "2022-11-02";
		supervisorSignature = "Signed";
		supervisorSignatureDate = "2022-11-02";
		forceJustified = 1;
 */
}
