package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ReportForm extends Application {

	public static void main(String[] args) {
        launch(args);
    }
	
	 @Override
	    public void start(Stage form) {
	        form.setTitle("Furman University Police Use of Force Report");
	        Text btn = new Text();
	        btn.setText("Say 'Hello World'");
	        //btn.setOnAction(new EventHandler<ActionEvent>() {
	        //    	@Override
	        //    	public void handle(ActionEvent event) {
	        //        	System.out.println("Hello World!");
	        //    	}
	        //	});
	        
	        StackPane root = new StackPane();
	        root.getChildren().add(btn);
	        form.setScene(new Scene(root, 300, 250));
	        form.show();
	    }
	
}
