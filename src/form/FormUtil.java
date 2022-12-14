package form;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;

public class FormUtil {
	
	public static Date addMinorTimeFromText(Date date, String hour, String minute, String second, String amPM) {
		try {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		if(amPM.equals("AM")) {
			calDate.add(Calendar.HOUR_OF_DAY,textToInteger(hour));
		}
		else {
			calDate.add(Calendar.HOUR_OF_DAY,textToInteger(hour) + 12);
		}
		calDate.add(Calendar.MINUTE,textToInteger(minute));
		calDate.add(Calendar.SECOND,textToInteger(second));
		return calDate.getTime();
		}
		catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static Date datePickerToDate(DatePicker dtp) {
		try {
    		return Date.from(dtp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	}
    	catch(Exception e){
    		dtp.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    		throw new IllegalArgumentException();
    	}
	}
	
	public static int textToInteger(String input) {
		try {
    		return Integer.parseInt(cleanInput(input));
    	}
    	catch(Exception e){
    		throw new IllegalArgumentException();
    	}
	}
	
	public static String cleanInput(String input) {
		if(input == null) {
			input = "";
		}
		input = input.trim();
		input = input.replaceAll("[^ a-zA-Z0-9]", "");
		return input;
	}
	
	public static void removeOrAddFromCheckBox(CheckBox cbx, ArrayList<String> data) {
		try {
			if(cbx.isSelected() && !data.contains(cbx.getText())) {
				data.add(cbx.getText());
			}
			else if(!cbx.isSelected() && data.contains(cbx.getText())) {
				data.remove(cbx.getText());
			}
		}
		catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public static <T> int getCurrentIndex(ComboBox<T> cob) {
		int index = 0;
		for (int i = 0; i < cob.getItems().size(); i++) {
			if (cob.getSelectionModel().isSelected(i)) {
				index = i;
			}
		}
		return index;
	}

	public static <T> int getCurrentIndex(ChoiceBox<T> cbx) {
		int index = 0;
		for (int i = 0; i < cbx.getItems().size(); i++) {
			if (cbx.getSelectionModel().isSelected(i)) {
				index = i;
			}
		}
		return index;
	}
	
	public static void toggleTextInputWithLabelEvent(CheckBox toggle, TextInputControl text, Node label) {
		toggle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(toggle.isSelected()) {
					label.setVisible(true);
					text.setVisible(true);
				}
				else {
					label.setVisible(false);
					text.setVisible(false);
				}
			}
		});
	}
	
	public static void toggleTextInputEvent(CheckBox toggle, TextInputControl text) {
		toggle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(toggle.isSelected()) {
					text.setVisible(true);
				}
				else {
					text.setVisible(false);
				}
			}
		});
	}
	
	public static void toggleTextFieldFromRadioButtonEvent(ToggleGroup toggles, int correctIndex, TextInputControl text) {
		
		for(int i=0; i<toggles.getToggles().size(); i++) {
			((ButtonBase) toggles.getToggles().get(i)).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(toggles.getToggles().get(correctIndex).isSelected()) {
						text.setVisible(true);
					}
					else {
						text.setVisible(false);
					}
				}
			});
		}
	}
	
	public static LocalDate dateToLocalDate(Date input) {
		LocalDate output;
		output = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return output;
	}
	
	public static void makeTextFieldNumeric(TextField txf) {
		txf.textProperty().addListener((InvalidationListener) observable -> {
			if(!txf.getText().replaceAll("[^0-9]", "").equals(txf.getText())) {
				txf.setText(txf.getText().replaceAll("[^0-9]", ""));
			}
		});
	}
	
	public static Label makePaddedLabel(String text) {
		Label label = new Label(text);
		label.setPadding(new Insets(3));
		return label;
	}
	
	public static String booleanToYesNo(boolean isTrue) {
		if(isTrue) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
	
	//1st 2nd 3rd 4th ... 10th 11th 12th 13th ... 20th 21st 22nd 23rd 24th ... 30th 31st 32nd 33rd 34th ...
	public static String numberNotation(int i) {
		if((i-1)%10==0 && (i>11||i<19)) {
			return i + "st";
		}
		else if(i%2==0 && (i>11||i<19)) {
			return i + "nd";
		}
		else if(i%3==0 && (i>11||i<19)) {
			return i + "rd";
		}
		else {
			return i + "th";
		}
	}
}