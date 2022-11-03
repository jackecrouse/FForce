package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import form.Incident;
import form.Officer;
import form.Subject;

public class SQL {
	
		//connection information
		protected static Connection _CON;
		protected static String _DBurl = "jdbc:mysql://csserver2016.fu.campus/FuPoForce";
		protected static String _DBuser = "lkvamme";
		protected static String _DBpassword = "csc353";
		
		//Blocks of information for SQL commands, such as row and value formats
		protected static final String ROW_FORMAT = "(Date, Time, Day, Location, IncidentType, "
												 + "OfficerName, BadgeNumber, Rank, DutyAssignment, "
												 + "YearsOfService, OfficerSex, OfficerRace, OfficerAge, "
												 + "OfficerInjured, OfficerKilled, OfficerOnDuty, OfficerHadUniform, "
												 + "Subject1Name, Subject1Sex, Subject1Race, Subject1Age, Subject1HadWeapon, "
												 + "Subject1Injured, Subject1Killed, Subject1InfluencedBy, Subject1Charges, Subject1Actions, "
												 + "Subject1Treatment, InjuriesToOfficer, HospitalizedOfficer, InjuriesToSubject, "
												 + "HospitalizedSubject, OfficerSign, OfficerSignDate, SupervisorSign, "
												 + "SupervisorSignDate, ForceJustified)";
		protected static final String ROW_FORMAT_SUBJECT2 = "(Date, Time, Day, Location, IncidentType, "
												 + "OfficerName, BadgeNumber, Rank, DutyAssignment, "
												 + "YearsOfService, OfficerSex, OfficerRace, OfficerAge, "
												 + "OfficerInjured, OfficerKilled, OfficerOnDuty, OfficerHadUniform, "
												 + "Subject1Name, Subject1Sex, Subject1Race, Subject1Age, Subject1HadWeapon, "
												 + "Subject1Injured, Subject1Killed, Subject1InfluencedBy, Subject1Charges, Subject1Actions, "
												 + "Subject1Treatment, "
												 + "Subject2Name, Subject2Sex, Subject2Race, Subject2Age, Subject2HadWeapon, "
												 + "Subject2Injured, Subject2Killed, Subject2InfluencedBy, Subject2Charges, Subject2Actions, "
												 + "Subject2Treatment, "
												 + "InjuriesToOfficer, HospitalizedOfficer, InjuriesToSubject, HospitalizedSubject, OfficerSign, "
												 + "OfficerSignDate, SupervisorSign, SupervisorSignDate, ForceJustified)";
		protected static final String VALUES_FORMAT = "('%s','%s','%s','%s','%s','%s',%d,'%s','%s',%d,'%s','%s', %d, %d, %d, %d, %d,"
												 + " '%s', '%s', '%s', %d, %d, %d, %d, '%s', '%s', '%s', '%s',"
												 + " '%s', %d, '%s', %d, '%s', '%s', '%s', '%s', %d)";
		
		protected static final String VALUES_FORMAT_SUBJECT2 = "('%s','%s','%s','%s','%s','%s',%d,'%s','%s',%d,'%s','%s', %d, %d, %d, %d, %d,"
												 + " '%s', '%s', '%s', %d, %d, %d, %d, '%s', '%s', '%s', '%s',"
												 + " '%s', '%s', '%s', %d, %d, %d, %d, '%s', '%s', '%s', '%s',"
												 + " '%s', %d, '%s', %d, '%s', '%s', '%s', '%s', %d)";
		

		
	public SQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			_CON = DriverManager.getConnection(_DBurl, _DBuser, _DBpassword);
		}
		catch (Exception e) {
			
		}
	}
	public boolean addOfficer() {
		String SQL_Command = String.format("INSERT INTO userInfo VALUES('%s', '%s', '%s', '%s', '%d', '%s', '%s')", null);
		try {
			Statement addOfficer = _CON.createStatement();
			addOfficer.execute(SQL_Command);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	public boolean insertNewForm(Incident incident) {
		
		String SQL_Command = getSQL_InsertForm(incident);
		
		try {
			Statement insertIntoForms = _CON.createStatement();
			insertIntoForms.execute(SQL_Command);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteForm(int caseID) {
		String SQL_Command = String.format("DELETE FROM forms WHERE caseID = %d", caseID);
		try {
			Statement deleteForm = _CON.createStatement();
			deleteForm.execute(SQL_Command);
			return true;
		}
		catch (Exception e){
			return false;
			
		}
	}
	
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		
		String updatePasswordCommand = "UPDATE persons SET pass = '%s' WHERE username = '%s' AND pass = '%s'";
		String SQL_command = String.format(updatePasswordCommand, newPassword, username, oldPassword);
		
		try {
			Statement changePassword = _CON.createStatement();
			changePassword.execute(SQL_command);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public boolean isUser(String username, String password) {
		
		try {
			ResultSet rs = getInformation(username, password);
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	private ResultSet getInformation(String username, String password) {
		String getInformationCommand = "SELECT * FROM persons WHERE username='%s' AND password='%s'";
		String SQL_command = String.format(getInformationCommand, username, password);
		
		try {
			Statement getInformation = _CON.createStatement();
			ResultSet rs = getInformation.executeQuery(SQL_command);
			return rs;
		}
		
		catch (Exception e) {
			return null;
		}
	}

	private String getSQL_InsertForm(Incident incident) {
		String insertIntoFormsCommand; 
		String SQL_Command;
		
		String valueFormat = "('%s','%s','%s','%s','%s','%s',%d,'%s','%s',%d,'%s','%s', %d, %d, %d, %d, %d,"
				 				+ " '%s', '%s', '%s', %d, %d, %d, %d, '%s', '%s', '%s', '%s',";
		
		String rowFormat = "(Date, Time, Day, Location, IncidentType, "
				 + "OfficerName, BadgeNumber, Rank, DutyAssignment, "
				 + "YearsOfService, OfficerSex, OfficerRace, OfficerAge, "
				 + "OfficerInjured, OfficerKilled, OfficerOnDuty, OfficerHadUniform, "
				 + "Subject1Name, Subject1Sex, Subject1Race, Subject1Age, Subject1HadWeapon, "
				 + "Subject1Injured, Subject1Killed, Subject1InfluencedBy, Subject1Charges, Subject1Actions, Subject1Treatment, ";
		
		if (incident.subjects.size() > 1) {
			for (int i = 1; i <= incident.subjects.size(); i++) {
				rowFormat += String.format("Subject%dName, Subject%dSex, Subject%dRace, Subject%dAge, Subject%dHadWeapon, Subject%dInjured, Subject%dKilled, Subject%dInfluencedBy, Subject%dCharges, Subject%dActions, Subject%dTreatment, ", i+1);
				valueFormat += " '%s', '%s', '%s', %d, %d, %d, %d, '%s', '%s', '%s', '%s',";
			}
		}
			
		rowFormat += " InjuriesToOfficer, HospitalizedOfficer, InjuriesToSubject, "
				 + "HospitalizedSubject, OfficerSign, OfficerSignDate, SupervisorSign, "
				 + "SupervisorSignDate, ForceJustified)";
		
		valueFormat += " '%s', %d, '%s', %d, '%s', '%s', '%s', '%s', %d)";
		
		insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s", rowFormat, valueFormat);
		
		Officer officer = incident.officer;
		
		String date = "";
		String time = "";
		String dayOfWeek = "";
		String officerName = String.format("%s, %s, %s", incident.officer.lastName, incident.officer.firstName, incident.officer.middleName);
		@SuppressWarnings("deprecation")
		int yearsOfService = 0;//incident.officer.serviceStart.getYear()-Date.;
		int officerAge = 0;
		
		Subject subject1 = incident.subjects.get(0);
		String subject1Name = String.format("%s, %s, %s", subject1.lastName, subject1.firstName, subject1.middleName);
		int subject1Age = 0;
		
		String officerSignature = "";
		String supervisorSignature = "";
		String officerDate = "";
		String supervisorDate = "";
		int forceJustified = 0;
		
		SQL_Command = String.format(insertIntoFormsCommand, date, time, dayOfWeek, incident.location, incident.type, 
				  officerName, officer.badgeNumber, officer.rank, officer.duty, yearsOfService,
				  officer.sex, officer.race, officerAge, officer.wasInjured, officer.wasKilled, 
				  officer.wasOnDuty, officer.wasUniformed, subject1Name, subject1.sex, 
				  subject1.race, subject1Age, subject1.wasWeaponed, subject1.wasInjured, subject1.wasKilled,  influences(subject1),
				  subject1.charges, actions(subject1), UOF(subject1), incident.officer.injuries, officer.hadMedicalTreatment, 
				  subject1.injuries, subject1.hadMedicalTreatment, officerSignature, officerDate, 
				  supervisorSignature, supervisorDate, forceJustified);
//		  form.subject2Name, form.subject2Sex, 
//		  form.subject2Race, form.subject2Age, form.subject2HadWeapon, form.subject2Injured, form.subject2Killed, 
//		  form.subject2UnderInfluence, form.subject2Charges, form.subject2Actions, 
//		  form.subject2Treatement,
		return SQL_Command;
	}
	private String influences(Subject subject) {
		return subject.influence.toString() + " " + subject.otherInfluence;
	}
	
	private String actions(Subject subject) {
		return subject.actions.toString() + " " + subject.otherActions;
	}
	
	private String UOF(Subject subject) {
		return subject.uofAgainst.toString() + " " + subject.otherUOF;
	}

}
//		if (form.hasSectionC2()) {
//			insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s", ROW_FORMAT_SUBJECT2, VALUES_FORMAT_SUBJECT2);
//			SQL_Command = String.format(insertIntoFormsCommand, form.date, form.time, form.dayOfWeek, form.location, form.typeOfIncident, 
//					  form.officerName, form.officerBadgeNumber, form.officerRank, form.dutyAssignment, form.yearsOfService,
//					  form.officerSex, form.officerRace, form.officerAge, form.officerInjured, form.officerKilled, 
//					  form.officerOnDuty, form.officerHadUniform, form.subject1Name, form.subject1Sex, 
//					  form.subject1Race, form.subject1Age, form.subject1HadWeapon, form.subject1Injured, form.subject1Killed, 
//					  form.subject1UnderInfluence, form.subject1Charges, form.subject1Actions, 
//					  form.subject1Treatement, 
//					  form.subject2Name, form.subject2Sex, 
//					  form.subject2Race, form.subject2Age, form.subject2HadWeapon, form.subject2Injured, form.subject2Killed, 
//					  form.subject2UnderInfluence, form.subject2Charges, form.subject2Actions, 
//					  form.subject2Treatement,
//					  form.officerInjuries, form.officerHospitalized, form.subjectInjuries, 
//					  form.subjectHospitalized, form.officerSignature, form.officerSignDate, 
//					  form.supervisorSignature, form.supervisorSignatureDate, form.forceJustified);
//			return SQL_Command;
//		}
//		insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s", ROW_FORMAT, VALUES_FORMAT);
//		SQL_Command = String.format(insertIntoFormsCommand, form.date, form.time, form.dayOfWeek, form.location, form.typeOfIncident, 
//				  form.officerName, form.officerBadgeNumber, form.officerRank, form.dutyAssignment, form.yearsOfService,
//				  form.officerSex, form.officerRace, form.officerAge, form.officerInjured, form.officerKilled, 
//				  form.officerOnDuty, form.officerHadUniform, form.subject1Name, form.subject1Sex, 
//				  form.subject1Race, form.subject1Age, form.subject1HadWeapon, form.subject1Injured, form.subject1Killed, 
//				  form.subject1UnderInfluence, form.subject1Charges, form.subject1Actions, 
//				  form.subject1Treatement, form.officerInjuries, form.officerHospitalized, form.subjectInjuries, 
//				  form.subjectHospitalized, form.officerSignature, form.officerSignDate, 
//				  form.supervisorSignature, form.supervisorSignatureDate, form.forceJustified);
//		return SQL_Command;

