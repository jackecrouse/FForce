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
		dateOfBirth = new Date();
		serviceStart = new Date();
	}
	
}