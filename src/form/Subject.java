package form;

import java.util.ArrayList;

public class Subject {
	
	public String firstName;
	public String middleName;
	public String lastName;
	public String sex;
	public String race;
	public int age;
	public boolean wasInjured;
	public boolean wasKilled;
	public boolean wasWeaponed;
	public boolean wasArrested;
	public boolean hadMedicalTreatment;
	public String injuries;
	public String charges;
	public ArrayList<String> influence;
	public String otherInfluence;
	public ArrayList<String> actions;
	public String otherActions;
	public ArrayList<String> uofAgainst;
	public String otherUOF;
	public int numberOfShots;
	
	public Subject() {
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.sex = "";
		this.race = "";
		this.age = 0;
		this.wasInjured = false;
		this.wasKilled = false;
		this.wasWeaponed = false;
		this.wasArrested = false;
		this.hadMedicalTreatment = false;
		this.injuries = "";
		this.charges = "";
		this.influence = new ArrayList<String>();
		this.otherInfluence = "";
		this.actions = new ArrayList<String>();
		this.otherActions = "";
		this.uofAgainst = new ArrayList<String>();
		this.otherUOF = "";
		this.numberOfShots = 0;
	}
	
	//for testing
	public Subject(String firstName, String middleName, String lastName, String sex, String race, int age, boolean wasInjured, boolean wasKilled,
				   boolean wasWeaponed, boolean wasArrested, boolean hadMedicalTreatment, String injuries, String charges, ArrayList<String> influence,
				   String otherInfluence, ArrayList<String> actions, String otherActions, ArrayList<String> uofAgainst, String otherUOF, int numberOfShots) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.sex = sex;
		this.race = race;
		this.age = age;
		this.wasInjured = wasInjured;
		this.wasKilled = wasKilled;
		this.wasWeaponed = wasWeaponed;
		this.wasArrested = wasArrested;
		this.hadMedicalTreatment = hadMedicalTreatment;
		this.injuries = injuries;
		this.charges = charges;
		this.influence = influence;
		this.otherInfluence = otherInfluence;
		this.actions = actions;
		this.otherActions = otherActions;
		this.uofAgainst = uofAgainst;
		this.otherUOF = otherUOF;
		this.numberOfShots = numberOfShots;
	}
	
}