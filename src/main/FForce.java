package main;


import java.sql.SQLException;

import database.SQL;
import form.UOFIncidentForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FForce extends Application {
	private static Scene loginScene;
	public static Scene homePageScene, formCreationScene, browseFormsScene;
	public static Stage window;
	private static Button submitButton;
	private static Button toLoginButton;
	private static Button createForm;
	private static Button browseForms;
	private static TextField _username;
	private static PasswordField _password;

	public FForce() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;

		GridPane loginGrid = createLoginPage();
		loginScene = new Scene(loginGrid, 400, 300);

		StackPane homePage = createHomePage();
		homePageScene = new Scene(homePage, 400, 400);

		GridPane formGrid = createFormPage();
		formCreationScene = new Scene(formGrid, 400, 300);

		GridPane browseGrid = createBrowsePage();
		browseFormsScene = new Scene(browseGrid, 400, 300);

		submitButton.setOnAction(e -> {
			

			try {
				if(validateLogin(_username, _password))
				{
					System.out.println("Success");
					window.setScene(homePageScene);
				}
				else
				{
					System.out.println("Failure");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}); // need to have an event handler method that
			// authenticates the user, for now just change
			// scene
		toLoginButton.setOnAction(e -> window.setScene(loginScene));
		createForm.setOnAction(e -> {
			window.setScene(formCreationScene);
			UOFIncidentForm incidentStage = new UOFIncidentForm();
			Stage form = incidentStage.create(primaryStage);
			form.show();
		});
		browseForms.setOnAction(e -> window.setScene(browseFormsScene));

		window.setScene(loginScene); // sets first scene shown
		window.setTitle("Fupo Force App");
		window.show();
	}

	
	private static boolean validateLogin(TextField username, PasswordField password) throws SQLException
	{
		SQL connection = new SQL();
		String user = username.getText();
		String pass = password.getText();
		return connection.isUser(user, pass);
	}


	public static GridPane createBrowsePage() {
		Label browse = new Label("form browsing page");
		GridPane grid = new GridPane();

		grid.setHgap(3);
		grid.setVgap(3);

		grid.add(browse, 0, 0);

		return grid;
	}

	public static GridPane createFormPage() {
		Label creation = new Label("form creation page");
		GridPane grid = new GridPane();

		grid.setHgap(3);
		grid.setVgap(3);

		grid.add(creation, 0, 0);

		return grid;
	}

	public static StackPane createHomePage() {
		StackPane homePage = new StackPane();
		toLoginButton = new Button("go to login page");
		createForm = new Button("go to form creation");
		browseForms = new Button("go to past forms");

		toLoginButton.setTranslateX(-110);
		toLoginButton.setTranslateY(-160);

		createForm.setTranslateX(-110);
		createForm.setTranslateY(-120);

		browseForms.setTranslateX(-110);
		browseForms.setTranslateY(-80);

		homePage.getChildren().addAll(toLoginButton, createForm, browseForms);

		return homePage;

	}

	public static GridPane createLoginPage() {
		Label welcome = new Label("Welcome to the login page!");
		submitButton = new Button("Log in");
		Label user = new Label("Username:");
		Label pass = new Label("Password:");

		GridPane grid = new GridPane();
		_username = new TextField();
		_password = new PasswordField();

		grid.setHgap(3); // width gap
		grid.setVgap(3); // vertical gap

		grid.add(welcome, 20, 0, 10, 10);
		grid.add(user, 0, 0, 1, 2);
		grid.add(_username, 0, 5, 1, 10);
		grid.add(pass, 0, 15, 1, 2);
		grid.add(_password, 0, 20, 1, 10);
		grid.add(submitButton, 0, 30, 1, 10);

		return grid;
	}

	public static void main(String[] args) {
		launch(args);
	}

}