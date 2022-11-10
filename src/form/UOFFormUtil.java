package form;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class UOFFormUtil {
	
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
		return input;
	}
	
	public static void removeOrAddFromCheckBox(CheckBox cbx, Vector<String> data) {
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

	public static void toggleTextAreaWithLabelEvent(CheckBox toggle, TextArea text, Node label) {
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
	
	public static void toggleTextFieldFromRadioButtonEvent(Toggle toggle, int correctIndex, TextInputControl text) {
		((ButtonBase) toggle).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToggleGroup group = toggle.getToggleGroup();
				System.out.println(group.getToggles().get(correctIndex).isSelected());
				if(group.getToggles().get(correctIndex).isSelected()) {
					text.setVisible(true);
				}
				else {
					text.setVisible(false);
				}
			}
		});
	}
	
}