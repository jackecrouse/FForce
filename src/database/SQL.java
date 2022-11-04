package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.Date;

import form.Incident;
import form.Officer;
import form.Subject;

public class SQL {
	
		//connection information
		protected static Connection _CON;
		protected static String _DBurl = "jdbc:mysql://csserver2016.fu.campus/FuPoForce";
		protected static String _DBuser = "lkvamme";
		protected static String _DBpassword = "csc353";
		
		//
		protected int _UserPrivlege = 0;
		
		//system roles
		protected static int ADMIN = 0;
		protected static int USER = 1;

		
	public SQL() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			_CON = DriverManager.getConnection(_DBurl, _DBuser, _DBpassword);
		}
		catch (Exception e) {
			
			throw new SQLException();
		}
	}
	
	
	public SQL(String user, String password) throws SQLException {
		try {
			_DBuser = user;
			_DBpassword = password;
			Class.forName("com.mysql.jdbc.Driver");
			_CON = DriverManager.getConnection(_DBurl, _DBuser, _DBpassword);
		}
		catch (Exception e)
		{
			throw new SQLException();
		}
	}

	public boolean addOfficer(String username, String password, String fName, String lName, int badgeNumber, String email, int privlige) {
		if (_UserPrivlege != ADMIN)
			return false;
		
		String SQL_Command = String.format("INSERT INTO userInfo VALUES('%s', '%s', '%s', '%s', '%d', '%s', '%d')", username, password, fName, lName, badgeNumber, email, privlige);
		
		try {
			Statement addOfficer = _CON.createStatement();
			addOfficer.execute(SQL_Command);
			return true;
		}
		catch (Exception e){
			
		}
		return false;
	}
	
	//As of now, can have two subjects max
	public boolean insertNewForm(Incident incident) {
		
		String SQL_Command = getSQL_InsertForm(incident);
		
		try {
			Statement insertIntoForms = _CON.createStatement();
			insertIntoForms.execute(SQL_Command);
			if (incident.subjects.size() > 1)
				SQL_Command = getSQL_AddSubject(incident.subjects.get(1));
				insertIntoForms.execute(SQL_Command);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteUser(String username) {
		if (_UserPrivlege != ADMIN)
			return false;
		
		String SQL_Command = String.format("DELETE FROM userInfo WHERE username = '%s'", username);
		try {
			Statement deleteUser = _CON.createStatement();
			deleteUser.execute(SQL_Command);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public boolean deleteForm(int caseID) {
		if (_UserPrivlege != ADMIN)
			return false;
		
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
			if (rs.getFetchSize()!=0)
				return true;
			return false;
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
								
		String tableValues =    //section A
								"('%s','%s','%s','%s','%s',"
								//section B
								+ "'%s',%d,'%s','%s',%d,'%s','%s', %d, %d, %d, %d, %d,"
								//section C
				 				+ " '%s', '%s', '%s', %d, %d, %d, %d, '%s', '%s', '%s', '%s',"
				 				//section D
				 				+ " '%s', %d, '%s', %d, '%d', '%s', '%d', '%s', %d)";
		
		String tableColumns = //Section A
						   "(Date, Time, Day, Location, IncidentType, "
							//Section B
						 + "OfficerName, BadgeNumber, Rank, DutyAssignment, YearsOfService, OfficerSex, OfficerRace, OfficerAge, "
						 + "OfficerInjured, OfficerKilled, OfficerOnDuty, OfficerHadUniform, "
						 	//Section C
						 + "Subject1Name, Subject1Sex, Subject1Race, Subject1Age, Subject1HadWeapon, Subject1Injured, Subject1Killed, "
						 + "Subject1InfluencedBy, Subject1Charges, Subject1Actions, Subject1Treatment, InjuriesToOfficer, "
						 	//Section D
						 + "HospitalizedOfficer, InjuriesToSubject, HospitalizedSubject, OfficerSign, OfficerSignDate, SupervisorSign, "
						 + "SupervisorSignDate, ForceJustified)";
			
		
		String insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s", tableColumns, tableValues);
		
		Officer officer = incident.officer;
		Subject subject = incident.subjects.get(0);
		
		
		String SQL_Command = String.format(insertIntoFormsCommand, 
							//Section A
							Utilities.convertDate(incident.incidentDate), Utilities.convertTime(incident.incidentDate),
							Utilities.dateToDayOfWeek(incident.incidentDate), incident.location, incident.type, 
							//Section B
							Utilities.getName(officer), officer.badgeNumber, officer.rank, officer.duty,
							Utilities.yearDelta(officer.serviceStart), officer.sex, officer.race,
							Utilities.yearDelta(officer.dateOfBirth), 
							Utilities.boolToInt(officer.wasInjured), Utilities.boolToInt(officer.wasKilled), 
							Utilities.boolToInt(officer.wasOnDuty), Utilities.boolToInt(officer.wasUniformed), 
				  			//Section C
				  			Utilities.getName(subject), subject.sex, subject.race, Utilities.yearDelta(subject.dateOfBirth),
				  			Utilities.boolToInt(subject.wasWeaponed), Utilities.boolToInt(subject.wasInjured), 
				  			Utilities.boolToInt(subject.wasKilled),  Utilities.influences(subject), 
				  			subject.charges, Utilities.actions(subject), Utilities.UOF(subject), 
				  			//Section D
				  			officer.injuries, Utilities.boolToInt(officer.hadMedicalTreatment), subject.injuries,
				  			Utilities.boolToInt(subject.hadMedicalTreatment), 
				  			Utilities.boolToInt(incident.hasOfficerSignature), Utilities.convertDate(incident.officerSignDate),
				  			Utilities.boolToInt(incident.hasSupervisorSignature), Utilities.convertDate(incident.supervisorSignatureDate),
				  			Utilities.boolToInt(incident.forceIsJustified)
				  			);
		return SQL_Command;
	}
	
	public String getSQL_AddSubject(Subject subject) {
		
		String tableValues = "'%s',%d,'%s','%s',%d,'%s','%s', %d, %d, %d, %d, %d,";
		String tableColumns = "(Subject2Name, Subject2Sex, Subject2Race, Subject2Age, Subject2HadWeapon, Subject2Injured, Subject2Killed, "
				 	  		+ "Subject2InfluencedBy, Subject2Charges, Subject2Actions, Subject2Treatment, InjuriesToOfficer)";
		
		String insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s", tableColumns, tableValues);
		
		String SQL_Command = String.format(insertIntoFormsCommand, 
							Utilities.getName(subject), subject.sex, subject.race, Utilities.yearDelta(subject.dateOfBirth), 
							Utilities.boolToInt(subject.wasWeaponed), Utilities.boolToInt(subject.wasInjured), 
							Utilities.boolToInt(subject.wasKilled),  Utilities.influences(subject), 
							subject.charges, Utilities.actions(subject), Utilities.UOF(subject)
							);
		return SQL_Command;
	}
	
}
