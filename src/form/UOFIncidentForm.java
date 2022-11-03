package form;

import java.util.Date;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;

public class UOFIncidentForm extends Application {

	private Incident report;
	private Pane pneReport;
	private HBox hbxReport;
	private VBox vbxOfficerFull;
	private Text txtOfficerInfo;
	private GridPane grdOfficerInfo;
	private Label lblBadgeNumber;
	private TextField txfBadgeNumber;
	private Label lblFirstName;
	private TextField txfFirstName;
	private Label lblMiddleName;
	private TextField txfMiddleName;
	private Label lblLastName;
	private TextField txfLastName;
	private Label lblSex;
	private TextField txfSex;
	private Label lblRace;
	private TextField txfRace;
	private Label lblDOB;
	private DatePicker dtpDOB;
	private Label lblServiceStart;
	private DatePicker dtpServiceStart;
	private Label lblRank;
	private TextField txfRank;
	private Label lblDutyAssignment;
	private TextField txfDutyAssignment;
	private CheckBox cbxOfficerInjured;
	private CheckBox cbxOfficerKilled;
	private CheckBox cbxOfficerOnDuty;
	private CheckBox cbxOfficerUniformed;
	private CheckBox cbxOfficerTreatment;
	private Label lblOfficerInjuriesDesc;
	private TextArea txaOfficerInjuriesDesc;
	private Separator sprOfficerIncident;
	private VBox vbxIncidentFull;
	private Text txtIncidentInfo;
	private GridPane grdIncidentInfo;
	private Label lblIncidentNumber;
	private TextField txfIncidentNumber;
	private Label lblIncidentDate;
	private DatePicker dtpIncidentDate;
	private Label lblIncidentLocation;
	private TextField txfIncidentLocation;
	private Label lblIncidentType;
	private VBox vbxIncidentType;
	private static ToggleGroup tgrType;
	private RadioButton rdbInProgress;
	private RadioButton rdbDomestic;
	private RadioButton rdbSuspicious;
	private RadioButton rdbTrafficStop;
	private RadioButton rdbArrest;
	private RadioButton rdbOtherType;
	private TextField txfOtherDesc;
	private Separator sprIncidentSubject;
	private Text txtSubjects;
	private HBox hbxSubjectInteract;
	private Button btnCreateSubject;
	private Button btnDeleteSubject;
	private Button btnEditSubject;
	private Label lblSubjectChoice;
	private ComboBox<String> cbxSubjectChoice;
	private Separator sprSubjectSubmit;
	private StackPane spaSubmit;
	private Button btnSubmit;
	
	public static void create() {
		launch();
    }
	
	@Override
	public void start(Stage form) {
		
		report = new Incident();
		
		form.setTitle("Furman University Police Use of Force Report");
		form.setResizable(false);
		
		pneReport = new Pane();
		form.setScene(new Scene(pneReport,680,610));
		
		hbxReport = new HBox();
		pneReport.getChildren().add(hbxReport);
		
		vbxOfficerFull = new VBox();
		hbxReport.getChildren().add(vbxOfficerFull);
		
		txtOfficerInfo = new Text();
		txtOfficerInfo.setTextAlignment(TextAlignment.CENTER);
		txtOfficerInfo.setText("Officer Information");
		txtOfficerInfo.setWrappingWidth(322.0);
		vbxOfficerFull.getChildren().add(txtOfficerInfo);
		
		grdOfficerInfo = new GridPane();
		vbxOfficerFull.getChildren().add(grdOfficerInfo);
		
		lblBadgeNumber = new Label();
		lblBadgeNumber.setText("Badge Number");
		grdOfficerInfo.add(lblBadgeNumber, 0, 0);
		txfBadgeNumber = new TextField();
		grdOfficerInfo.add(txfBadgeNumber, 1, 0);
		
		lblFirstName = new Label();
		lblFirstName.setText("First Name");
		grdOfficerInfo.add(lblFirstName, 0, 1);
		txfFirstName = new TextField();
		grdOfficerInfo.add(txfFirstName, 1, 1);

		lblMiddleName = new Label();
		lblMiddleName.setText("Middle Name");
		grdOfficerInfo.add(lblMiddleName, 0, 2);
		txfMiddleName = new TextField();
		grdOfficerInfo.add(txfMiddleName, 1, 2);

		lblLastName = new Label();
		lblLastName.setText("Last Name");
		grdOfficerInfo.add(lblLastName, 0, 3);
		txfLastName = new TextField();
		grdOfficerInfo.add(txfLastName, 1, 3);

		lblSex = new Label();
		lblSex.setText("Sex");
		grdOfficerInfo.add(lblSex, 0, 4);
		txfSex = new TextField();
		grdOfficerInfo.add(txfSex, 1, 4);

		lblRace = new Label();
		lblRace.setText("Race");
		grdOfficerInfo.add(lblRace, 0, 5);
		txfRace = new TextField();
		grdOfficerInfo.add(txfRace, 1, 5);

		lblDOB = new Label();
		lblDOB.setText("Date Of Birth");
		grdOfficerInfo.add(lblDOB, 0, 6);
		dtpDOB = new DatePicker();
		grdOfficerInfo.add(dtpDOB, 1, 6);

		lblServiceStart = new Label();
		lblServiceStart.setText("Started Service");
		grdOfficerInfo.add(lblServiceStart, 0, 7);
		dtpServiceStart = new DatePicker();
		grdOfficerInfo.add(dtpServiceStart, 1, 7);

		lblRank = new Label();
		lblRank.setText("Rank");
		grdOfficerInfo.add(lblRank, 0, 8);
		txfRank = new TextField();
		grdOfficerInfo.add(txfRank, 1, 8);
		
		lblDutyAssignment = new Label();
		lblDutyAssignment.setText("Duty Assignment");
		grdOfficerInfo.add(lblDutyAssignment, 0, 9);
		txfDutyAssignment = new TextField();
		grdOfficerInfo.add(txfDutyAssignment, 1, 9);
		
		cbxOfficerInjured = new CheckBox();
		cbxOfficerInjured.setText("Officer Was Injured");
		vbxOfficerFull.getChildren().add(cbxOfficerInjured);
		
		cbxOfficerKilled = new CheckBox();
		cbxOfficerKilled.setText("Officer Was Killed");
		vbxOfficerFull.getChildren().add(cbxOfficerKilled);

		cbxOfficerOnDuty = new CheckBox();
		cbxOfficerOnDuty.setText("Officer Was On-Duty");
		vbxOfficerFull.getChildren().add(cbxOfficerOnDuty);
		
		cbxOfficerUniformed = new CheckBox();
		cbxOfficerUniformed.setText("Officer was Uniformed");
		vbxOfficerFull.getChildren().add(cbxOfficerUniformed);
		
		cbxOfficerTreatment = new CheckBox();
		cbxOfficerTreatment.setText("Officer Recieved Medical Treatment");
		vbxOfficerFull.getChildren().add(cbxOfficerTreatment);
		
		lblOfficerInjuriesDesc = new Label();
		lblOfficerInjuriesDesc.setText("Describe Injuries to Officer");
		vbxOfficerFull.getChildren().add(lblOfficerInjuriesDesc);
		txaOfficerInjuriesDesc = new TextArea();
		txaOfficerInjuriesDesc.setPrefWidth(200);
		txaOfficerInjuriesDesc.setPrefHeight(150);
		vbxOfficerFull.getChildren().add(txaOfficerInjuriesDesc);
		
		sprOfficerIncident = new Separator();
		sprOfficerIncident.setOrientation(Orientation.VERTICAL);
		hbxReport.getChildren().add(sprOfficerIncident);
		
		vbxIncidentFull = new VBox();
		vbxIncidentFull.setAlignment(Pos.TOP_CENTER);
		hbxReport.getChildren().add(vbxIncidentFull);
		
		txtIncidentInfo = new Text();
		txtIncidentInfo.setTextAlignment(TextAlignment.CENTER);
		txtIncidentInfo.setText("Incident Information");
		vbxIncidentFull.getChildren().add(txtIncidentInfo);
		
		grdIncidentInfo = new GridPane();
		vbxIncidentFull.getChildren().add(grdIncidentInfo);
		
		lblIncidentNumber = new Label();
		lblIncidentNumber.setText("Incident Number");
		grdIncidentInfo.add(lblIncidentNumber, 0, 0);
		txfIncidentNumber = new TextField();
		grdIncidentInfo.add(txfIncidentNumber, 1, 0);
		
		lblIncidentDate = new Label();
		lblIncidentDate.setText("Incident Date");
		grdIncidentInfo.add(lblIncidentDate, 0, 1);
		dtpIncidentDate = new DatePicker();
		grdIncidentInfo.add(dtpIncidentDate, 1, 1);
		
		lblIncidentLocation = new Label();
		lblIncidentLocation.setText("Incident Location");
		grdIncidentInfo.add(lblIncidentLocation, 0, 2);
		txfIncidentLocation = new TextField();
		grdIncidentInfo.add(txfIncidentLocation, 1, 2);
		
		lblIncidentType = new Label();
		lblIncidentType.setText("Incident Type");
		grdIncidentInfo.add(lblIncidentType, 0, 3);
		vbxIncidentType = new VBox();
		grdIncidentInfo.add(vbxIncidentType, 1, 3);
		
		tgrType = new ToggleGroup();
		
		rdbInProgress = new RadioButton();
		rdbInProgress.setToggleGroup(tgrType);
		rdbInProgress.setText("Crime In Progress");
		vbxIncidentType.getChildren().add(rdbInProgress);
		
		rdbDomestic = new RadioButton();
		rdbDomestic.setToggleGroup(tgrType);
		rdbDomestic.setText("Domestic");
		vbxIncidentType.getChildren().add(rdbDomestic);
		
		rdbSuspicious = new RadioButton();
		rdbSuspicious.setToggleGroup(tgrType);
		rdbSuspicious.setText("Suspicious Person");
		vbxIncidentType.getChildren().add(rdbSuspicious);
		
		rdbTrafficStop = new RadioButton();
		rdbTrafficStop.setToggleGroup(tgrType);
		rdbTrafficStop.setText("Traffic Stop");
		vbxIncidentType.getChildren().add(rdbTrafficStop);
		
		rdbArrest = new RadioButton();
		rdbArrest.setToggleGroup(tgrType);
		rdbArrest.setText("Arrest");
		vbxIncidentType.getChildren().add(rdbArrest);
		
		rdbOtherType = new RadioButton();
		rdbOtherType.setToggleGroup(tgrType);
		rdbOtherType.setText("Other");
		vbxIncidentType.getChildren().add(rdbOtherType);
		
		txfOtherDesc = new TextField();
		vbxIncidentType.getChildren().add(txfOtherDesc);
		
		sprIncidentSubject = new Separator();
		vbxIncidentFull.getChildren().add(sprIncidentSubject);
		
		txtSubjects = new Text();
		txtSubjects.setTextAlignment(TextAlignment.CENTER);
		txtSubjects.setText("Subjects");
		vbxIncidentFull.getChildren().add(txtSubjects);
		
		hbxSubjectInteract = new HBox();
		hbxSubjectInteract.setAlignment(Pos.CENTER);
		vbxIncidentFull.getChildren().add(hbxSubjectInteract);
		
		btnCreateSubject = new Button();
		btnCreateSubject.setText("Create Subject");
		btnCreateSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int lastPos = cbxSubjectChoice.getItems().size();
            	cbxSubjectChoice.getItems().add("Subject " + (lastPos + 1));
            	report.subjects.add(new Subject());
            }
        });
		hbxSubjectInteract.getChildren().add(btnCreateSubject);
		
		btnDeleteSubject = new Button();
		btnDeleteSubject.setText("Delete Subject");
		btnDeleteSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int lastPos = cbxSubjectChoice.getItems().size();
            	if(lastPos >= 2 ) {
                	int index = getCurrentBoxIndex();
                	report.subjects.remove(report.subjects.get(index));
                	cbxSubjectChoice.getItems().remove(index);
                	lastPos--;
                	for(int i=index; i<lastPos; i++) {
                		cbxSubjectChoice.getItems().set(i, "Subject " + (i + 1));
                	}
                	cbxSubjectChoice.getSelectionModel().select(0);
            	}
            }
        });
		hbxSubjectInteract.getChildren().add(btnDeleteSubject);
		
		btnEditSubject = new Button();
		btnEditSubject.setText("Edit Subject");
		btnEditSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UOFSubjectForm currentSubjectForm = new UOFSubjectForm();
                int index = getCurrentBoxIndex();
                currentSubjectForm.run(report.subjects.get(index));
            }	
        });
		hbxSubjectInteract.getChildren().add(btnEditSubject);		
		
		lblSubjectChoice = new Label();
		lblSubjectChoice.setContentDisplay(ContentDisplay.CENTER);
		lblSubjectChoice.setText("Choose A Subject");
		lblSubjectChoice.setAlignment(Pos.CENTER);
		vbxIncidentFull.getChildren().add(lblSubjectChoice);
		
		cbxSubjectChoice = new ComboBox<String>();
		cbxSubjectChoice.getItems().add("Subject 1");
		cbxSubjectChoice.getSelectionModel().select(0);
		vbxIncidentFull.getChildren().add(cbxSubjectChoice);
		
		sprSubjectSubmit = new Separator();
		vbxIncidentFull.getChildren().add(sprSubjectSubmit);
		
		spaSubmit = new StackPane();
		vbxIncidentFull.getChildren().add(spaSubmit);
		
		btnSubmit = new Button();
		btnSubmit.setText("Finish and Submit");
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) { }
        });
		spaSubmit.getChildren().add(btnSubmit);

		form.show();
	}
	
	private int getCurrentBoxIndex() {
		int index = 0;
        for(int i = 0; i < cbxSubjectChoice.getItems().size(); i++) {
        	if(cbxSubjectChoice.getSelectionModel().isSelected(i)) {
        		index = i;
        	}
        }
		return index;
	}
	
}