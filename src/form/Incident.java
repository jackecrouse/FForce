package form;

import java.util.Date;
import java.util.Vector;

public class Incident {
	
	public Officer officer;
	public Vector<Subject> subjects;
	public int incidentNumber; // TODO: remove if unnecessary
	public Date incidentDate;
	public String location;
	public String type;
	public String otherType;
	public boolean hasOfficerSignature;
	public Date officerSignDate;
	public boolean hasSupervisorSignature;
	public Date supervisorSignatureDate;
	public boolean forceIsJustified;
	
	public Incident() {
		officer = new Officer();
		subjects = new Vector<Subject>();
		incidentNumber = 0; // TODO: remove if unnecessary
		subjects.add(new Subject());
		incidentDate = new Date();
		location = "";
		type = "";
		otherType = "";
		hasOfficerSignature = false;
		officerSignDate = new Date();
		hasSupervisorSignature = false;
		supervisorSignatureDate = new Date();
		forceIsJustified = false;
	}
	
}