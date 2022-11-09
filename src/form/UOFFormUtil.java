package form;

import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UOFFormUtil {
	
	public static Date datePickerToDate(DatePicker dtp) {
		try {
    		return Date.from(dtp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	}
    	catch(Exception e){
    		dtp.setValue(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    		throw new IllegalArgumentException();
    	}
	}
	
	public static int textFieldToInteger(TextField txf) {
		try {
    		return Integer.parseInt(cleanInput(txf.getText()));
    	}
    	catch(Exception e){
    		txf.setText("0");
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
		if(cbx.isSelected() && !data.contains(cbx.getText())) {
    		data.add(cbx.getText());
    	}
    	else if(!cbx.isSelected() && data.contains(cbx.getText())) {
    		data.remove(cbx.getText());
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

}