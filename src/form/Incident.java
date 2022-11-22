package form;

import java.util.ArrayList;
import java.util.Date;

public class Incident {
	
	public int id;
	public Officer officer;
	public ArrayList<Subject> subjects;
	public Date incidentDate;
	public String location;
	public String type;
	public String otherType;
	public boolean hasSupervisorSignature;
	public Date supervisorSignDate;
	public String supervisorFinding;
	
	public Incident() {
		id = -1;
		officer = new Officer();
		subjects = new ArrayList<Subject>();
		subjects.add(new Subject());
		incidentDate = new Date();
		location = "";
		type = "";
		otherType = "";
		hasSupervisorSignature = false;
		supervisorSignDate = new Date();
		supervisorFinding = "";
	}
	
	//for testing
	public Incident(String [] incidentArgs, String [] officerArgs, String [] subjectArgs) {
		id = -1;
		officer = new Officer(officerArgs);
		subjects = new ArrayList<Subject>();
		subjects.add(new Subject(subjectArgs));
		incidentDate = new Date(System.currentTimeMillis());
		location = incidentArgs[1];
		type = incidentArgs[2];
		hasSupervisorSignature = Boolean.getBoolean(incidentArgs[3]);
		supervisorSignDate = new Date(System.currentTimeMillis());
		supervisorFinding = incidentArgs[5];
	}
	
}