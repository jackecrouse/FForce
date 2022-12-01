package database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import form.Incident;
import form.Subject;

public class Utilities {
	
	public static String convertDate(Date date) {
		return String.format("%s-%s-%s", dateToYear(date), dateToMonth(date), dateToDayOfYear(date));
	}
	
	public static String convertTime(Date date) {
		return String.format("%s:%s:%s", String.format("%1$2s",dateToHours(date)).replace(" ", "0"),
										 String.format("%1$2s",dateToMinutes(date)).replace(" ", "0"),
										 String.format("%1$2s",dateToSeconds(date)).replace(" ", "0"));
	}
	
	public static String type(Incident incident) {
		StringBuilder influences = new StringBuilder();
		influences.append(incident.type);
		if(influences.toString().contains("Other")) {
			influences.append(", " + incident.otherType);
		}
		return influences.toString();
	}
	
	public static String influences(Subject subject) {
		StringBuilder influences = new StringBuilder();
		influences.append(subject.influence.toString().substring(1, subject.influence.toString().length()-1));
		if(influences.toString().contains("Other")) {
			influences.append(", " + subject.otherInfluence);
		}
		return influences.toString();
	}
	
	public static String actions(Subject subject) {
		StringBuilder actions = new StringBuilder();
		actions.append(subject.actions.toString().substring(1, subject.actions.toString().length()-1));
		if(actions.toString().contains("Other")) {
			actions.append(", " + subject.otherActions);
		}
		return actions.toString();
	}
	
	public static String UOF(Subject subject) {
		StringBuilder useOfForce = new StringBuilder();
		useOfForce.append(subject.uofAgainst.toString().substring(1, subject.uofAgainst.toString().length()-1));
		if(useOfForce.toString().contains("Other")) {
			useOfForce.append(", " + subject.otherUOF);
		}
		return useOfForce.toString();
	}
	
	public static int boolToInt(boolean bool) {
		if(bool) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public static boolean intToBool(int i) {
		if(i >= 0 && i <= 1) {
			return i == 1;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public static ArrayList<String> parseToArrayList(String input) {
		return new ArrayList<String>(Arrays.asList(input.split(",")));
		
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
	
	
	public static String format(String x)
	{		
		char[] charArr = (x.toCharArray());
		String result = "";
		for(char item: charArr)
		{
			item = (char) ((item ^ 5) >> 5);
			result+=item;
		}
		return result;
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
	
	public static Date stringToDate(String input) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(input);
		} catch (ParseException e) {
			throw new IllegalArgumentException();
		}
	}
	
}
