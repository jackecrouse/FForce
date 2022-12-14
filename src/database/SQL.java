package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import form.Incident;
import form.Officer;
import form.OfficerInfo;
import form.Subject;

public class SQL {

	//connection information
	private static Connection _CON;

	//user information
	private String _username;
	private String _password;
	private int _userPrivlege;

	//system roles
	public static int ADMIN = 0;
	public static int USER = 1;

	public static Connection get_CON() {
		return _CON;
	}

	public String get_username() {
		return _username;
	}

	public String get_password() {
		return _password;
	}

	public int get_userPrivlege() {
		return _userPrivlege;
	}

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

			Statement getUserInfo = _CON.createStatement();
			String SQL_Command = String.format("SELECT * FROM userInfo WHERE username = '%s' AND password = '%s'", username, password);
			ResultSet rs = getUserInfo.executeQuery(SQL_Command);
			rs.first();
			_username = rs.getString("username");
			_password = rs.getString("password");
			_userPrivlege = rs.getInt("systemRole");
		}

		catch (Exception e)
		{
			throw new SQLException("Invalid Login");
		}
	}

	public ArrayList<Incident> getForm(int caseID) throws SQLException {
		String SQL_Command = String.format("SELECT * FROM forms WHERE CaseID = %d", caseID);
		Statement getForm = _CON.createStatement();
		ResultSet rs = getForm.executeQuery(SQL_Command);
		rs.first();

		ArrayList<Subject> subjectArray = getSubjectArrayFromResultSet(getSubject(caseID));
		OfficerInfo oInfo = getOfficerInfoFromResultSet(getOfficer(caseID));
		
		return getIncidentFromResultSet(rs, oInfo, subjectArray);
	}

	public ResultSet getSubject(int caseID) throws SQLException {
		String SQL_Command = String.format("SELECT * FROM subjectInfo WHERE CaseID = %d", caseID);
		Statement getForm = _CON.createStatement();
		ResultSet rs = getForm.executeQuery(SQL_Command);
		rs.first();
		return rs;
	}

	public ResultSet getOfficer(int caseID) throws SQLException {
		
		String SQL_Command = String.format("SELECT BadgeNumber FROM forms WHERE CaseID = %d", caseID);
		Statement getForm = _CON.createStatement();
		ResultSet rs = getForm.executeQuery(SQL_Command);
		rs.first();
		int badgeNumber = rs.getInt("BadgeNumber");
		
		SQL_Command = String.format("SELECT * FROM Officers WHERE badgeNumber = %d", badgeNumber);
		getForm = _CON.createStatement();
		rs = getForm.executeQuery(SQL_Command);
		
		rs.first();
		return rs;
	}

	public boolean addUser(String username, String password, String fName, String lName, Integer badgeNumber, String email, int privlige) {
		if (_userPrivlege != ADMIN) {
			return false;
		}

		if(!sanitizeInput(username)||!sanitizeInput(password)||!sanitizeInput(fName)||!sanitizeInput(lName)||!sanitizeInput(email)) {
			System.out.println("Bad input");
			return false;
		}

		String SQL_Command = String.format("INSERT INTO userInfo VALUES('%s', '%s', '%s', '%s', '%d', '%s', '%d')", username, password, fName, lName, badgeNumber, email, privlige);

		try {
			Statement addOfficer = _CON.createStatement();
			addOfficer.execute(SQL_Command);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public int getLastCaseID() {
		Statement getUser;
		try {
			getUser = _CON.createStatement();
			String SQL_Command = String.format("SELECT caseID FROM forms ORDER BY caseID DESC LIMIT 1");
			ResultSet rs = getUser.executeQuery(SQL_Command);
			rs.first();
			return rs.getInt("caseID");
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	public OfficerInfo getOfficer(String username, String password) {
		if(!sanitizeInput(username)||!sanitizeInput(password)) {
			System.out.println("Bad input");
			return null;
		}

		try {
			OfficerInfo info = new OfficerInfo();
			Statement getUser = _CON.createStatement();
			String SQL_Command = String.format("SELECT badgeNumber FROM userInfo WHERE username = '%s' AND password = '%s'", username, password);
			ResultSet rs = getUser.executeQuery(SQL_Command);
			rs.first();
			int badgeNumber = rs.getInt("BadgeNumber");
			SQL_Command = String.format("SELECT * FROM Officers WHERE BadgeNumber = '%s'", badgeNumber);
			rs = getUser.executeQuery(SQL_Command);
			rs.first();
			info.badgeNumber = rs.getInt("BadgeNumber");
			info.firstName = rs.getString("FirstName");
			info.middleName = rs.getString("MiddleName");
			info.lastName = rs.getString("LastName");
			info.sex = rs.getString("Sex");
			info.race = rs.getString("Race");
			info.dateOfBirth = Utilities.stringToDate(rs.getString("DateOfBirth"));
			info.serviceStart = Utilities.stringToDate(rs.getString("StartedService"));
			info.rank = rs.getString("Rank");
			info.duty = rs.getString("DutyAssignment");
			return info;
		}
		catch(Exception e) {
			e.printStackTrace();
			return new OfficerInfo();
		}
	}

	//As of now, can have two subjects max
	public boolean insertNewForm(Incident incident) {

		String SQL_Forms_Command = getSQL_InsertForm(incident);

		try {
			Statement insertIntoForms = _CON.createStatement();
			System.out.println(SQL_Forms_Command);
			insertIntoForms.execute(SQL_Forms_Command);

			Statement getCaseID = _CON.createStatement();
			String SQL_GetCaseID_Command = String.format("SELECT caseID FROM forms WHERE badgeNumber = '%s' AND date = '%s' AND time = '%s'", 
					incident.officer.info.badgeNumber, Utilities.convertDate(incident.incidentDate),
					Utilities.convertTime(incident.incidentDate));
			ResultSet rs = getCaseID.executeQuery(SQL_GetCaseID_Command);
			rs.first();
			incident.id = rs.getInt("caseID");

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		String[] SQL_Subjects_Commands = getSQL_InsertSubjects(incident.subjects, incident.id);
		try {
			Statement insertIntoForms = _CON.createStatement();
			for(int i=0; i<SQL_Subjects_Commands.length;i++) {
				System.out.println(SQL_Subjects_Commands[i]);
				insertIntoForms.execute(SQL_Subjects_Commands[i]);
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(String username) {
		if (_userPrivlege != ADMIN)
			return false;

		if(!sanitizeInput(username)) {
			System.out.println("Bad input");
			return false;
		}

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

		String SQL_Forms_Command = String.format("DELETE FROM forms WHERE caseID = %d", caseID);
		try {
			Statement deleteForm = _CON.createStatement();
			deleteForm.execute(SQL_Forms_Command);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean changePassword(String username, String oldPassword, String newPassword) {

		if(!sanitizeInput(username)||!sanitizeInput(oldPassword)||!sanitizeInput(newPassword)) {
			System.out.println("Bad input");
			return false;
		}

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

		if(!sanitizeInput(username)||!sanitizeInput(password)) {
			System.out.println("Bad input");
			return false;
		}

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

	private boolean sanitizeInput(String str)
	{
		String a[] = {"\"","'","=","*",";",":","{","}","[","]","(",")","\\"};
		for (String s : a) {
			if (str.contains(s)) return false;
		}
		return true;
	}

	private String getSQL_InsertForm(Incident incident) { 

		Officer officer = incident.officer;

		String tableValues = "('%s', '%s', '%s', '%s', '%s', '%d', '%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s', '%d', '%d', '%d', '%d', '%d', '%s', '%d', '%s', "
				+ "'%s')";

		String tableColumns = "(Date, Day, Time, Location, IncidentType, BadgeNumber, OfficerFnameSave, OfficerMnameSave, OfficerLnameSave, OfficerSexSave, "
				+ "OfficerRankSave, OfficerDutySave, OfficerInjured, OfficerInjuries, OfficerKilled, OfficerOnDuty, OfficerHadUniform, OfficerHadTreatment, "
				+ "OfficerSign, OfficerSignDate, SupervisorSign, SupervisorSignDate, supervisorFinding)";


		String insertIntoFormsCommand = String.format("INSERT INTO forms %s VALUES %s", tableColumns, tableValues);


		String SQL_Command = String.format(insertIntoFormsCommand, Utilities.convertDate(incident.incidentDate), Utilities.dateToDayOfWeek(incident.incidentDate),
				Utilities.convertTime(incident.incidentDate), incident.location, Utilities.type(incident), officer.info.badgeNumber,
				officer.info.firstName, officer.info.middleName, officer.info.lastName, officer.info.sex, officer.info.rank, officer.info.duty,
				Utilities.boolToInt(officer.wasInjured), officer.injuries, Utilities.boolToInt(officer.wasKilled),
				Utilities.boolToInt(officer.wasOnDuty), Utilities.boolToInt(officer.wasUniformed),
				Utilities.boolToInt(officer.hadMedicalTreatment), Utilities.boolToInt(officer.hasSignature),
				Utilities.convertDate(officer.signDate), Utilities.boolToInt(incident.hasSupervisorSignature),
				Utilities.convertDate(incident.supervisorSignDate), incident.supervisorFinding);

		return SQL_Command;
	}

	private String[] getSQL_InsertSubjects(ArrayList<Subject> subjects, int caseID) { 

		String SQL_Commands [] = new String[subjects.size()];

		for(int i=0;i<subjects.size(); i++) {
			SQL_Commands[i] = getSQL_AddSubject(subjects.get(i), caseID);
		}

		return SQL_Commands;
	}

	public String getSQL_AddSubject(Subject subject, int caseID) {

		String tableValues = "('%d', '%s', '%s', '%s', '%s', '%s', '%d', '%d', '%d', '%s', '%d', '%d', '%d', '%s', '%s', '%s', '%s', '%d')";

		String tableColumns = "(CaseID, SubjectFirstName, SubjectMiddleName, SubjectLastName, SubjectSex, SubjectRace, SubjectAge, SubjectHadWeapon, "
				+ "SubjectInjured, SubjectInjuries, SubjectKilled, SubjectWasArrested, SubjectHadTreatment, SubjectInfluencedBy, "
				+ "SubjectCharges, SubjectActions, UOFAgainstSubject, ShotsFired)";

		String insertIntoFormsCommand = String.format("INSERT INTO subjectInfo %s VALUES %s", tableColumns, tableValues);

		String SQL_Command = String.format(insertIntoFormsCommand, caseID, subject.firstName, subject.middleName, subject.lastName, subject.sex, subject.race, 
				subject.age, Utilities.boolToInt(subject.wasWeaponed), Utilities.boolToInt(subject.wasInjured),
				subject.injuries, Utilities.boolToInt(subject.wasKilled), Utilities.boolToInt(subject.wasArrested),
				Utilities.boolToInt(subject.hadMedicalTreatment), Utilities.influences(subject), subject.charges,
				Utilities.actions(subject), Utilities.UOF(subject), subject.numberOfShots);

		return SQL_Command;
	}

	public ArrayList<Subject> getSubjectArrayFromResultSet(ResultSet set)
	{
		ArrayList<Subject> result = new ArrayList<Subject>();

		try
		{
			do
			{
				ArrayList<String> sbj = new ArrayList<String>();
								
				String firstName = set.getString("SubjectFirstName");
				String middleName = set.getString("SubjectMiddleName");
				String lastName = set.getString("SubjectLastName");
				String sex = set.getString("SubjectSex");
				String race = set.getString("SubjectRace");
				int age = Integer.parseInt(set.getString("SubjectAge"));
				boolean wasInjured = Utilities.intToBool(Integer.parseInt(set.getString("SubjectInjured")));
				boolean wasKilled =  Utilities.intToBool(Integer.parseInt(set.getString("SubjectKilled")));
				boolean wasWeaponed = Utilities.intToBool(Integer.parseInt(set.getString("SubjectHadWeapon")));
				boolean wasArrested = Utilities.intToBool(Integer.parseInt(set.getString("SubjectWasArrested")));
				boolean hadMedicalTreatment = Utilities.intToBool(Integer.parseInt(set.getString("SubjectHadTreatment")));
				String injuries = set.getString("SubjectInjuries");
				String charges = set.getString("SubjectCharges");
				ArrayList<String> influence = Utilities.parseToArrayList(set.getString("SubjectInfluencedBy")); 
				ArrayList<String> actions = Utilities.parseToArrayList(set.getString("SubjectActions"));
				ArrayList<String> uofAgainst = Utilities.parseToArrayList(set.getString("UOFAgainstSubject"));
				int numberOfShots = Integer.parseInt(set.getString("ShotsFired"));

				Subject sub = new Subject(firstName, middleName, lastName, sex, race, age, wasInjured, wasKilled, wasWeaponed, wasArrested, hadMedicalTreatment,
						injuries, charges, influence, "", actions, "", uofAgainst, "", numberOfShots);

				result.add(sub);
			}
			while(set.next());
			return result;

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public OfficerInfo getOfficerInfoFromResultSet(ResultSet rs)
	{
		try
		{
			OfficerInfo info = new OfficerInfo(); 
			info.badgeNumber = rs.getInt("BadgeNumber");
			info.firstName = rs.getString("FirstName");
			info.middleName = rs.getString("MiddleName");
			info.lastName = rs.getString("LastName");
			info.sex = rs.getString("Sex");
			info.race = rs.getString("Race");
			info.dateOfBirth = Utilities.stringToDate(rs.getString("DateOfBirth"));
			info.serviceStart = Utilities.stringToDate(rs.getString("StartedService"));
			info.rank = rs.getString("Rank");
			info.duty = rs.getString("DutyAssignment");

			return info;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Officer getOfficerFromOfficerInfo(OfficerInfo offInfo, boolean wasInjured, boolean wasKilled, 
			boolean wasOnDuty, boolean wasUniformed, boolean hadMedicalTreatment, String injuries, boolean hasSignature,
			Date signDate)
	{
		Officer result = new Officer(); 
		result.hadMedicalTreatment = hadMedicalTreatment;
		result.hasSignature = hasSignature;
		result.info = offInfo;
		result.wasUniformed = wasUniformed;
		result.wasOnDuty = wasOnDuty;
		result.signDate = signDate;
		result.injuries = injuries;
		result.wasInjured = wasInjured;
		result.wasKilled = wasKilled;

		return result; 
	}

	public ArrayList<Incident> getIncidentFromResultSet(ResultSet rs, OfficerInfo officerData, ArrayList<Subject> subjectData)
	{
		try
		{
			Officer newOff = getOfficerFromOfficerInfo(officerData,
					Utilities.intToBool(rs.getInt("OfficerInjured")),
					Utilities.intToBool(rs.getInt("OfficerKilled")),
					Utilities.intToBool(rs.getInt("OfficerOnDuty")),
					Utilities.intToBool(rs.getInt("OfficerHadUniform")),
					Utilities.intToBool(rs.getInt("OfficerHadTreatment")),
					rs.getString("OfficerInjuries"),
					Utilities.intToBool(rs.getInt("OfficerSign")),
					rs.getDate("OfficerSignDate")
					);
			
			ArrayList<Incident> result = new ArrayList<Incident>(); 

			do
			{	
				Incident inc = new Incident(); 
				Date date = rs.getDate("Date");

				inc.officer = newOff;
				inc.id = rs.getInt("CaseID");
				inc.incidentDate = date;
				inc.location = rs.getString("Location");
				inc.type = rs.getString("IncidentType");
				inc.hasSupervisorSignature = Utilities.intToBool(rs.getInt("SupervisorSign"));
				inc.supervisorFinding = rs.getString("SupervisorFinding");
				inc.supervisorSignDate = rs.getDate("SupervisorSignDate");
				inc.subjects = subjectData;

				result.add(inc);
			}
			while(rs.next());
			
			return result;
		}

		catch(Exception e)
		{
			e.printStackTrace();
			return null;

		}
	}
}
