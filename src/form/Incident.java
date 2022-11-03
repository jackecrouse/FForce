package form;

import java.util.Calendar;
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
		location = "";
		type = "";
	}
	
	public static int boolToInt(boolean bool) {
		if(bool) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	public static String dateToDayOfWeek(Date date) {
		Calendar dayOfWeek = Calendar.getInstance();
		dayOfWeek.setTime(date);
		switch(dayOfWeek.get(Calendar.DAY_OF_WEEK)) {
		case 0:
			return "Sunday";
		case 1:
			return "Monday";
		case 2:
			return "Tuesday";
		case 3:
			return "Wednesday";
		case 4:
			return "Thursday";
		case 5:
			return "Friday";
		case 6:
			return "Saturnday";
		default:
			throw new IllegalArgumentException();
		}
	}
	
}