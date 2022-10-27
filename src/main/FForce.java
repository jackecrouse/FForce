package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class FForce extends Application {
	private static Scene scene;

	public FForce() {

	}

	public void start(Stage stage) throws Exception {
//		FileInputStream fileInputStream = new FileInputStream(new File("src/main/java/CRUD/bnkseekCRUD.fxml"));
 // 		Parent root = FXMLLoader.load(getClass().getResource("/Users/lukekvamme/git/FForce/src/main/primary.fxml"));
//		Parent root2 = FXMLLoader.setController("primary.fxml");
		AnchorPane root = (AnchorPane) FXMLLoader.load(FForce.class.getResource("primary.fxml"));
		Scene scene = new Scene(root, 600, 600);

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}
	
	
}
