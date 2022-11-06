package form;

import java.util.Date;

public class Officer {

	public int badgeNumber;
	public String firstName;
	public String middleName;
	public String lastName;
	public String sex;
	public String race;
	public Date dateOfBirth;
	public Date serviceStart;
	public String rank;
	public String duty;
	public boolean wasInjured;
	public boolean wasKilled;
	public boolean wasOnDuty;
	public boolean wasUniformed;
	public boolean hadMedicalTreatment;
	public String injuries;
	
	public Officer() {
		badgeNumber = 0;
		firstName = "";
		middleName = "";
		lastName = "";
		sex = "";
		race = "";
		dateOfBirth = new Date();
		serviceStart = new Date();
		rank = "";
		duty = "";
		wasInjured = false;
		wasKilled = false;
		wasOnDuty = false;
		wasUniformed = false;
		hadMedicalTreatment = false;
		injuries = "";
	}
	
	//for testing
	public Officer(String [] args) {
		badgeNumber = Integer.parseInt(args[0]);
		firstName = args[1];
		middleName = args[2];
		lastName = args[3];
		sex = args[4];
		race = args[5];
		dateOfBirth = new Date(System.currentTimeMillis());
		serviceStart = new Date(System.currentTimeMillis());
		rank = args[8];
		duty = args[9];
		wasInjured = Boolean.getBoolean(args[10]);
		wasKilled = Boolean.getBoolean(args[11]);
		wasOnDuty = Boolean.getBoolean(args[12]);
		wasUniformed = Boolean.getBoolean(args[13]);
		hadMedicalTreatment = Boolean.getBoolean(args[14]);
		injuries = args[15];
	}
	
}