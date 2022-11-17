package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import form.Incident;
import form.Officer;
import form.Subject;

public class SQL {
	
		//connection information
		protected static Connection _CON;
		
		//user information
		protected String _username;
		protected String _password;
		protected int _userPrivlege;
		
		//system roles
		protected static int ADMIN = 0;
		protected static int USER = 1;

		
	public SQL() throws SQLException {
		try {
			_CON = ConnectionInformation.establishConnection();
		}
		catch (Exception e) {
			
			throw new SQLException();
		}
	}
	
	
	public SQL(String username, String password) throws SQLException {
		try {
			_CON = ConnectionInformation.establishConnection();
			
			ResultSet rs = getInformation(username, password);
			while (rs.next()) {
				_username = rs.getString("username");
				_password = rs.getString("password");
				_userPrivlege = rs.getInt("systemRole");
			}
			
		}
		catch (Exception e)
		{
			throw new SQLException("Invalid Login");
		}
	}

	public boolean addOfficer(String username, String password, String fName, String lName, int badgeNumber, String email, int privlige) {
		if (_userPrivlege != ADMIN)
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
			System.out.println(SQL_Command);
			insertIntoForms.execute(SQL_Command);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		if (incident.subjects.size() <= 1) return true;
			
		SQL_Command = String.format("SELECT caseID FROM forms WHERE date = '%s' AND time = '%s", Utilities.convertDate(incident.incidentDate), Utilities.convertTime(incident.incidentDate));
		
		try {
			Statement getCaseID = _CON.createStatement();
			ResultSet rs = getCaseID.executeQuery(SQL_Command);
			SQL_Command = getSQL_AddSubject(incident.subjects.get(1), rs.getInt("caseID"));	
			Statement insertIntoForms = _CON.createStatement();
			insertIntoForms.execute(SQL_Command);
			return true;
		}
			catch (Exception e) {
				return false;
			}
	}
	
	public boolean deleteUser(String username) {
		if (_userPrivlege != ADMIN)
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
		if (_userPrivlege != ADMIN)
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
		String SQL_Command = "SELECT * FROM userInfo WHERE username = '%s' AND password = '%s'";
		SQL_Command = String.format(SQL_Command, username, password);
		try {
			Statement isUser = _CON.createStatement();
			isUser.execute(SQL_Command);
			
			ResultSet results = isUser.getResultSet();
			return results.first();
		}
		catch (Exception e) {
			return false;
		}
		
	}
	
	private ResultSet getInformation(String username, String password) throws SQLException {
		String SQL_command = String.format("SELECT * FROM userInfo WHERE username ='%s' AND password ='%s'", username, password);
		try {
			Statement getInformation = _CON.createStatement();
			ResultSet rs = getInformation.executeQuery(SQL_command);
			return rs;
		}
		catch (Exception e) {
			throw new SQLException("Invalid username and password combination");
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
				 				+ " '%s', %d, '%s', %d, %d, '%s', %d, '%s', %d)";
		
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
							Utilities.convertDate(incident.incidentDate), Utilities.convertTime(incident.incidentDate), Utilities.dateToDayOfWeek(incident.incidentDate), incident.location, incident.type, 
							//Section B
							Utilities.getName(officer), officer.badgeNumber, officer.rank, officer.duty, Utilities.yearDelta(officer.serviceStart), officer.sex, officer.race, Utilities.yearDelta(officer.dateOfBirth), 
							Utilities.boolToInt(officer.wasInjured), Utilities.boolToInt(officer.wasKilled), 
							Utilities.boolToInt(officer.wasOnDuty), Utilities.boolToInt(officer.wasUniformed), 
				  			//Section C
				  			Utilities.getName(subject), subject.sex, subject.race, Utilities.yearDelta(subject.dateOfBirth), 
				  			Utilities.boolToInt(subject.wasWeaponed), Utilities.boolToInt(subject.wasInjured), 
				  			Utilities.boolToInt(subject.wasKilled),  Utilities.influences(subject), 
				  			subject.charges, Utilities.actions(subject), Utilities.UOF(subject), 
				  			//Section D
				  			officer.injuries, Utilities.boolToInt(officer.hadMedicalTreatment), subject.injuries, Utilities.boolToInt(subject.hadMedicalTreatment), 
				  			Utilities.boolToInt(incident.hasOfficerSignature), Utilities.convertDate(incident.officerSignDate), Utilities.boolToInt(incident.hasSupervisorSignature), Utilities.convertDate(incident.supervisorSignatureDate), Utilities.boolToInt(incident.forceIsJustified)
				  			);
		return SQL_Command;
	}
	
	public String getSQL_AddSubject(Subject subject, int caseID) {
		
		String tableValues = "'%s',%d,'%s','%s',%d,'%s','%s', %d, %d, %d, %d, %d,";
		String tableColumns = "(Subject2Name, Subject2Sex, Subject2Race, Subject2Age, Subject2HadWeapon, Subject2Injured, Subject2Killed, "
				 	  		+ "Subject2InfluencedBy, Subject2Charges, Subject2Actions, Subject2Treatment, InjuriesToOfficer)";
		
		String insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s WHERE caseID = %d", tableColumns, tableValues, caseID);
		
		String SQL_Command = String.format(insertIntoFormsCommand, 
							Utilities.getName(subject), subject.sex, subject.race, Utilities.yearDelta(subject.dateOfBirth), 
							Incident.boolToInt(subject.wasWeaponed), Incident.boolToInt(subject.wasInjured), 
							Incident.boolToInt(subject.wasKilled),  Utilities.influences(subject), 
							subject.charges, Utilities.actions(subject), Utilities.UOF(subject)
							);
		return SQL_Command;
	}
	
}
