package database;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import form.Officer;
import form.Subject;

public class Utilities {

	public static String convertDate(Date date) {
		return String.format("%s-%s-%s", dateToYear(date), dateToMonth(date), dateToDayOfYear(date));
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
	
	public static String dateToDayOfYear(Date date) {
		Calendar days = Calendar.getInstance();
		days.setTime(date);
		int day = days.get(Calendar.DAY_OF_MONTH);
		
		if (day < 10)
			return ("0" + String.valueOf(day));
		return String.valueOf(day);
	}
	
	public static String dateToMonth(Date date) {
		Calendar month = Calendar.getInstance();
		month.setTime(date);
		switch(month.get(Calendar.MONTH)) {
		case 0: return "01";
		case 1: return "02";
		case 2:	return "03";
		case 3: return "04";
		case 4:	return "05";
		case 5:	return "06";
		case 6:	return "07";
		case 7:	return "08";
		case 8:	return "09";
		case 9: return "10";
		case 10: return "11";
		case 11: return "12";
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
		case 6:	return "Saturday";
		default: throw new IllegalArgumentException();
		}
}
}
