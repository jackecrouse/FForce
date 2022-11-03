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
	
}