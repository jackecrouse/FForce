package form;

import java.util.Date;

public class Officer {

	public OfficerInfo info;
	public boolean wasInjured;
	public boolean wasKilled;
	public boolean wasOnDuty;
	public boolean wasUniformed;
	public boolean hadMedicalTreatment;
	public String injuries;
	public boolean hasSignature;
	public Date signDate;
	
	public Officer() {
		info = new OfficerInfo();
		wasInjured = false;
		wasKilled = false;
		wasOnDuty = false;
		wasUniformed = false;
		hadMedicalTreatment = false;
		injuries = "";
		hasSignature = false;
		signDate = new Date();
	}
	
	//for testing
	public Officer(OfficerInfo info, boolean wasInjured, boolean wasKilled, boolean wasOnDuty, boolean wasUniformed, boolean hadMedicalTreatment,
				   String injuries, boolean hasSigniture, Date signDate) {
		this.info = info;
		this.wasInjured = wasInjured;
		this.wasKilled = wasKilled;
		this.wasOnDuty = wasOnDuty;
		this.wasUniformed = wasUniformed;
		this.hadMedicalTreatment = hadMedicalTreatment;
		this.injuries = injuries;
		this.hasSignature = hasSigniture;
		this.signDate = signDate;
	}
	
}