package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class UOFSubjectForm extends Application {

	private Subject subject;
	
	public Subject run() {
        this.start(new Stage());
        return subject;
    }
	
	@Override
	public void start(Stage form) {
		
		form.setTitle("FUPO UOF Report - Subject");
		form.setResizable(false);
		
		Pane pneSubject = new Pane();
		form.setScene(new Scene(pneSubject, 755, 635));
		
		HBox hbxSubject = new HBox();
		pneSubject.getChildren().add(hbxSubject);
		
		VBox vbxSubjectInfo = new VBox();
		hbxSubject.getChildren().add(vbxSubjectInfo);
		
		Text txtSubjectInfo = new Text();
		txtSubjectInfo.setStrokeWidth(0.0);
		txtSubjectInfo.setStrokeType(StrokeType.OUTSIDE);
		txtSubjectInfo.setText("Subject Information");
		vbxSubjectInfo.getChildren().add(txtSubjectInfo);
		
		GridPane grdSubjectInfo = new GridPane();
		vbxSubjectInfo.getChildren().add(grdSubjectInfo);
		
		Label lblFirstName = new Label();
		lblFirstName.setText("First Name");
		grdSubjectInfo.add(lblFirstName, 0, 0);
		TextField txfFirstName = new TextField();
		grdSubjectInfo.add(txfFirstName, 1, 0);
		
		Label lblMiddleName = new Label();
		lblMiddleName.setText("Middle Name");
		grdSubjectInfo.add(lblMiddleName, 0, 1);
		TextField txfMiddleName = new TextField();
		grdSubjectInfo.add(txfMiddleName, 1, 1);

		Label lblLastName = new Label();
		lblLastName.setText("Last Name");
		grdSubjectInfo.add(lblLastName, 0, 2);
		TextField txfLastName = new TextField();
		grdSubjectInfo.add(txfLastName, 1, 2);

		Label lblSex = new Label();
		lblSex.setText("Sex");
		grdSubjectInfo.add(lblSex, 0, 3);
		TextField txfSex = new TextField();
		grdSubjectInfo.add(txfSex, 1, 3);

		Label lblRace = new Label();
		lblRace.setText("Race");
		grdSubjectInfo.add(lblRace, 0, 4);
		TextField txfRace = new TextField();
		grdSubjectInfo.add(txfRace, 1, 4);
		
		Label lblDOB = new Label();
		lblDOB.setText("Date of Birth");
		grdSubjectInfo.add(lblDOB, 0, 5);
		DatePicker dtpDOB = new DatePicker();
		grdSubjectInfo.add(dtpDOB, 1, 5);
		
		CheckBox cbxSubjectInjured = new CheckBox();
		cbxSubjectInjured.setText("Subject was Injured");
		vbxSubjectInfo.getChildren().add(cbxSubjectInjured);
		
		CheckBox cbxSubjectKilled = new CheckBox();
		cbxSubjectKilled.setText("Subject was Killed");
		vbxSubjectInfo.getChildren().add(cbxSubjectKilled);
		
		CheckBox cbxSubjetWeaponed = new CheckBox();
		cbxSubjetWeaponed.setText("Subject had a Weapon");
		vbxSubjectInfo.getChildren().add(cbxSubjetWeaponed);
		
		CheckBox cbxSubjectArrested = new CheckBox();
		cbxSubjectArrested.setText("Subject Was Arrested");
		vbxSubjectInfo.getChildren().add(cbxSubjectArrested);
		
		Label lblInjuries = new Label();
		lblInjuries.setText("Describe Injuries to Subject");
		vbxSubjectInfo.getChildren().add(lblInjuries);
		TextArea txaInjuries = new TextArea();
		txaInjuries.setPrefHeight(80.0);
		txaInjuries.setPrefWidth(100.0);
		vbxSubjectInfo.getChildren().add(txaInjuries);
		
		Label lblInfluence = new Label();
		lblInfluence.setText("Under the Influence of:");
		vbxSubjectInfo.getChildren().add(lblInfluence);
		
		CheckBox cbxDrugs = new CheckBox();
		cbxDrugs.setText("Drugs");
		vbxSubjectInfo.getChildren().add(cbxDrugs);
		
		CheckBox cbxAlcohol = new CheckBox();
		cbxAlcohol.setText("Alcohol");
		vbxSubjectInfo.getChildren().add(cbxAlcohol);
		
		CheckBox cbxMentalIllness = new CheckBox();
		cbxMentalIllness.setText("Mental Illness");
		vbxSubjectInfo.getChildren().add(cbxMentalIllness);
		
		CheckBox cbxOtherCondition = new CheckBox();
		cbxOtherCondition.setText("Other Condition");
		vbxSubjectInfo.getChildren().add(cbxOtherCondition);
		
		TextField txfOtherCondition = new TextField();
		vbxSubjectInfo.getChildren().add(txfOtherCondition);
		
		Label lblCharges = new Label();
		lblCharges.setText("Charges");
		vbxSubjectInfo.getChildren().add(lblCharges);
		TextArea txaCharges = new TextArea();
		txaCharges.setPrefHeight(80.0);
		txaCharges.setPrefWidth(100.0);
		vbxSubjectInfo.getChildren().add(txaCharges);
		
		Separator sprInformationActions = new Separator();
		sprInformationActions.setOrientation(Orientation.VERTICAL);
		hbxSubject.getChildren().add(sprInformationActions);
		
		VBox vbxActions = new VBox();
		hbxSubject.getChildren().add(vbxActions);
		
		Text txtSubjectActions = new Text();
		txtSubjectActions.setStrokeWidth(0.0);
		txtSubjectActions.setStrokeType(StrokeType.OUTSIDE);
		txtSubjectActions.setText("Subject's Actions");
		vbxActions.getChildren().add(txtSubjectActions);
		
		CheckBox cbxResisted = new CheckBox();
		cbxResisted.setText("Resisted Police Officer Control/Arrest");
		vbxActions.getChildren().add(cbxResisted);
		
		CheckBox cbxThreatOrAttack = new CheckBox();
		cbxThreatOrAttack.setText("Physical Treat/Attack on Officer or Another");
		vbxActions.getChildren().add(cbxThreatOrAttack);
		
		CheckBox cbxKnife = new CheckBox();
		cbxKnife.setText("Threatened/Attacked Officer or Another With a Knife/Weapon");
		vbxActions.getChildren().add(cbxKnife);
		
		CheckBox cbxMotorVehicle = new CheckBox();
		cbxMotorVehicle.setText("Threatened/Attacked Officer or Another with Motor Vehicle");
		vbxActions.getChildren().add(cbxMotorVehicle);
		
		CheckBox cbxFirearm = new CheckBox();
		cbxFirearm.setText("Threatened Officer or Another With a Firearm");
		vbxActions.getChildren().add(cbxFirearm);
		
		CheckBox cbxShotFirearm = new CheckBox();
		cbxShotFirearm.setText("Fired at Officer or Another");
		vbxActions.getChildren().add(cbxShotFirearm);
		
		CheckBox cbxOtherAction = new CheckBox();
		cbxOtherAction.setText("Other (Specify)");
		vbxActions.getChildren().add(cbxOtherAction);
		
		TextField txfOtherAction = new TextField();
		vbxActions.getChildren().add(txfOtherAction);
		
		Separator sprActionsUOF = new Separator();
		sprActionsUOF.setPrefWidth(200.0);
		vbxActions.getChildren().add(sprActionsUOF);
		
		Text txtUOF = new Text();
		txtUOF.setStrokeWidth(0.0);
		txtUOF.setStrokeType(StrokeType.OUTSIDE);
		txtUOF.setText("Officer's Use of Force Toward Subject");
		vbxActions.getChildren().add(txtUOF);
		
		CheckBox cbxComplianceHold = new CheckBox();
		cbxComplianceHold.setText("Compliance Hold");
		vbxActions.getChildren().add(cbxComplianceHold);
		
		CheckBox cbxHandsFistFeet = new CheckBox();
		cbxHandsFistFeet.setText("Hands/Fist/Feet");
		vbxActions.getChildren().add(cbxHandsFistFeet);
		
		CheckBox cbxElectric = new CheckBox();
		cbxElectric.setText("Electronic Control Device");
		vbxActions.getChildren().add(cbxElectric);
		
		CheckBox cbxChemical = new CheckBox();
		cbxChemical.setText("Chemical Agent");
		vbxActions.getChildren().add(cbxChemical);
		
		CheckBox cbxBaton = new CheckBox();
		cbxBaton.setText("Strike/Use Baton or Other Object");
		vbxActions.getChildren().add(cbxBaton);
		
		CheckBox cbxOtherUOF = new CheckBox();
		cbxOtherUOF.setText("Other (Specify)");
		vbxActions.getChildren().add(cbxOtherUOF);
		
		TextField txfOtherUOF = new TextField();
		vbxActions.getChildren().add(txfOtherUOF);
		
		Label lblFirearm = new Label();
		lblFirearm.setText("Firearm:");
		vbxActions.getChildren().add(lblFirearm);
		
		CheckBox cbxFirearmAimed = new CheckBox();
		cbxFirearmAimed.setText("Aimed");
		vbxActions.getChildren().add(cbxFirearmAimed);
		
		CheckBox cbxFirearmDischarged = new CheckBox();
		cbxFirearmDischarged.setText("Discharged");
		vbxActions.getChildren().add(cbxFirearmDischarged);
		
		HBox hbxNumberOfShots = new HBox();
		hbxNumberOfShots.setAlignment(Pos.CENTER_LEFT);
		vbxActions.getChildren().add(hbxNumberOfShots);
		
		Label lblNumberOfShots = new Label();
		lblNumberOfShots.setText("Number of Shots Fired");
		hbxNumberOfShots.getChildren().add(lblNumberOfShots);
		
		TextField txfNumberOfShots = new TextField();
		hbxNumberOfShots.getChildren().add(txfNumberOfShots);
		
		Separator sprUOFSubmit = new Separator();
		vbxActions.getChildren().add(sprUOFSubmit);
		
		StackPane spnSubmit = new StackPane();
		vbxActions.getChildren().add(spnSubmit);
		
		Button btnSubmit = new Button();
		btnSubmit.setText("Save and Submit");
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	form.close();
            }
        });
		spnSubmit.getChildren().add(btnSubmit);
		
		form.show();
	}
	
}