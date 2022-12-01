package form;

import java.util.Date;

public class OfficerInfo {
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

	public OfficerInfo() {
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
	}
	
	// for testing
	public OfficerInfo(int badgeNumber, String firstName, String middleName, String lastName, String sex, String race, Date dateOfBirth, Date serviceStart,
					   String rank, String duty) {
		this.badgeNumber = badgeNumber;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.sex = sex;
		this.race = race;
		this.dateOfBirth = dateOfBirth;
		this.serviceStart = serviceStart;
		this.rank = rank;
		this.duty = duty;
	}
}