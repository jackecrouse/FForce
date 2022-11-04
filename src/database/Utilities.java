package database;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import form.Incident;
import form.Officer;
import form.Subject;

public class Utilities {

	public static String convertDate(Date date) {
		return String.format("%d-%d-%d", dateToYear(date), dateToMonth(date), dateToDayOfMonth(date));
	}
	
	public static String convertTime(Date date) {
		return String.format("%d:%d:%d", dateToHours(date), dateToMinutes(date), dateToSeconds(date));
	}
	
	public static int yearDelta(Date date) {
		return Year.now().getValue() - dateToYear(date);
	}
	
	public static String getName(Subject subject) {
		return String.format("%s, %s, %s", subject.lastName, subject.firstName, subject.middleName);
	}
	
	public static String getName(Officer officer) {
		return String.format("%s, %s, %s", officer.lastName, officer.firstName, officer.middleName);
	}
	
	public static String types(Incident incident) {
		return incident.type.toString() + " " + incident.otherType;
	}
	
	public static String influences(Subject subject) {
		return subject.influence.toString() + " " + subject.otherInfluence;
	}
	
	public static String actions(Subject subject) {
		return subject.actions.toString() + " " + subject.otherActions;
	}
	
	public static String UOF(Subject subject) {
		return subject.uofAgainst.toString() + " " + subject.otherUOF;
	}
	
	public static int boolToInt(boolean bool) {
		if(bool) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
	//Calendar
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
	
	public static int dateToDayOfMonth(Date date) {
		Calendar days = Calendar.getInstance();
		days.setTime(date);
		return days.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int dateToMonth(Date date) {
		Calendar month = Calendar.getInstance();
		month.setTime(date);
		return month.get(Calendar.MONTH) + 1; // Calendar.JANUARY == 0
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
		case 6:	return "Saturday";
		default: throw new IllegalArgumentException();
		}
}
}