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
	public OfficerInfo(String[] args) {
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
	}
}