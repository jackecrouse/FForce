package form;

import java.util.Date;
import java.util.Vector;

public class Incident {
	
	public Officer officer;
	public Vector<Subject> subjects;
	public Date incidentDate;
	public String location;
	public String type;
	
	public Incident() {
		officer = new Officer();
		subjects = new Vector<Subject>();
		subjects.add(new Subject());
		incidentDate = new Date();
	}
	
}