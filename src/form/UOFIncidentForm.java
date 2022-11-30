package form;

import java.sql.SQLException;

import database.SQL;
import javafx.application.Application;
import javafx.stage.Stage;
import main.FForce;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;

public class UOFIncidentForm extends Application {

	private Incident incident;
	private SQL sql;
	
	private Pane pneReport;
	private HBox hbxReport, hbxTime, hbxSubjectInteract;
	private VBox vbxOfficerFull, vbxIncidentFull, vbxIncidentType, vbxSubmit;
	private Text txtOfficerInfo, txtIncidentInfo, txtSubjects;
	private GridPane grdOfficerInfo, grdIncidentInfo;
	private TextField txfBadgeNumber,txfFirstName, txfMiddleName, txfLastName,
					  txfSex, txfRace, txfRank, txfDutyAssignment, txfIncidentNumber,
					  txfIncidentLocation, txfOtherDesc;
	private DatePicker dtpDOB, dtpServiceStart, dtpIncidentDate;
	private CheckBox cbxOfficerInjured, cbxOfficerKilled, cbxOfficerOnDuty,
					 cbxOfficerUniformed, cbxOfficerTreatment;
	private TextArea txaOfficerInjuriesDesc;
	private ToggleGroup tgrType;
	private RadioButton rdbInProgress, rdbDomestic, rdbSuspicious, rdbTrafficStop,
						rdbArrest, rdbOtherType;
	private Button btnCreateSubject, btnDeleteSubject, btnEditSubject, btnSubmit, btnQuit;
	private ComboBox<String> cobAmPm, cobHour, cobMinutes, cobSeconds, cobSubjectChoice;
	
	public static void run() {
		launch();
	}

	public Stage create(Stage form) {
		
		try {
			sql = new SQL(FForce.getUsername(), FForce.getPassword());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		incident = new Incident();

		form.setTitle("Furman University Police Use of Force Report");
		form.setResizable(false);

		pneReport = new Pane();
		form.setScene(new Scene(pneReport, 770, 610));

		hbxReport = new HBox();
		pneReport.getChildren().add(hbxReport);

		createOfficerInfo();

		hbxReport.getChildren().add(new Separator(Orientation.VERTICAL));

		createIncidentInfo();

		vbxIncidentFull.getChildren().add(new Separator());

		createSubjectSelector();
		
		vbxIncidentFull.getChildren().add(new Separator());

		createSubmitArea();

		return form;
	}

	private void createOfficerInfo() {
		vbxOfficerFull = new VBox();
		hbxReport.getChildren().add(vbxOfficerFull);
		
		txtOfficerInfo = new Text("Officer Information");
		txtOfficerInfo.setTextAlignment(TextAlignment.CENTER);
		txtOfficerInfo.setWrappingWidth(322.0);
		vbxOfficerFull.getChildren().add(txtOfficerInfo);

		createOfficerPersonalInfo();
		
		createOfficerIncidentInfo();
	}

	private void createOfficerPersonalInfo() {
		
		incident.officer.info = sql.getOfficer(sql.get_username(), sql.get_password());
		
		grdOfficerInfo = new GridPane();
		vbxOfficerFull.getChildren().add(grdOfficerInfo);
		
		grdOfficerInfo.add(new Label("Badge Number"), 0, 0);
		txfBadgeNumber = new TextField(""+incident.officer.info.badgeNumber);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfBadgeNumber.setDisable(true);
			txfBadgeNumber.setOpacity(0.7);
		}
		FormUtil.makeTextFieldNumeric(txfBadgeNumber);
		grdOfficerInfo.add(txfBadgeNumber, 1, 0);
		
		grdOfficerInfo.add(new Label("First Name"), 0, 1);
		txfFirstName = new TextField(incident.officer.info.firstName);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfFirstName.setDisable(true);
			txfFirstName.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfFirstName, 1, 1);
		
		grdOfficerInfo.add(new Label("Middle Name"), 0, 2);
		txfMiddleName = new TextField(incident.officer.info.middleName);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfMiddleName.setDisable(true);
			txfMiddleName.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfMiddleName, 1, 2);
		
		grdOfficerInfo.add(new Label("Last Name"), 0, 3);
		txfLastName = new TextField(incident.officer.info.lastName);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfLastName.setDisable(true);
			txfLastName.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfLastName, 1, 3);
		
		grdOfficerInfo.add(new Label("Sex"), 0, 4);
		txfSex = new TextField(incident.officer.info.sex);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfSex.setDisable(true);
			txfSex.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfSex, 1, 4);
		
		grdOfficerInfo.add(new Label("Race"), 0, 5);
		txfRace = new TextField(incident.officer.info.race);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfRace.setDisable(true);
			txfRace.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfRace, 1, 5);
		
		grdOfficerInfo.add(new Label("Date Of Birth"), 0, 6);
		dtpDOB = new DatePicker(FormUtil.dateToLocalDate(incident.officer.info.dateOfBirth));
		if(sql.get_userPrivlege() == SQL.USER) {
			dtpDOB.setDisable(true);
			dtpDOB.setOpacity(0.7);
		}
		grdOfficerInfo.add(dtpDOB, 1, 6);
		
		grdOfficerInfo.add(new Label("Started Service"), 0, 7);
		dtpServiceStart = new DatePicker(FormUtil.dateToLocalDate(incident.officer.info.serviceStart));
		if(sql.get_userPrivlege() == SQL.USER) {
			dtpServiceStart.setDisable(true);
			dtpServiceStart.setOpacity(0.7);
		}
		grdOfficerInfo.add(dtpServiceStart, 1, 7);
		
		grdOfficerInfo.add(new Label("Rank"), 0, 8);
		txfRank = new TextField(incident.officer.info.rank);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfRank.setDisable(true);
			txfRank.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfRank, 1, 8);
		
		grdOfficerInfo.add(new Label("Duty Assignment"), 0, 9);
		txfDutyAssignment = new TextField(incident.officer.info.duty);
		if(sql.get_userPrivlege() == SQL.USER) {
			txfDutyAssignment.setDisable(true);
			txfDutyAssignment.setOpacity(0.7);
		}
		grdOfficerInfo.add(txfDutyAssignment, 1, 9);
	}
	
	private void createOfficerIncidentInfo() {
		int injuresLabelIndex = 7;
		
		cbxOfficerInjured = new CheckBox("Officer Was Injured");
		vbxOfficerFull.getChildren().add(cbxOfficerInjured);

		cbxOfficerKilled = new CheckBox("Officer Was Killed");
		vbxOfficerFull.getChildren().add(cbxOfficerKilled);

		cbxOfficerOnDuty = new CheckBox("Officer Was On-Duty");
		vbxOfficerFull.getChildren().add(cbxOfficerOnDuty);

		cbxOfficerUniformed = new CheckBox("Officer was Uniformed");
		vbxOfficerFull.getChildren().add(cbxOfficerUniformed);

		cbxOfficerTreatment = new CheckBox("Officer Recieved Medical Treatment");
		vbxOfficerFull.getChildren().add(cbxOfficerTreatment);
		
		vbxOfficerFull.getChildren().add(new Label("Describe Injuries to Officer"));
		vbxOfficerFull.getChildren().get(injuresLabelIndex).setVisible(false);
		txaOfficerInjuriesDesc = new TextArea();
		txaOfficerInjuriesDesc.setPrefSize(200, 150);
		txaOfficerInjuriesDesc.setVisible(false);
		FormUtil.toggleTextInputWithLabelEvent(cbxOfficerInjured, txaOfficerInjuriesDesc,
				 								  vbxOfficerFull.getChildren().get(injuresLabelIndex));
		vbxOfficerFull.getChildren().add(txaOfficerInjuriesDesc);
	}
	
	private void createIncidentInfo() {
		vbxIncidentFull = new VBox();
		vbxIncidentFull.setAlignment(Pos.TOP_CENTER);
		hbxReport.getChildren().add(vbxIncidentFull);

		txtIncidentInfo = new Text("Incident Information");
		txtIncidentInfo.setTextAlignment(TextAlignment.CENTER);
		vbxIncidentFull.getChildren().add(txtIncidentInfo);

		createIncidentGeneralInfo();

		createIncidentTypeInfo();
	}

	private void createIncidentGeneralInfo() {
		grdIncidentInfo = new GridPane();
		vbxIncidentFull.getChildren().add(grdIncidentInfo);
		
		grdIncidentInfo.add(new Label("Incident Number"), 0, 0);
		txfIncidentNumber = new TextField(""+(sql.getLastCaseID()+1));
		txfIncidentNumber.setDisable(true);
		txfIncidentNumber.setOpacity(0.7);
		grdIncidentInfo.add(txfIncidentNumber, 1, 0);

		grdIncidentInfo.add(new Label("Incident Date"), 0, 1);
		dtpIncidentDate = new DatePicker();
		grdIncidentInfo.add(dtpIncidentDate, 1, 1);
		
		grdIncidentInfo.add(new Label("Incident Time"), 0, 2);
		hbxTime = new HBox();
		grdIncidentInfo.add(hbxTime, 1, 2);
		
		createTimeSelect();
		
		grdIncidentInfo.add(new Label("Incident Location"), 0, 3);
		txfIncidentLocation = new TextField();
		grdIncidentInfo.add(txfIncidentLocation, 1, 3);
		
		grdIncidentInfo.add(new Label("Incident Type"), 0, 4);
		vbxIncidentType = new VBox();
		grdIncidentInfo.add(vbxIncidentType, 1, 4);
	}

	private void createTimeSelect() {
		cobHour = new ComboBox<String>();
		for(int i=1; i<=12; i++) {
			cobHour.getItems().add("" + i);
		}
		hbxTime.getChildren().add(cobHour);
		
		hbxTime.getChildren().add(new Label(" : "));
		cobMinutes = new ComboBox<String>();
		for(int i=0; i<=59; i++) {
			if(i < 10) {
				cobMinutes.getItems().add("0" + i);
			}
			else {
				cobMinutes.getItems().add("" + i);
			}
		}
		hbxTime.getChildren().add(cobMinutes);
		
		hbxTime.getChildren().add(new Label(" : "));
		cobSeconds = new ComboBox<String>();
		for(int i=0; i<=59; i++) {
			if(i < 10) {
				cobSeconds.getItems().add("0" + i);
			}
			else {
				cobSeconds.getItems().add("" + i);
			}
		}
		hbxTime.getChildren().add(cobSeconds);
		
		hbxTime.getChildren().add(new Label(" "));
		cobAmPm = new ComboBox<String>();
		cobAmPm.getItems().addAll("AM","PM");
		hbxTime.getChildren().add(cobAmPm);
	}

	private void createIncidentTypeInfo() {
		int otherIndex = 5;
		
		tgrType = new ToggleGroup();

		rdbInProgress = new RadioButton("Crime In Progress");
		rdbInProgress.setToggleGroup(tgrType);
		vbxIncidentType.getChildren().add(rdbInProgress);

		rdbDomestic = new RadioButton("Domestic");
		rdbDomestic.setToggleGroup(tgrType);
		vbxIncidentType.getChildren().add(rdbDomestic);

		rdbSuspicious = new RadioButton("Suspicious Person");
		rdbSuspicious.setToggleGroup(tgrType);
		vbxIncidentType.getChildren().add(rdbSuspicious);

		rdbTrafficStop = new RadioButton("Traffic Stop");
		rdbTrafficStop.setToggleGroup(tgrType);
		vbxIncidentType.getChildren().add(rdbTrafficStop);

		rdbArrest = new RadioButton("Arrest");
		rdbArrest.setToggleGroup(tgrType);
		vbxIncidentType.getChildren().add(rdbArrest);

		rdbOtherType = new RadioButton("Other");
		rdbOtherType.setToggleGroup(tgrType);
		vbxIncidentType.getChildren().add(rdbOtherType);

		txfOtherDesc = new TextField();
		txfOtherDesc.setVisible(false);
		FormUtil.toggleTextFieldFromRadioButtonEvent(tgrType, otherIndex, txfOtherDesc);
		vbxIncidentType.getChildren().add(txfOtherDesc);
	}
	
	private void createSubjectSelector() {
		txtSubjects = new Text("Subjects");
		txtSubjects.setTextAlignment(TextAlignment.CENTER);
		vbxIncidentFull.getChildren().add(txtSubjects);

		createSubjectInteractor();

		createSubjectSelectBox();
	}
	
	private void createSubjectInteractor() {
		hbxSubjectInteract = new HBox();
		hbxSubjectInteract.setAlignment(Pos.CENTER);
		vbxIncidentFull.getChildren().add(hbxSubjectInteract);

		btnCreateSubject = new Button("Create Subject");
		makeSubjectEvent();
		hbxSubjectInteract.getChildren().add(btnCreateSubject);

		btnDeleteSubject = new Button("Delete Subject");
		deleteSelectedSubjectEvent();
		hbxSubjectInteract.getChildren().add(btnDeleteSubject);

		btnEditSubject = new Button("Edit Subject");
		editSelectedSubjectEvent();
		hbxSubjectInteract.getChildren().add(btnEditSubject);
	}

	private void makeSubjectEvent() {
		btnCreateSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int lastPos = cobSubjectChoice.getItems().size();
            	cobSubjectChoice.getItems().add("Subject " + (lastPos + 1));
            	incident.subjects.add(new Subject());
            }
        });
	}

	private void deleteSelectedSubjectEvent() {
		btnDeleteSubject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int lastPos = cobSubjectChoice.getItems().size();
				if (lastPos >= 2) {
					int index = FormUtil.getCurrentIndex(cobSubjectChoice);
					incident.subjects.remove(incident.subjects.get(index));
					cobSubjectChoice.getItems().remove(index);
					for (int i = index; i < lastPos-1; i++) {
						cobSubjectChoice.getItems().set(i, "Subject " + (i + 1));
					}
					cobSubjectChoice.getSelectionModel().select(0);
				}
			}
		});
	}

	private void editSelectedSubjectEvent() {
		btnEditSubject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new UOFSubjectForm().run(incident.subjects.get(FormUtil.getCurrentIndex(cobSubjectChoice)));
			}
		});
	}
	
	private void createSubjectSelectBox() {
		vbxIncidentFull.getChildren().add(new Label("Choose a Subject"));

		cobSubjectChoice = new ComboBox<String>();
		cobSubjectChoice.getItems().add("Subject 1");
		cobSubjectChoice.getSelectionModel().select(0);
		vbxIncidentFull.getChildren().add(cobSubjectChoice);
	}
	
	private void createSubmitArea() {
		vbxSubmit = new VBox();
		vbxSubmit.setAlignment(Pos.CENTER);
		vbxIncidentFull.getChildren().add(vbxSubmit);

		btnSubmit = new Button("Finish and Submit");
		submitIncidentEvent();
		vbxSubmit.getChildren().add(btnSubmit);
		
		btnQuit = new Button("Quit");
		quitIncidentEvent();
		vbxSubmit.getChildren().add(btnQuit);
	}

	private void submitIncidentEvent() {
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					fillIncidentFromForm();
				}
				catch(IllegalArgumentException e) {
					return;
				}
				sql.insertNewForm(incident);
				FForce.window.setScene(FForce.homePageScene);
			}
		});
	}
	
	private void quitIncidentEvent() {
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FForce.window.setScene(FForce.homePageScene);
			}
		});
	}
	
	private void fillIncidentFromForm() {
		Officer officer = incident.officer;
		officer.info.badgeNumber = FormUtil.textToInteger(txfBadgeNumber.getText());
		officer.info.firstName = FormUtil.cleanInput(txfFirstName.getText());
		officer.info.middleName = FormUtil.cleanInput(txfMiddleName.getText());
		officer.info.lastName = FormUtil.cleanInput(txfLastName.getText());
		officer.info.sex = FormUtil.cleanInput(txfSex.getText());
		officer.info.race = FormUtil.cleanInput(txfRace.getText());
		officer.info.dateOfBirth = FormUtil.datePickerToDate(dtpDOB);
		officer.info.serviceStart = FormUtil.datePickerToDate(dtpServiceStart);
		officer.info.rank = FormUtil.cleanInput(txfRank.getText());
		officer.info.duty = FormUtil.cleanInput(txfDutyAssignment.getText());
		officer.wasInjured = cbxOfficerInjured.isSelected();
		officer.wasKilled = cbxOfficerKilled.isSelected();
		officer.wasOnDuty = cbxOfficerOnDuty.isSelected();
		officer.wasUniformed = cbxOfficerUniformed.isSelected();
		officer.hadMedicalTreatment = cbxOfficerTreatment.isSelected();
		officer.injuries = FormUtil.cleanInput(txaOfficerInjuriesDesc.getText());
		incident.id = FormUtil.textToInteger(txfIncidentNumber.getText());
		incident.incidentDate = FormUtil.datePickerToDate(dtpIncidentDate);
		incident.incidentDate = FormUtil.addMinorTimeFromText(incident.incidentDate, cobHour.getValue(), cobMinutes.getValue(),
																cobSeconds.getValue(), cobAmPm.getValue());
		incident.location = FormUtil.cleanInput(txfIncidentLocation.getText());
		incident.type = ((RadioButton)tgrType.getSelectedToggle()).getText();
		incident.otherType = txfOtherDesc.getText();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		UOFIncidentForm incidentStage = new UOFIncidentForm();
		incidentStage.create(primaryStage);
		primaryStage.show();
	}

}