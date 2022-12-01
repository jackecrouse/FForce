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
	public Incident(Officer officer, ArrayList<Subject> subjects, Date incidentDate, String location, String type, String otherType,
					boolean hasSupervisorSignature, Date supervisorSignDate, String supervisorFinding) {
		id = -1;
		this.officer = officer;
		this.subjects = subjects;
		this.incidentDate = incidentDate;
		this.location = location;
		this.type = type;
		this.hasSupervisorSignature = hasSupervisorSignature;
		this.supervisorSignDate = supervisorSignDate;
		this.supervisorFinding = supervisorFinding;
	}
	
}