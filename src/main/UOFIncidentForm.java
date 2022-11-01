package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;

public class UOFIncidentForm extends Application {

	public static void main(String[] args) {
        launch(args);
    }
	
	@Override
	public void start(Stage form) {
		
		form.setTitle("Furman University Police Use of Force Report");
		form.setResizable(false);
		
		Pane pneReport = new Pane();
		form.setScene(new Scene(pneReport,680,610));
		
		HBox hbxReport = new HBox();
		pneReport.getChildren().add(hbxReport);
		
		VBox vbxOfficerFull = new VBox();
		hbxReport.getChildren().add(vbxOfficerFull);
		
		Text txtOfficerInfo = new Text();
		txtOfficerInfo.setStrokeWidth(0.0);
		txtOfficerInfo.setStrokeType(StrokeType.OUTSIDE);
		txtOfficerInfo.setTextAlignment(TextAlignment.CENTER);
		txtOfficerInfo.setText("Officer Information");
		txtOfficerInfo.setWrappingWidth(322.0);
		vbxOfficerFull.getChildren().add(txtOfficerInfo);
		
		GridPane grdOfficerInfo = new GridPane();
		vbxOfficerFull.getChildren().add(grdOfficerInfo);
		
		Label lblBadgeNumber = new Label();
		lblBadgeNumber.setText("Badge Number");
		grdOfficerInfo.add(lblBadgeNumber, 0, 0);
		TextField txfBadgeNumber = new TextField();
		grdOfficerInfo.add(txfBadgeNumber, 1, 0);
		
		Label lblFirstName = new Label();
		lblFirstName.setText("First Name");
		grdOfficerInfo.add(lblFirstName, 0, 1);
		TextField txfFirstName = new TextField();
		grdOfficerInfo.add(txfFirstName, 1, 1);

		Label lblMiddleName = new Label();
		lblMiddleName.setText("Middle Name");
		grdOfficerInfo.add(lblMiddleName, 0, 2);
		TextField txfMiddleName = new TextField();
		grdOfficerInfo.add(txfMiddleName, 1, 2);

		Label lblLastName = new Label();
		lblLastName.setText("Last Name");
		grdOfficerInfo.add(lblLastName, 0, 3);
		TextField txfLastName = new TextField();
		grdOfficerInfo.add(txfLastName, 1, 3);

		Label lblSex = new Label();
		lblSex.setText("Sex");
		grdOfficerInfo.add(lblSex, 0, 4);
		TextField txfSex = new TextField();
		grdOfficerInfo.add(txfSex, 1, 4);

		Label lblRace = new Label();
		lblRace.setText("Race");
		grdOfficerInfo.add(lblRace, 0, 5);
		TextField txfRace = new TextField();
		grdOfficerInfo.add(txfRace, 1, 5);

		Label lblDOB = new Label();
		lblDOB.setText("Date Of Birth");
		grdOfficerInfo.add(lblDOB, 0, 6);
		DatePicker dtpDOB = new DatePicker();
		grdOfficerInfo.add(dtpDOB, 1, 6);

		Label lblServiceStart = new Label();
		lblServiceStart.setText("Started Service");
		grdOfficerInfo.add(lblServiceStart, 0, 7);
		DatePicker dtpServiceStart = new DatePicker();
		grdOfficerInfo.add(dtpServiceStart, 1, 7);

		Label lblRank = new Label();
		lblRank.setText("Rank");
		grdOfficerInfo.add(lblRank, 0, 8);
		TextField txfRank = new TextField();
		grdOfficerInfo.add(txfRank, 1, 8);
		
		Label lblDutyAssignment = new Label();
		lblDutyAssignment.setText("Duty Assignment");
		grdOfficerInfo.add(lblDutyAssignment, 0, 9);
		TextField txfDutyAssignment = new TextField();
		grdOfficerInfo.add(txfDutyAssignment, 1, 9);
		
		CheckBox cbxOfficerInjured = new CheckBox();
		cbxOfficerInjured.setText("Officer Was Injured");
		vbxOfficerFull.getChildren().add(cbxOfficerInjured);
		
		CheckBox cbxOfficerKilled = new CheckBox();
		cbxOfficerKilled.setText("Officer Was Killed");
		vbxOfficerFull.getChildren().add(cbxOfficerKilled);

		CheckBox cbxOfficerOnDuty = new CheckBox();
		cbxOfficerOnDuty.setText("Officer Was On-Duty");
		vbxOfficerFull.getChildren().add(cbxOfficerOnDuty);
		
		CheckBox cbxOfficerUniformed = new CheckBox();
		cbxOfficerUniformed.setText("Officer was Uniformed");
		vbxOfficerFull.getChildren().add(cbxOfficerUniformed);
		
		CheckBox cbxOfficerTreatment = new CheckBox();
		cbxOfficerTreatment.setText("Officer Recieved Medical Treatment");
		vbxOfficerFull.getChildren().add(cbxOfficerTreatment);
		
		Label lblOfficerInjuriesDesc = new Label();
		lblOfficerInjuriesDesc.setText("Describe Injuries to Officer");
		vbxOfficerFull.getChildren().add(lblOfficerInjuriesDesc);
		TextArea txaOfficerInjuriesDesc = new TextArea();
		txaOfficerInjuriesDesc.setPrefWidth(200);
		txaOfficerInjuriesDesc.setPrefHeight(150);
		vbxOfficerFull.getChildren().add(txaOfficerInjuriesDesc);
		
		Separator sprOfficerIncident = new Separator();
		sprOfficerIncident.setOrientation(Orientation.VERTICAL);
		hbxReport.getChildren().add(sprOfficerIncident);
		
		VBox vbxIncidentFull = new VBox();
		vbxIncidentFull.setAlignment(Pos.TOP_CENTER);
		hbxReport.getChildren().add(vbxIncidentFull);
		
		Text txtIncidentInfo = new Text();
		txtIncidentInfo.setStrokeWidth(0.0);
		txtIncidentInfo.setStrokeType(StrokeType.OUTSIDE);
		txtIncidentInfo.setTextAlignment(TextAlignment.CENTER);
		txtIncidentInfo.setText("Incident Information");
		vbxIncidentFull.getChildren().add(txtIncidentInfo);
		
		GridPane grdIncidentInfo = new GridPane();
		vbxIncidentFull.getChildren().add(grdIncidentInfo);
		
		Label lblIncidentDate = new Label();
		lblIncidentDate.setText("Incident Date");
		grdIncidentInfo.add(lblIncidentDate, 0, 0);
		DatePicker dtpIncidentDate = new DatePicker();
		grdIncidentInfo.add(dtpIncidentDate, 1, 0);
		
		Label lblIncidentLocation = new Label();
		lblIncidentLocation.setText("Incident Location");
		grdIncidentInfo.add(lblIncidentLocation, 0, 1);
		TextField txfIncidentLocation = new TextField();
		grdIncidentInfo.add(txfIncidentLocation, 1, 1);
		
		Label lblIncidentType = new Label();
		lblIncidentType.setText("Incident Type");
		grdIncidentInfo.add(lblIncidentType, 0, 2);
		VBox vbxIncidentType = new VBox();
		grdIncidentInfo.add(vbxIncidentType, 1, 2);
		
		final ToggleGroup type = new ToggleGroup();
		
		RadioButton rdbInProgress = new RadioButton();
		rdbInProgress.setToggleGroup(type);
		rdbInProgress.setText("Crime In Progress");
		vbxIncidentType.getChildren().add(rdbInProgress);
		
		RadioButton rdbDomestic = new RadioButton();
		rdbDomestic.setToggleGroup(type);
		rdbDomestic.setText("Domestic");
		vbxIncidentType.getChildren().add(rdbDomestic);
		
		RadioButton rdbSuspicious = new RadioButton();
		rdbSuspicious.setToggleGroup(type);
		rdbSuspicious.setText("Suspicious Person");
		vbxIncidentType.getChildren().add(rdbSuspicious);
		
		RadioButton rdbTrafficStop = new RadioButton();
		rdbTrafficStop.setToggleGroup(type);
		rdbTrafficStop.setText("Traffic Stop");
		vbxIncidentType.getChildren().add(rdbTrafficStop);
		
		RadioButton rdbArrest = new RadioButton();
		rdbArrest.setToggleGroup(type);
		rdbArrest.setText("Arrest");
		vbxIncidentType.getChildren().add(rdbArrest);
		
		RadioButton rdbOtherType = new RadioButton();
		rdbOtherType.setToggleGroup(type);
		rdbOtherType.setText("Other");
		vbxIncidentType.getChildren().add(rdbOtherType);
		
		TextField txfOtherDesc = new TextField();
		vbxIncidentType.getChildren().add(txfOtherDesc);
		
		Separator sprIncidentSubject = new Separator();
		vbxIncidentFull.getChildren().add(sprIncidentSubject);
		
		Text txtSubjects = new Text();
		txtSubjects.setStrokeWidth(0.0);
		txtSubjects.setStrokeType(StrokeType.OUTSIDE);
		txtSubjects.setTextAlignment(TextAlignment.CENTER);
		txtSubjects.setText("Subjects");
		vbxIncidentFull.getChildren().add(txtSubjects);
		
		HBox hbxSubjectInteract = new HBox();
		hbxSubjectInteract.setAlignment(Pos.CENTER);
		vbxIncidentFull.getChildren().add(hbxSubjectInteract);
		
		Button btnCreateSubject = new Button();
		btnCreateSubject.setText("Create Subject");
		hbxSubjectInteract.getChildren().add(btnCreateSubject);
		
		Button btnDeleteSubject = new Button();
		btnDeleteSubject.setText("Delete Subject");
		hbxSubjectInteract.getChildren().add(btnDeleteSubject);
		
		Button btnEditSubject = new Button();
		btnEditSubject.setText("Edit Subject");
		hbxSubjectInteract.getChildren().add(btnEditSubject);
		
		Label lblSubjectChoice = new Label();
		lblSubjectChoice.setContentDisplay(ContentDisplay.CENTER);
		lblSubjectChoice.setText("Choose A Subject");
		lblSubjectChoice.setAlignment(Pos.CENTER);
		vbxIncidentFull.getChildren().add(lblSubjectChoice);
		
		ComboBox<String> cbxSubjectChoice = new ComboBox<String>();
		cbxSubjectChoice.getItems().add("Subject 1");
		vbxIncidentFull.getChildren().add(cbxSubjectChoice);
		
		Separator sprSubjectSubmit = new Separator();
		vbxIncidentFull.getChildren().add(sprSubjectSubmit);
		
		StackPane spnSubmit = new StackPane();
		vbxIncidentFull.getChildren().add(spnSubmit);
		
		Button btnSubmit = new Button();
		btnSubmit.setText("Finish and Submit");
		spaSubmit.getChildren().add(btnSubmit);

		form.show();
	}
	
}