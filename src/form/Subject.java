package form;

import java.util.Date;
import java.util.Vector;

public class Subject {
	
	public String firstName;
	public String middleName;
	public String lastName;
	public String sex;
	public String race;
	public Date dateOfBirth;
	public boolean wasInjured;
	public boolean wasKilled;
	public boolean wasWeaponed;
	public boolean wasArrested;
	public boolean hadMedicalTreatment;
	public String injuries;
	public String charges;
	public Vector<String> influence;
	public String otherInfluence;
	public Vector<String> actions;
	public String otherActions;
	public Vector<String> uofAgainst;
	public String otherUOF;
	public int numberOfShots;
	
	public Subject() {
		super();
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.sex = "";
		this.race = "";
		this.dateOfBirth = new Date();
		this.wasInjured = false;
		this.wasKilled = false;
		this.wasWeaponed = false;
		this.wasArrested = false;
		this.hadMedicalTreatment = false;
		this.injuries = "";
		this.charges = "";
		this.influence = new Vector<String>();
		this.otherInfluence = "";
		this.actions = new Vector<String>();
		this.otherActions = "";
		this.uofAgainst = new Vector<String>();
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
		this.dateOfBirth = new Date(System.currentTimeMillis());
		this.wasInjured = Boolean.getBoolean(args[6]);
		this.wasKilled = Boolean.getBoolean(args[7]);
		this.wasWeaponed = Boolean.getBoolean(args[8]);
		this.wasArrested = Boolean.getBoolean(args[9]);
		this.hadMedicalTreatment = Boolean.getBoolean(args[10]);
		this.injuries = args[11];
		this.charges = args[12];
		this.influence = new Vector<String>();
		this.otherInfluence = args[13];
		this.actions = new Vector<String>();
		this.otherActions = args[14];
		this.uofAgainst = new Vector<String>();
		this.otherUOF = args[15];
		this.numberOfShots = Integer.parseInt(args[16]);
	}
	
}