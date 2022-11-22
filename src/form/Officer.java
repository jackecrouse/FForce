package form;

import java.util.Date;

public class Officer {

	public OfficerInfo info = new OfficerInfo();
	public boolean wasInjured;
	public boolean wasKilled;
	public boolean wasOnDuty;
	public boolean wasUniformed;
	public boolean hadMedicalTreatment;
	public String injuries;
	public boolean hasSigniture;
	public Date signDate;
	
	public Officer() {
		info = new OfficerInfo();
		wasInjured = false;
		wasKilled = false;
		wasOnDuty = false;
		wasUniformed = false;
		hadMedicalTreatment = false;
		injuries = "";
		hasSigniture = false;
		signDate = new Date();
	}
	
	//for testing
	public Officer(String [] args) {
		info = new OfficerInfo(args);
		wasInjured = Boolean.getBoolean(args[10]);
		wasKilled = Boolean.getBoolean(args[11]);
		wasOnDuty = Boolean.getBoolean(args[12]);
		wasUniformed = Boolean.getBoolean(args[13]);
		hadMedicalTreatment = Boolean.getBoolean(args[14]);
		injuries = args[15];
		hasSigniture = Boolean.parseBoolean(args[16]);
		signDate = new Date(System.currentTimeMillis());
	}
	
}