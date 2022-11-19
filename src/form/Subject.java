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
		super();
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
	public Subject(String [] args) {
		super();
		this.firstName = args[0];
		this.middleName = args[1];
		this.lastName = args[2];
		this.sex = args[3];
		this.race = args[4];
		this.age = Integer.parseInt(args[5]);
		this.wasInjured = Boolean.getBoolean(args[6]);
		this.wasKilled = Boolean.getBoolean(args[7]);
		this.wasWeaponed = Boolean.getBoolean(args[8]);
		this.wasArrested = Boolean.getBoolean(args[9]);
		this.hadMedicalTreatment = Boolean.getBoolean(args[10]);
		this.injuries = args[11];
		this.charges = args[12];
		this.influence = new ArrayList<String>();
		this.otherInfluence = args[14];
		this.actions = new ArrayList<String>();
		this.otherActions = args[16];
		this.uofAgainst = new ArrayList<String>();
		this.otherUOF = args[18];
		this.numberOfShots = Integer.parseInt(args[19]);
	}
	
}