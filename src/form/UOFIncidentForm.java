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
import javafx.scene.Node;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;

public class UOFIncidentForm extends Application {

	private Incident incident;
	
	private Pane pneReport;
	private HBox hbxReport, hbxTime, hbxSubjectInteract;
	private VBox vbxOfficerFull, vbxIncidentFull, vbxIncidentType;
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
	private Button btnCreateSubject, btnDeleteSubject, btnEditSubject, btnSubmit;
	private ComboBox<String> cobAmPm, cobHour, cobMinutes, cobSeconds, cobSubjectChoice;
	private StackPane spaSubmit;
	
	public static void run() {
		launch();
	}

	public Stage create(Stage form) {

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
		grdOfficerInfo = new GridPane();
		vbxOfficerFull.getChildren().add(grdOfficerInfo);
		
		grdOfficerInfo.add(new Label("Badge Number"), 0, 0);
		txfBadgeNumber = new TextField();
		grdOfficerInfo.add(txfBadgeNumber, 1, 0);
		
		grdOfficerInfo.add(new Label("First Name"), 0, 1);
		txfFirstName = new TextField();
		grdOfficerInfo.add(txfFirstName, 1, 1);
		
		grdOfficerInfo.add(new Label("Middle Name"), 0, 2);
		txfMiddleName = new TextField();
		grdOfficerInfo.add(txfMiddleName, 1, 2);
		
		grdOfficerInfo.add(new Label("Last Name"), 0, 3);
		txfLastName = new TextField();
		grdOfficerInfo.add(txfLastName, 1, 3);
		
		grdOfficerInfo.add(new Label("Sex"), 0, 4);
		txfSex = new TextField();
		grdOfficerInfo.add(txfSex, 1, 4);
		
		grdOfficerInfo.add(new Label("Race"), 0, 5);
		txfRace = new TextField();
		grdOfficerInfo.add(txfRace, 1, 5);
		
		grdOfficerInfo.add(new Label("Date Of Birth"), 0, 6);
		dtpDOB = new DatePicker();
		grdOfficerInfo.add(dtpDOB, 1, 6);
		
		grdOfficerInfo.add(new Label("Started Service"), 0, 7);
		dtpServiceStart = new DatePicker();
		grdOfficerInfo.add(dtpServiceStart, 1, 7);
		
		grdOfficerInfo.add(new Label("Rank"), 0, 8);
		txfRank = new TextField();
		grdOfficerInfo.add(txfRank, 1, 8);
		
		grdOfficerInfo.add(new Label("Duty Assignment"), 0, 9);
		txfDutyAssignment = new TextField();
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
		UOFFormUtil.toggleTextAreaWithLabelEvent(cbxOfficerInjured, txaOfficerInjuriesDesc,
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
		txfIncidentNumber = new TextField();
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
		for(int i=0; i<tgrType.getToggles().size(); i++) {
			UOFFormUtil.toggleTextFieldFromRadioButtonEvent(tgrType.getToggles().get(i), 5, txfOtherDesc);
		}
		
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
					int index = UOFFormUtil.getCurrentIndex(cobSubjectChoice);
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
				new UOFSubjectForm().run(incident.subjects.get(UOFFormUtil.getCurrentIndex(cobSubjectChoice)));
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
		spaSubmit = new StackPane();
		vbxIncidentFull.getChildren().add(spaSubmit);

		btnSubmit = new Button("Finish and Submit");
		submitIncidentEvent();
		spaSubmit.getChildren().add(btnSubmit);
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
				try {
					new SQL().insertNewForm(incident);
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				FForce.window.setScene(FForce.homePageScene);
			}
		});
	}
	
	private void fillIncidentFromForm() {
		Officer officer = incident.officer;
		officer.badgeNumber = UOFFormUtil.textToInteger(txfBadgeNumber.getText());
		officer.firstName = UOFFormUtil.cleanInput(txfFirstName.getText());
		officer.middleName = UOFFormUtil.cleanInput(txfMiddleName.getText());
		officer.lastName = UOFFormUtil.cleanInput(txfLastName.getText());
		officer.sex = UOFFormUtil.cleanInput(txfSex.getText());
		officer.race = UOFFormUtil.cleanInput(txfRace.getText());
		officer.dateOfBirth = UOFFormUtil.datePickerToDate(dtpDOB);
		officer.serviceStart = UOFFormUtil.datePickerToDate(dtpServiceStart);
		officer.rank = UOFFormUtil.cleanInput(txfRank.getText());
		officer.duty = UOFFormUtil.cleanInput(txfDutyAssignment.getText());
		officer.wasInjured = cbxOfficerInjured.isSelected();
		officer.wasKilled = cbxOfficerKilled.isSelected();
		officer.wasOnDuty = cbxOfficerOnDuty.isSelected();
		officer.wasUniformed = cbxOfficerUniformed.isSelected();
		officer.hadMedicalTreatment = cbxOfficerTreatment.isSelected();
		officer.injuries = UOFFormUtil.cleanInput(txaOfficerInjuriesDesc.getText());
		incident.incidentDate = UOFFormUtil.datePickerToDate(dtpIncidentDate);
		incident.incidentDate = UOFFormUtil.addMinorTimeFromText(incident.incidentDate, cobHour.getValue(), cobMinutes.getValue(),
																cobSeconds.getValue(), cobAmPm.getValue());
		System.out.println(incident.incidentDate);
		incident.location = UOFFormUtil.cleanInput(txfIncidentLocation.getText());
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