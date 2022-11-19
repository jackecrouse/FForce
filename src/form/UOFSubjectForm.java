package form;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UOFSubjectForm extends Application {

	private Subject subject;
	private Pane pneSubject;
	private HBox hbxSubject;
	private VBox vbxSubjectInfo;
	private Text txtSubjectInfo;
	private GridPane grdSubjectInfo;
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
	private Label lblAge;
	private TextField txfDOB;
	private CheckBox cbxSubjectInjured;
	private CheckBox cbxSubjectKilled;
	private CheckBox cbxSubjectWeaponed;
	private CheckBox cbxSubjectArrested;
	private Label lblInjuries;
	private TextArea txaInjuries;
	private Label lblInfluence;
	private CheckBox cbxDrugs;
	private CheckBox cbxAlcohol;
	private CheckBox cbxMentalIllness;
	private CheckBox cbxOtherCondition;
	private TextField txfOtherCondition;
	private Label lblCharges;
	private TextArea txaCharges;
	private Separator sprInformationActions;
	private VBox vbxActions;
	private Text txtSubjectActions;
	private CheckBox cbxResisted;
	private CheckBox cbxThreatOrAttack;
	private CheckBox cbxKnife;
	private CheckBox cbxMotorVehicle;
	private CheckBox cbxFirearm;
	private CheckBox cbxShotFirearm;
	private CheckBox cbxOtherAction;
	private TextField txfOtherAction;
	private Separator sprActionsUOF;
	private Text txtUOF;
	private CheckBox cbxComplianceHold;
	private CheckBox cbxHandsFistFeet;
	private CheckBox cbxElectric;
	private CheckBox cbxChemical;
	private CheckBox cbxBaton;
	private CheckBox cbxOtherUOF;
	private TextField txfOtherUOF;
	private Label lblFirearm;
	private CheckBox cbxFirearmAimed;
	private CheckBox cbxFirearmDischarged;
	private HBox hbxNumberOfShots;
	private Label lblNumberOfShots;
	private TextField txfNumberOfShots;
	private Separator sprUOFSubmit;
	private StackPane spnSubmit;
	private Button btnSubmit;
	private CheckBox cbxSubjectHadTreatment;
	
	public void run(Subject newSubject) {
        subject = newSubject;
		start(new Stage());
    }
	
	@Override
	public void start(Stage form) {
		
		form.setTitle("FUPO UOF Report - Subject");
		form.setResizable(false);
		
		pneSubject = new Pane();
		form.setScene(new Scene(pneSubject, 755, 635));
		
		hbxSubject = new HBox();
		pneSubject.getChildren().add(hbxSubject);
		
		// everything on the left half of the screen start
		vbxSubjectInfo = new VBox();
		hbxSubject.getChildren().add(vbxSubjectInfo);
		
		txtSubjectInfo = new Text("Subject Information");
		vbxSubjectInfo.getChildren().add(txtSubjectInfo);
		
		// Subject's personal information start
		grdSubjectInfo = new GridPane();
		vbxSubjectInfo.getChildren().add(grdSubjectInfo);
		
		lblFirstName = new Label("First Name");
		grdSubjectInfo.add(lblFirstName, 0, 0);
		txfFirstName = new TextField(subject.firstName);
		grdSubjectInfo.add(txfFirstName, 1, 0);
		
		lblMiddleName = new Label("Middle Name");
		grdSubjectInfo.add(lblMiddleName, 0, 1);
		txfMiddleName = new TextField(subject.middleName);
		grdSubjectInfo.add(txfMiddleName, 1, 1);

		lblLastName = new Label("Last Name");
		grdSubjectInfo.add(lblLastName, 0, 2);
		txfLastName = new TextField(subject.lastName);
		grdSubjectInfo.add(txfLastName, 1, 2);

		lblSex = new Label("Sex");
		grdSubjectInfo.add(lblSex, 0, 3);
		txfSex = new TextField(subject.sex);
		grdSubjectInfo.add(txfSex, 1, 3);

		lblRace = new Label("Race");
		grdSubjectInfo.add(lblRace, 0, 4);
		txfRace = new TextField(subject.race);
		grdSubjectInfo.add(txfRace, 1, 4);
		
		lblAge = new Label("Age");
		grdSubjectInfo.add(lblAge, 0, 5);
		txfDOB = new TextField(Integer.toString(subject.age));
		grdSubjectInfo.add(txfDOB, 1, 5);
		// Subject's personal information end
		
		// Subject's incident information start
		cbxSubjectInjured = new CheckBox("Subject was Injured");
		cbxSubjectInjured.setSelected(subject.wasInjured);
		vbxSubjectInfo.getChildren().add(cbxSubjectInjured);
		
		cbxSubjectKilled = new CheckBox("Subject was Killed");
		cbxSubjectKilled.setSelected(subject.wasKilled);
		vbxSubjectInfo.getChildren().add(cbxSubjectKilled);
		
		cbxSubjectWeaponed = new CheckBox("Subject had a Weapon");
		cbxSubjectWeaponed.setSelected(subject.wasWeaponed);
		vbxSubjectInfo.getChildren().add(cbxSubjectWeaponed);
		
		cbxSubjectArrested = new CheckBox("Subject Was Arrested");
		cbxSubjectArrested.setSelected(subject.wasArrested);
		vbxSubjectInfo.getChildren().add(cbxSubjectArrested);
		
		cbxSubjectHadTreatment = new CheckBox("Subject Recieved Medical Treatment");
		cbxSubjectHadTreatment.setSelected(subject.hadMedicalTreatment);
		vbxSubjectInfo.getChildren().add(cbxSubjectHadTreatment);
		
		int injuriesLabelIndex = 7;
		lblInjuries = new Label("Describe Injuries to Subject");
		vbxSubjectInfo.getChildren().add(lblInjuries);
		txaInjuries = new TextArea(subject.injuries);
		txaInjuries.setPrefSize(100.0, 80.0);
		UOFFormUtil.toggleTextInputWithLabelEvent(cbxSubjectInjured, txaInjuries, vbxSubjectInfo.getChildren().get(injuriesLabelIndex));
		if(!cbxSubjectInjured.isSelected()) {
			vbxSubjectInfo.getChildren().get(injuriesLabelIndex).setVisible(false);
			txaInjuries.setVisible(false);
		}
		vbxSubjectInfo.getChildren().add(txaInjuries);
		
		// Subject's conditions start
		lblInfluence = new Label("Under the Influence of:");
		vbxSubjectInfo.getChildren().add(lblInfluence);
		
		cbxDrugs = new CheckBox("Drugs");
		cbxDrugs.setSelected(subject.influence.contains(cbxDrugs.getText()));
		vbxSubjectInfo.getChildren().add(cbxDrugs);
		
		cbxAlcohol = new CheckBox("Alcohol");
		cbxAlcohol.setSelected(subject.influence.contains(cbxAlcohol.getText()));
		vbxSubjectInfo.getChildren().add(cbxAlcohol);
		
		cbxMentalIllness = new CheckBox("Mental Illness");
		cbxMentalIllness.setSelected(subject.influence.contains(cbxMentalIllness.getText()));
		vbxSubjectInfo.getChildren().add(cbxMentalIllness);
		
		cbxOtherCondition = new CheckBox("Other Condition");
		cbxOtherCondition.setSelected(subject.influence.contains(cbxOtherCondition.getText()));
		vbxSubjectInfo.getChildren().add(cbxOtherCondition);
		
		txfOtherCondition = new TextField(subject.otherInfluence);
		UOFFormUtil.toggleTextInputEvent(cbxOtherCondition, txfOtherCondition);
		if(!cbxOtherCondition.isSelected()) {
			txfOtherCondition.setVisible(false);
		}
		vbxSubjectInfo.getChildren().add(txfOtherCondition);
		// Subject's conditions start
		
		int chargesLabelIndex = 15;
		lblCharges = new Label("Charges");
		vbxSubjectInfo.getChildren().add(lblCharges);
		txaCharges = new TextArea(subject.charges);
		txaCharges.setPrefSize(100.0,80.0);
		UOFFormUtil.toggleTextInputWithLabelEvent(cbxSubjectArrested, txaCharges, vbxSubjectInfo.getChildren().get(chargesLabelIndex));
		if(!cbxSubjectArrested.isSelected()) {
			vbxSubjectInfo.getChildren().get(chargesLabelIndex).setVisible(false);
			txaCharges.setVisible(false);
		}
		vbxSubjectInfo.getChildren().add(txaCharges);
		// Subject's incident information start
		// everything on the left half of the screen end
		
		sprInformationActions = new Separator(Orientation.VERTICAL);
		hbxSubject.getChildren().add(sprInformationActions);
		
		// everything on the right half of the screen start
		vbxActions = new VBox();
		hbxSubject.getChildren().add(vbxActions); 
		
		// Subject's action start
		txtSubjectActions = new Text("Subject's Actions");
		vbxActions.getChildren().add(txtSubjectActions);
		
		cbxResisted = new CheckBox("Resisted Police Officer Control/Arrest");
		cbxResisted.setSelected(subject.actions.contains(cbxResisted.getText()));
		vbxActions.getChildren().add(cbxResisted);
		
		cbxThreatOrAttack = new CheckBox("Physical Treat/Attack on Officer or Another");
		cbxThreatOrAttack.setSelected(subject.actions.contains(cbxThreatOrAttack.getText()));
		vbxActions.getChildren().add(cbxThreatOrAttack);
		
		cbxKnife = new CheckBox("Threatened/Attacked Officer or Another With a Knife/Weapon");
		cbxKnife.setSelected(subject.actions.contains(cbxKnife.getText()));
		vbxActions.getChildren().add(cbxKnife);
		
		cbxMotorVehicle = new CheckBox("Threatened/Attacked Officer or Another with Motor Vehicle");
		cbxMotorVehicle.setSelected(subject.actions.contains(cbxMotorVehicle.getText()));
		vbxActions.getChildren().add(cbxMotorVehicle);
		
		cbxFirearm = new CheckBox("Threatened Officer or Another With a Firearm");
		cbxFirearm.setSelected(subject.actions.contains(cbxFirearm.getText()));
		vbxActions.getChildren().add(cbxFirearm);
		
		cbxShotFirearm = new CheckBox("Fired at Officer or Another");
		cbxShotFirearm.setSelected(subject.actions.contains(cbxShotFirearm.getText()));
		vbxActions.getChildren().add(cbxShotFirearm);
		
		cbxOtherAction = new CheckBox("Other (Specify)");
		cbxOtherAction.setSelected(subject.actions.contains(cbxOtherAction.getText()));
		vbxActions.getChildren().add(cbxOtherAction);
		
		txfOtherAction = new TextField(subject.otherActions);
		UOFFormUtil.toggleTextInputEvent(cbxOtherAction, txfOtherAction);
		if(!cbxOtherAction.isSelected()) {
			txfOtherAction.setVisible(false);
		}
		vbxActions.getChildren().add(txfOtherAction);
		// Subject's actions end
		
		sprActionsUOF = new Separator();
		vbxActions.getChildren().add(sprActionsUOF);
		
		// Officer's use of force start
		txtUOF = new Text("Officer's Use of Force Toward Subject");
		vbxActions.getChildren().add(txtUOF);
		
		cbxComplianceHold = new CheckBox("Compliance Hold");
		cbxComplianceHold.setSelected(subject.uofAgainst.contains(cbxComplianceHold.getText()));
		vbxActions.getChildren().add(cbxComplianceHold);
		
		cbxHandsFistFeet = new CheckBox("Hands/Fist/Feet");
		cbxHandsFistFeet.setSelected(subject.uofAgainst.contains(cbxHandsFistFeet.getText()));
		vbxActions.getChildren().add(cbxHandsFistFeet);
		
		cbxElectric = new CheckBox("Electronic Control Device");
		cbxElectric.setSelected(subject.uofAgainst.contains(cbxElectric.getText()));
		vbxActions.getChildren().add(cbxElectric);
		
		cbxChemical = new CheckBox("Chemical Agent");
		cbxChemical.setSelected(subject.uofAgainst.contains(cbxChemical.getText()));
		vbxActions.getChildren().add(cbxChemical);
		
		cbxBaton = new CheckBox("Strike/Use Baton or Other Object");
		cbxBaton.setSelected(subject.uofAgainst.contains(cbxBaton.getText()));
		vbxActions.getChildren().add(cbxBaton);
		
		cbxOtherUOF = new CheckBox("Other (Specify)");
		cbxOtherUOF.setSelected(subject.uofAgainst.contains(cbxOtherUOF.getText()));
		vbxActions.getChildren().add(cbxOtherUOF);
		
		txfOtherUOF = new TextField(subject.otherUOF);
		UOFFormUtil.toggleTextInputEvent(cbxOtherUOF, txfOtherUOF);
		if(!cbxOtherUOF.isSelected()) {
			txfOtherUOF.setVisible(false);
		}
		vbxActions.getChildren().add(txfOtherUOF);
		
		lblFirearm = new Label("Firearm:");
		vbxActions.getChildren().add(lblFirearm);
		
		cbxFirearmAimed = new CheckBox("Aimed");
		cbxFirearmAimed.setSelected(subject.uofAgainst.contains(cbxFirearmAimed.getText()));
		vbxActions.getChildren().add(cbxFirearmAimed);
		
		cbxFirearmDischarged = new CheckBox("Discharged");
		cbxFirearmDischarged.setSelected(subject.uofAgainst.contains(cbxFirearmDischarged.getText()));
		vbxActions.getChildren().add(cbxFirearmDischarged);
		
		hbxNumberOfShots = new HBox();
		hbxNumberOfShots.setAlignment(Pos.CENTER_LEFT);
		vbxActions.getChildren().add(hbxNumberOfShots);
		
		lblNumberOfShots = new Label("Number of Shots Fired");
		hbxNumberOfShots.getChildren().add(lblNumberOfShots);
		
		txfNumberOfShots = new TextField("" + subject.numberOfShots);
		hbxNumberOfShots.getChildren().add(txfNumberOfShots);
		// Officer's use of force end
		
		sprUOFSubmit = new Separator();
		vbxActions.getChildren().add(sprUOFSubmit);
		
		spnSubmit = new StackPane();
		vbxActions.getChildren().add(spnSubmit);
		
		// Submit start
		btnSubmit = new Button("Save and Submit");
		btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	subject.firstName = UOFFormUtil.cleanInput(txfFirstName.getText());
            	subject.middleName = UOFFormUtil.cleanInput(txfMiddleName.getText());
            	subject.lastName = UOFFormUtil.cleanInput(txfLastName.getText());
            	subject.sex = UOFFormUtil.cleanInput(txfSex.getText());
            	subject.race = UOFFormUtil.cleanInput(txfRace.getText());
            	subject.age = UOFFormUtil.textToInteger(txfDOB.getText());
            	subject.wasInjured = cbxSubjectInjured.isSelected();
            	subject.wasKilled = cbxSubjectKilled.isSelected();
            	subject.wasWeaponed = cbxSubjectWeaponed.isSelected();
            	subject.wasArrested = cbxSubjectArrested.isSelected();
            	subject.hadMedicalTreatment = cbxSubjectHadTreatment.isSelected();
            	subject.injuries = UOFFormUtil.cleanInput(txaInjuries.getText());
            	UOFFormUtil.removeOrAddFromCheckBox(cbxDrugs, subject.influence);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxAlcohol, subject.influence);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxMentalIllness, subject.influence);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxOtherCondition, subject.influence);
            	subject.otherInfluence = UOFFormUtil.cleanInput(txfOtherCondition.getText());
            	subject.charges = UOFFormUtil.cleanInput(txaCharges.getText());
            	UOFFormUtil.removeOrAddFromCheckBox(cbxResisted, subject.actions);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxThreatOrAttack, subject.actions);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxKnife, subject.actions);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxMotorVehicle, subject.actions);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxFirearm, subject.actions);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxShotFirearm, subject.actions);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxOtherAction, subject.actions);
            	subject.otherActions = UOFFormUtil.cleanInput(txfOtherAction.getText());
            	UOFFormUtil.removeOrAddFromCheckBox(cbxComplianceHold, subject.uofAgainst);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxHandsFistFeet, subject.uofAgainst);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxElectric, subject.uofAgainst);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxChemical, subject.uofAgainst);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxBaton, subject.uofAgainst);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxOtherUOF, subject.uofAgainst);
            	subject.otherUOF = UOFFormUtil.cleanInput(txfOtherUOF.getText());
            	UOFFormUtil.removeOrAddFromCheckBox(cbxFirearmAimed, subject.uofAgainst);
            	UOFFormUtil.removeOrAddFromCheckBox(cbxFirearmDischarged, subject.uofAgainst);
            	subject.numberOfShots = UOFFormUtil.textToInteger(txfNumberOfShots.getText());
            	form.close();
            }

			
        });
		spnSubmit.getChildren().add(btnSubmit);
		// Submit end
		
		form.show();
	}
	
}