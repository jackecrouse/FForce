package database;

public class Form {
	boolean hasSectionC2 = false;
	
	//Section A fields
	String date;
	String time;
	String dayOfWeek;
	String location;
	String typeOfIncident;
	
	//Section B fields
	String officerName;
	int officerBadgeNumber;
	String officerRank;
	String dutyAssignment;
	int yearsOfService;
	String officerSex;
	String officerRace;
	int officerAge;
	int officerInjured;
	int officerKilled;
	int officerOnDuty;
	int officerHadUniform;
	
	//Section C1 Fields
	String subject1Name;
	int subject1HadWeapon;
	String subject1Sex;
	String subject1Race;
	int subject1Age;
	int subject1Injured;
	int subject1Killed;
	String subject1UnderInfluence;
	int subject1Arrested;
	String subject1Charges;
	String subject1Actions;
	String subject1Treatement;
	
	//Section C2 Fields
	String subject2Name;
	int subject2HadWeapon;
	String subject2Sex;
	String subject2Race;
	int subject2Age;
	int subject2Injured;
	int subject2Killed;
	String subject2UnderInfluence;
	int subject2Arrested;
	String subject2Charges;
	String subject2Actions;
	String subject2Treatement;
	
	//Section D Fields
	String officerInjuries;
	int officerHospitalized;
	String subjectInjuries;
	int subjectHospitalized;
	
	//Signature Fields
	String officerSignature;
	String officerSignDate;
	String supervisorSignature;
	String supervisorSignatureDate;
	int forceJustified;
	
	public Form(String[] inputs) {
		int length = inputs.length;
		if (length > 39 || length <= 0)
			hasSectionC2 = true;
		
		//Section A
		date = inputs[0];
		time = inputs[1];
		dayOfWeek = inputs[2];
		location = inputs[3];
		typeOfIncident = inputs [4];
		
		//Section B
		officerName = inputs [5];
		officerBadgeNumber = Integer.parseInt(inputs[6]);
		officerRank = inputs [7];
		dutyAssignment = inputs [8];
		yearsOfService = Integer.parseInt(inputs[9]);
		officerSex = inputs [10];
		officerRace = inputs [11];
		officerAge = Integer.parseInt(inputs[12]);
		officerInjured = Integer.parseInt(inputs[13]);
		officerKilled = Integer.parseInt(inputs[14]);
		officerOnDuty = Integer.parseInt(inputs[15]);
		officerHadUniform = Integer.parseInt(inputs[16]);
		
		//Section C1
		subject1Name = inputs[17];
		subject1HadWeapon = Integer.parseInt(inputs[18]);
		subject1Sex = inputs[19];
		subject1Race =inputs[20];
		subject1Age = Integer.parseInt(inputs[21]);
		subject1Injured = Integer.parseInt(inputs[22]);
		subject1Killed = Integer.parseInt(inputs[23]);
		subject1UnderInfluence= inputs[24];
		subject1Arrested = Integer.parseInt(inputs[25]);
		subject1Charges = inputs[26];
		subject1Actions = inputs[27];
		subject1Treatement = inputs[28];
		
		//Section C2 (Optional)
		if (hasSectionC2) {
			subject2Name = inputs[29];
			subject2HadWeapon = Integer.parseInt(inputs[30]);
			subject2Sex = inputs[31];
			subject2Race =inputs[32];
			subject2Age = Integer.parseInt(inputs[33]);
			subject2Injured = Integer.parseInt(inputs[34]);
			subject2Killed = Integer.parseInt(inputs[35]);
			subject2UnderInfluence= inputs[36];
			subject2Arrested = Integer.parseInt(inputs[37]);
			subject2Charges = inputs[38];
			subject2Actions = inputs[39];
			subject2Treatement = inputs[40];
		}
		
		//if C2 is filled out, all other indices are offset by 12.
		int offset = 0;
		if (hasSectionC2)
			offset = 12;
		
		//Section D
		officerInjuries = inputs[29 + offset];
		officerHospitalized = Integer.parseInt(inputs[30 + offset]);
		subjectInjuries = inputs[31 + offset];
		subjectHospitalized = Integer.parseInt(inputs[32 + offset]);
		
		//Signature Section
		officerSignature = inputs[33 + offset];
		officerSignDate = inputs[34 + offset];
		supervisorSignature = inputs[35 + offset];
		supervisorSignatureDate = inputs[36 + offset];
		forceJustified = Integer.parseInt(inputs[37 + offset]);
	}
	
	public Form() {
		date = "2022-11-02";
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
	}
	
	public boolean hasSectionC2() {
		if (hasSectionC2 == true) return true; 
		return false;
	}

}
