package form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import database.SQL;
import database.Utilities;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.FForce;

public class SearchForm extends Application{
	
	private ArrayList<Incident> incidents;
	private SQL sql;
	
	private final int SCROLL_OFFSET = 19;
	private final int SMALL_INSET = 3;
	private final int MEDIUM_INSET = 5;
	private final int LARGE_INSET = 10;
	
	private VBox screen;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		SearchForm searchStage = new SearchForm();
		searchStage.create(primaryStage);
		primaryStage.show();
	}
	
	public Stage create(Stage root) {
		incidents = new ArrayList<Incident>();
		
		try {
			sql = new SQL(FForce.getUsername(), FForce.getPassword());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		screen = new VBox();
		screen.setPadding(new Insets(MEDIUM_INSET));
		root.setScene(new Scene(screen, 600, 600));
		
		VBox search = new VBox();
		search.setPadding(new Insets(MEDIUM_INSET));
		search.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		screen.getChildren().add(search);
		
		ScrollPane results = new ScrollPane();
		screen.heightProperty().addListener((InvalidationListener) observable -> {
			results.setPrefViewportHeight(screen.getHeight()-search.getHeight()-SCROLL_OFFSET);
		});
		results.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		results.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		screen.getChildren().add(results);
		
		search.getChildren().add(createSearchPane());
		
		results.setContent(createResultsPane());
		
		root.show();
		
		results.setPrefViewportHeight(screen.getHeight()-search.getHeight()-SCROLL_OFFSET);
		
		return root;
	}
	
	private GridPane createSearchPane() {
		GridPane search = new GridPane();
		search.setPadding(new Insets(MEDIUM_INSET));
		
		search.add(FormUtil.makePaddedLabel("Search By:"), 0, 0);
		ChoiceBox<String> criteria = new ChoiceBox<String>();
		criteria.getItems().addAll("Incident Number", "Badge Number");
		criteria.getSelectionModel().select(0);
		search.add(criteria, 0, 1);
		
		search.add(FormUtil.makePaddedLabel("Search For:"), 1, 0);
		TextField value = new TextField();
		FormUtil.makeTextFieldNumeric(value);
		search.add(value, 1, 1);
		
		Button processSearch = new Button("Search");
		processSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				incidents.clear();
				if(criteria.getItems().get(FormUtil.getCurrentIndex(criteria)).equals("Incident Number")) {
					incidents = getForm(value.getText()); //TODO: retrieve sql data
				}
				else if(criteria.getItems().get(FormUtil.getCurrentIndex(criteria)).equals("Badge Number")) {
//					incidents = getIncidentsFromBadgeNumber(value.getText()); //TODO: retrieve sql data
				}
				((ScrollPane) screen.getChildren().get(1)).setContent(createResultsPane());
			}
		});
		search.add(processSearch, 2, 1);
		
		return search;
	}

	private VBox createResultsPane() {
		
		VBox results = new VBox();
		
		for(int i=0; i<incidents.size(); i++) {
			results.getChildren().add(createResultPane(i));
			results.getChildren().add(new Separator());
			((Region) results.getChildren().get(1)).setPadding(new Insets(LARGE_INSET));
		}
		
		return results;
	}
	
	private VBox createResultPane(int incidentNumber) {
		Incident incident = incidents.get(incidentNumber);
		
		VBox result = new VBox();
		result.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		result.getChildren().add(addIncidentInformation(incidentNumber));
		result.getChildren().add(addOfficerInformation(incidentNumber));
		for(int i=0; i < incident.subjects.size(); i++) {
			result.getChildren().add(addSubjectInformation(incidentNumber, i));
		}
		result.getChildren().add(addInjuryInformation(incidentNumber));
		
		return result;
	}

	private VBox addIncidentInformation(int incidentNumber) {
		Incident incident = incidents.get(incidentNumber);
		
		VBox result = new VBox();
		
		result.getChildren().add(FormUtil.makePaddedLabel("A. Incident Information"));
		((Labeled) result.getChildren().get(0)).setFont(Font.font("", FontWeight.BOLD, 14));
		
		VBox incidentInfo = new VBox();
		incidentInfo.setPadding(new Insets(MEDIUM_INSET));
		result.getChildren().add(incidentInfo);
		
		GridPane dateTimeInfo = new GridPane();
		dateTimeInfo.setGridLinesVisible(true);
		dateTimeInfo.setPadding(new Insets(SMALL_INSET));
		incidentInfo.getChildren().add(dateTimeInfo);
		
		dateTimeInfo.add(FormUtil.makePaddedLabel("Date"), 0, 0);
		dateTimeInfo.add(FormUtil.makePaddedLabel(Utilities.convertDate(incident.incidentDate)), 0, 1);
		
		dateTimeInfo.add(FormUtil.makePaddedLabel("Time"), 1, 0);
		dateTimeInfo.add(FormUtil.makePaddedLabel(Utilities.convertTime(incident.incidentDate)), 1, 1);
		
		dateTimeInfo.add(FormUtil.makePaddedLabel("Day of Week"), 2, 0);
		dateTimeInfo.add(FormUtil.makePaddedLabel(Utilities.dateToDayOfWeek(incident.incidentDate)), 2, 1);
		
		dateTimeInfo.add(FormUtil.makePaddedLabel("Location"), 3, 0);
		dateTimeInfo.add(FormUtil.makePaddedLabel(incident.location), 3, 1);
		
		dateTimeInfo.add(FormUtil.makePaddedLabel("FUPD Case #"), 4, 0);
		dateTimeInfo.add(FormUtil.makePaddedLabel(""+incident.id), 4, 1);
		
		GridPane incidentType = new GridPane();
		incidentType.setGridLinesVisible(true);
		incidentType.setPadding(new Insets(SMALL_INSET));
		incidentInfo.getChildren().add(incidentType);
		
		incidentType.add(FormUtil.makePaddedLabel("Type of Incident"), 0, 0);
		incidentType.add(FormUtil.makePaddedLabel(Utilities.type(incident)), 0, 1);
		
		result.getChildren().add(new Separator());
		((Region) result.getChildren().get(2)).setPadding(new Insets(MEDIUM_INSET));
		
		return result;
	}
	
	private VBox addOfficerInformation(int incidentNumber) {
		Officer officer = incidents.get(incidentNumber).officer;
		OfficerInfo info = officer.info;
		
		VBox result = new VBox();
		
		result.getChildren().add(FormUtil.makePaddedLabel("B. Officer Information"));
		((Labeled) result.getChildren().get(0)).setFont(Font.font("", FontWeight.BOLD, 14));
		
		VBox officerInfo = new VBox();
		officerInfo.setPadding(new Insets(MEDIUM_INSET));
		result.getChildren().add(officerInfo);
		
		GridPane personalInfo = new GridPane();
		personalInfo.setGridLinesVisible(true);
		personalInfo.setPadding(new Insets(SMALL_INSET));
		officerInfo.getChildren().add(personalInfo);
		
		personalInfo.add(FormUtil.makePaddedLabel("Name (Last, First Middle)"), 0, 0);
		personalInfo.add(FormUtil.makePaddedLabel(info.lastName + ", " + info.firstName + ", " + info.middleName), 0, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Badge #"), 1, 0);
		personalInfo.add(FormUtil.makePaddedLabel(""+info.badgeNumber), 1, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Sex"), 2, 0);
		personalInfo.add(FormUtil.makePaddedLabel(info.sex), 2, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Race"), 3, 0);
		personalInfo.add(FormUtil.makePaddedLabel(info.race), 3, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Date of Birth"), 4, 0);
		personalInfo.add(FormUtil.makePaddedLabel(Utilities.convertDate(info.dateOfBirth)), 4, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Injuried"), 5, 0);
		personalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(officer.wasInjured)), 5, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Killed"), 6, 0);
		personalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(officer.wasKilled)), 6, 1);
		
		GridPane temporalInfo = new GridPane();
		temporalInfo.setGridLinesVisible(true);
		temporalInfo.setPadding(new Insets(SMALL_INSET));
		officerInfo.getChildren().add(temporalInfo);
		
		temporalInfo.add(FormUtil.makePaddedLabel("Rank"), 0, 0);
		temporalInfo.add(FormUtil.makePaddedLabel(info.rank), 0, 1);
		
		temporalInfo.add(FormUtil.makePaddedLabel("Duty Assignment"), 1, 0);
		temporalInfo.add(FormUtil.makePaddedLabel(info.duty), 1, 1);
		
		temporalInfo.add(FormUtil.makePaddedLabel("Service Start Date"), 2, 0);
		temporalInfo.add(FormUtil.makePaddedLabel(Utilities.convertDate(info.serviceStart)), 2, 1);
		
		temporalInfo.add(FormUtil.makePaddedLabel("On-Duty"), 3, 0);
		temporalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(officer.wasOnDuty)), 3, 1);
		
		temporalInfo.add(FormUtil.makePaddedLabel("Uniform"), 4, 0);
		temporalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(officer.wasUniformed)), 4, 1);
		
		result.getChildren().add(new Separator());
		((Region) result.getChildren().get(2)).setPadding(new Insets(MEDIUM_INSET));
		
		return result;
	}
		
	private VBox addSubjectInformation(int incidentNumber, int subjectNumber) {
		Subject subject = incidents.get(incidentNumber).subjects.get(subjectNumber);
		
		VBox result = new VBox();

		result.getChildren().add(FormUtil.makePaddedLabel("C" + (subjectNumber+1) + ". Subject Informaton"));
		((Labeled) result.getChildren().get(0)).setFont(Font.font("", FontWeight.BOLD, 14));
		
		VBox subjectInfo = new VBox();
		subjectInfo.setPadding(new Insets(MEDIUM_INSET));
		result.getChildren().add(subjectInfo);
		
		GridPane personalInfo = new GridPane();
		personalInfo.setPadding(new Insets(SMALL_INSET));
		personalInfo.setGridLinesVisible(true);
		subjectInfo.getChildren().add(personalInfo);
		
		personalInfo.add(FormUtil.makePaddedLabel("Name (Last, First Middle)"), 0, 0);
		personalInfo.add(FormUtil.makePaddedLabel(subject.lastName + ", " + subject.firstName + ", " + subject.middleName), 0, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Weapon"), 1, 0);
		personalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(subject.wasWeaponed)), 1, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Sex"), 2, 0);
		personalInfo.add(FormUtil.makePaddedLabel(subject.sex), 2, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Race"), 3, 0);
		personalInfo.add(FormUtil.makePaddedLabel(subject.race), 3, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Age"), 4, 0);
		personalInfo.add(FormUtil.makePaddedLabel(""+subject.age), 4, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Injuried"), 5, 0);
		personalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(subject.wasInjured)), 5, 1);
		
		personalInfo.add(FormUtil.makePaddedLabel("Killed"), 6, 0);
		personalInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(subject.wasKilled)), 6, 1);
		
		GridPane effectInfo = new GridPane();
		effectInfo.setPadding(new Insets(SMALL_INSET));
		effectInfo.setGridLinesVisible(true);
		subjectInfo.getChildren().add(effectInfo);
		
		effectInfo.add(FormUtil.makePaddedLabel("Under the Influence of:"), 0, 0);
		effectInfo.add(FormUtil.makePaddedLabel(Utilities.influences(subject)), 0, 1);
		
		effectInfo.add(FormUtil.makePaddedLabel("Arrested"), 1, 0);
		effectInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(subject.wasArrested)), 1, 1);
		
		effectInfo.add(FormUtil.makePaddedLabel("Charges"), 2, 0);
		effectInfo.add(FormUtil.makePaddedLabel(subject.charges), 2, 1);
		
		GridPane actionsInfo = new GridPane();
		actionsInfo.setPadding(new Insets(SMALL_INSET));
		actionsInfo.setGridLinesVisible(true);
		subjectInfo.getChildren().add(actionsInfo);
		
		actionsInfo.add(FormUtil.makePaddedLabel("Subject's Actions:"), 0, 0);
		actionsInfo.add(FormUtil.makePaddedLabel(Utilities.actions(subject)), 0, 1);
		
		actionsInfo.add(FormUtil.makePaddedLabel("Officer's Use of Force Toward Subject"), 1, 0);
		actionsInfo.add(FormUtil.makePaddedLabel(Utilities.UOF(subject)), 1, 1);
		
		result.getChildren().add(new Separator());
		((Region) result.getChildren().get(2)).setPadding(new Insets(MEDIUM_INSET));
		
		return result;
	}
	
	private VBox addInjuryInformation(int incidentNumber) {
		Incident incident = incidents.get(incidentNumber);
		Officer officer = incident.officer;
		ArrayList<Subject> subjects = incident.subjects;
		
		VBox result = new VBox();
		
		result.getChildren().add(FormUtil.makePaddedLabel("D. Injuries and Medical Treatment"));
		((Labeled) result.getChildren().get(0)).setFont(Font.font("", FontWeight.BOLD, 14));
		
		VBox medicalInfo = new VBox();
		medicalInfo.setPadding(new Insets(MEDIUM_INSET));
		result.getChildren().add(medicalInfo);
		
		GridPane injuryInfo = new GridPane();
		injuryInfo.setPadding(new Insets(SMALL_INSET));
		injuryInfo.setGridLinesVisible(true);
		medicalInfo.getChildren().add(injuryInfo);
		
		injuryInfo.add(FormUtil.makePaddedLabel("Describe Injuries to Officer:"), 0, 0);
		injuryInfo.add(FormUtil.makePaddedLabel(officer.injuries), 0, 1);
		
		injuryInfo.add(FormUtil.makePaddedLabel("Medical Treatment:"), 1, 0);
		injuryInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(officer.hadMedicalTreatment)), 1, 1);
		
		int position = 2;
		for(int i=0; i<subjects.size(); i++) {
			injuryInfo.add(FormUtil.makePaddedLabel("Describe Injuries to " + FormUtil.numberNotation(i+1) + " Subject:"), 0, position);
			injuryInfo.add(FormUtil.makePaddedLabel(subjects.get(i).injuries), 0, position+1);
		
			injuryInfo.add(FormUtil.makePaddedLabel("Medical Treatment to " + FormUtil.numberNotation(i+1) + " Subject:"), 1, position);
			injuryInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(subjects.get(i).hadMedicalTreatment)), 1, position+1);
			
			position+=2;
		}
		
		GridPane signatureInfo = new GridPane();
		signatureInfo.setPadding(new Insets(SMALL_INSET));
		signatureInfo.setGridLinesVisible(true);
		medicalInfo.getChildren().add(signatureInfo);
		
		signatureInfo.add(FormUtil.makePaddedLabel("Officer Signed:"), 0, 0);
		signatureInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(officer.hasSigniture)), 0, 1);
		
		if(officer.hasSigniture) {
			signatureInfo.add(FormUtil.makePaddedLabel("Date:"), 1, 0);
			signatureInfo.add(FormUtil.makePaddedLabel(Utilities.convertDate(officer.signDate)), 1, 1);
		}
		
		signatureInfo.add(FormUtil.makePaddedLabel("Supervisor Signed:"), 0, 2);
		signatureInfo.add(FormUtil.makePaddedLabel(FormUtil.booleanToYesNo(incident.hasSupervisorSignature)), 0, 3);
		
		if(incident.hasSupervisorSignature) {
			signatureInfo.add(FormUtil.makePaddedLabel("Date:"), 1, 2);
			signatureInfo.add(FormUtil.makePaddedLabel(Utilities.convertDate(incident.supervisorSignDate)), 1, 3);
		}
		
		GridPane findingInfo = new GridPane();
		findingInfo.setPadding(new Insets(SMALL_INSET));
		findingInfo.setGridLinesVisible(true);
		medicalInfo.getChildren().add(findingInfo);
		
		findingInfo.add(FormUtil.makePaddedLabel("Reviewing Supervisor Finding:"), 0, 0);
		findingInfo.add(FormUtil.makePaddedLabel(incident.supervisorFinding), 0, 1);
		
		result.getChildren().add(new Separator());
		((Region) result.getChildren().get(2)).setPadding(new Insets(MEDIUM_INSET));
		
		return result;
	}

	private Incident createSampleIncident() {
			
		OfficerInfo info = new OfficerInfo(21, "Johnathan", "Tyler", "Dewey", "Male", "White", new Date(), new Date(), "Private", "Patrol");
		
		Officer officer = new Officer(info, false, false, false, false, false, "A", false, new Date());
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		
		ArrayList<String> influence = new ArrayList<String>();
		influence.addAll(0, Arrays.asList("Bitcoin"));
		ArrayList<String> actions = new ArrayList<String>();
		actions.addAll(0, Arrays.asList("Acting"));
		ArrayList<String> uofAgainst = new ArrayList<String>();
		uofAgainst.addAll(0, Arrays.asList("Violence"));
		Subject subject = new Subject("Joe", "Damn", "Mama", "Other", "White", 1, false, false, true, false, false, "B1", "C1", influence, "D1", actions, "E1", uofAgainst, "F1", 0);
		subjects.add(subject);
		
		influence = new ArrayList<String>();
		influence.addAll(0, Arrays.asList("Eether", "Doge"));
		actions = new ArrayList<String>();
		actions.addAll(0, Arrays.asList("Cool Aid", "Hope", "Truth"));
		uofAgainst = new ArrayList<String>();
		uofAgainst.addAll(0, Arrays.asList("Bomb", "Threat", "Other"));
		subject = new Subject("Tom", "Darn", "Papa", "Beyond", "Green", 3, false, true, false, false, true, "B2", "C2", influence, "D2", actions, "E2", uofAgainst, "F2", 9999);
		subjects.add(subject);
		
		subjects.add(subject);
		
		return new Incident(officer, subjects, new Date(), "Your Moms House", "Excessive Force", "", true, new Date(), "G");
	}
	
	
}
