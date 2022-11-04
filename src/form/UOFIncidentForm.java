package form;

import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;

public class UOFIncidentForm extends Application {

	private Incident incident;
	private Pane pneReport;
	private HBox hbxReport;
	private VBox vbxOfficerFull;
	private Text txtOfficerInfo;
	private GridPane grdOfficerInfo;
	private TextField txfBadgeNumber;
	private TextField txfFirstName;
	private TextField txfMiddleName;
	private TextField txfLastName;
	private TextField txfSex;
	private TextField txfRace;
	private DatePicker dtpDOB;
	private DatePicker dtpServiceStart;
	private TextField txfRank;
	private TextField txfDutyAssignment;
	private CheckBox cbxOfficerInjured;
	private CheckBox cbxOfficerKilled;
	private CheckBox cbxOfficerOnDuty;
	private CheckBox cbxOfficerUniformed;
	private CheckBox cbxOfficerTreatment;
	private TextArea txaOfficerInjuriesDesc;
	private VBox vbxIncidentFull;
	private Text txtIncidentInfo;
	private GridPane grdIncidentInfo;
	private TextField txfIncidentNumber;
	private DatePicker dtpIncidentDate;
	private TextField txfIncidentLocation;
	private VBox vbxIncidentType;
	private static ToggleGroup tgrType;
	private RadioButton rdbInProgress;
	private RadioButton rdbDomestic;
	private RadioButton rdbSuspicious;
	private RadioButton rdbTrafficStop;
	private RadioButton rdbArrest;
	private RadioButton rdbOtherType;
	private TextField txfOtherDesc;
	private Text txtSubjects;
	private HBox hbxSubjectInteract;
	private Button btnCreateSubject;
	private Button btnDeleteSubject;
	private Button btnEditSubject;
	private ComboBox<String> cbxSubjectChoice;
	private StackPane spaSubmit;
	private Button btnSubmit;
	
	public static void run() {
		launch();
	}

	public Stage create(Stage form) {

		incident = new Incident();

		form.setTitle("Furman University Police Use of Force Report");
		form.setResizable(false);

		pneReport = new Pane();
		form.setScene(new Scene(pneReport, 680, 610));

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
		txaOfficerInjuriesDesc = new TextArea();
		txaOfficerInjuriesDesc.setPrefSize(200, 150);
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
		
		grdIncidentInfo.add(new Label("Incident Location"), 0, 2);
		txfIncidentLocation = new TextField();
		grdIncidentInfo.add(txfIncidentLocation, 1, 2);
		
		grdIncidentInfo.add(new Label("Incident Type"), 0, 3);
		vbxIncidentType = new VBox();
		grdIncidentInfo.add(vbxIncidentType, 1, 3);
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
            	int lastPos = cbxSubjectChoice.getItems().size();
            	cbxSubjectChoice.getItems().add("Subject " + (lastPos + 1));
            	incident.subjects.add(new Subject());
            }
        });
	}

	private void deleteSelectedSubjectEvent() {
		btnDeleteSubject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int lastPos = cbxSubjectChoice.getItems().size();
				if (lastPos >= 2) {
					int index = getCurrentIndex();
					incident.subjects.remove(incident.subjects.get(index));
					cbxSubjectChoice.getItems().remove(index);
					for (int i = index; i < lastPos-1; i++) {
						cbxSubjectChoice.getItems().set(i, "Subject " + (i + 1));
					}
					cbxSubjectChoice.getSelectionModel().select(0);
				}
			}
		});
	}

	private void editSelectedSubjectEvent() {
		btnEditSubject.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new UOFSubjectForm().run(incident.subjects.get(getCurrentIndex()));
			}
		});
	}
	
	private void createSubjectSelectBox() {
		vbxIncidentFull.getChildren().add(new Label("Choose a Subject"));

		cbxSubjectChoice = new ComboBox<String>();
		cbxSubjectChoice.getItems().add("Subject 1");
		cbxSubjectChoice.getSelectionModel().select(0);
		vbxIncidentFull.getChildren().add(cbxSubjectChoice);
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
				//FForce.window.setScene(FForce.homePageScene);
			}
		});
	}
	
	private void fillIncidentFromForm() {
		Officer officer = incident.officer;
		
		officer.firstName = cleanInput(txfBadgeNumber.getText());
		officer.middleName = cleanInput(txfFirstName.getText());
		officer.lastName = cleanInput(txfMiddleName.getText());
		officer.sex = cleanInput(txfSex.getText());
		officer.race = cleanInput(txfRace.getText());
		officer.dateOfBirth = datePickerToDate(dtpDOB);
		officer.serviceStart = datePickerToDate(dtpServiceStart);
		officer.rank = cleanInput(txfRank.getText());
		officer.duty = cleanInput(txfDutyAssignment.getText());
		officer.wasInjured = cbxOfficerInjured.isSelected();
		officer.wasKilled = cbxOfficerKilled.isSelected();
		officer.wasOnDuty = cbxOfficerOnDuty.isSelected();
		officer.wasUniformed = cbxOfficerUniformed.isSelected();
		officer.hadMedicalTreatment = cbxOfficerTreatment.isSelected();
		officer.injuries = cleanInput(txaOfficerInjuriesDesc.getText());
		incident.incidentNumber = textFieldToInt(txfIncidentNumber);
		incident.incidentDate = datePickerToDate(dtpIncidentDate);
		incident.location = cleanInput(txfIncidentLocation.getText());
		incident.type = ((RadioButton)tgrType.getSelectedToggle()).getText();
		incident.otherType = txfOtherDesc.getText();
	}

	private Date datePickerToDate(DatePicker dtp) {
		Date date = new Date();
		try {
    		date = Date.from(dtp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    		return date;
    	}
    	catch(Exception e){
    		dtp.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    		throw new IllegalArgumentException();
    	}
	}
	private int textFieldToInt(TextField txf) {
		int output;
		try {
			output = Integer.parseInt(cleanInput(txf.getText()));
			return output;
		}
		catch(Exception e){
			txf.clear();
			throw new IllegalArgumentException();
		}
	}
	
	private String cleanInput(String input) {
		if(input == null) {
			input = "";
		}
		return input;
	}

	private int getCurrentIndex() {
		int index = 0;
		for (int i = 0; i < cbxSubjectChoice.getItems().size(); i++) {
			if (cbxSubjectChoice.getSelectionModel().isSelected(i)) {
				index = i;
			}
		}
		return index;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		UOFIncidentForm incidentStage = new UOFIncidentForm();
		incidentStage.create(primaryStage);
		primaryStage.show();
	}

}