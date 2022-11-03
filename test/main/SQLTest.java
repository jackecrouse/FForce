package main;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.SQL;

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
		assertTrue(connection.insertNewForm(C2));
		
		//Null Values [without C2] (Should Fail)
		
		//Null Values [with C2] (Should Fail)
	}
	
	@Test
	void testChangePassword() {
		//Passes
		
		//Fails
		
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
	
	

}
