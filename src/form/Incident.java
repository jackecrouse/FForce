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
	public boolean hasOfficerSignature;
	public Date officerSignDate;
	public boolean hasSupervisorSignature;
	public Date supervisorSignatureDate;
	public boolean forceIsJustified;
	
	public Incident() {
		officer = new Officer();
		subjects = new Vector<Subject>();
		subjects.add(new Subject());
		incidentDate = new Date();
		location = "";
		type = "";
		hasOfficerSignature = false;
		officerSignDate = new Date();
		hasSupervisorSignature = false;
		supervisorSignatureDate = new Date();
		forceIsJustified = false;
	}
	
	public static int boolToInt(boolean bool) {
		if(bool) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public static int dateToSeconds(Date date) {
		Calendar seconds = Calendar.getInstance();
		seconds.setTime(date);
		return seconds.get(Calendar.SECOND);
	}
	
	public static int dateToMinutes(Date date) {
		Calendar minutes = Calendar.getInstance();
		minutes.setTime(date);
		return minutes.get(Calendar.MINUTE);
	}
	
	public static int dateToHours(Date date) {
		Calendar hours = Calendar.getInstance();
		hours.setTime(date);
		return hours.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int dateToDayOfYear(Date date) {
		Calendar days = Calendar.getInstance();
		days.setTime(date);
		return days.get(Calendar.DAY_OF_YEAR);
	}
	
	public static String dateToMonth(Date date) {
		Calendar month = Calendar.getInstance();
		month.setTime(date);
		switch(month.get(Calendar.MONTH)) {
		case 0: return "January";
		case 1: return "Febuary";
		case 2:	return "March";
		case 3: return "April";
		case 4:	return "May";
		case 5:	return "June";
		case 6:	return "July";
		case 7:	return "August";
		case 8:	return "September";
		case 9: return "October";
		case 10: return "November";
		case 11: return "December";
		default: throw new IllegalArgumentException();
		}
	}
	
	public static int dateToYear(Date date) {
		Calendar year = Calendar.getInstance();
		year.setTime(date);
		return year.get(Calendar.YEAR);
	}
	
	
	public static String dateToDayOfWeek(Date date) {
		Calendar dayOfWeek = Calendar.getInstance();
		dayOfWeek.setTime(date);
		switch(dayOfWeek.get(Calendar.DAY_OF_WEEK)) {
		case 0: return "Sunday";
		case 1:	return "Monday";
		case 2:	return "Tuesday";
		case 3: return "Wednesday";
		case 4:	return "Thursday";
		case 5: return "Friday";
		case 6:	return "Saturnday";
		default: throw new IllegalArgumentException();
		}
	}
	
}