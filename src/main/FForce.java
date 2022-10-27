package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.*;


import java.io.FileInputStream;
import java.io.IOException;

public class FForce extends Application {
	private static Scene scene;

	public FForce() {

	}

	@Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
    
        Scene scene = new Scene(root, 500, 500);
    
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
	
}
