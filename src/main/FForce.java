package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FForce extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
        primaryStage.setTitle("Login");
        Button btn = new Button("Submit");
        btn.setTranslateY(40);

        TextField user = new TextField();
        user.setTranslateY(-50);
        
        PasswordField pass = new PasswordField();
        pass.setTranslateY(-10);
        
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(user.getText());
                System.out.println(pass.getText());
            }
        });
        
        StackPane root = new StackPane();
        
        root.getChildren().addAll(btn, user, pass);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}