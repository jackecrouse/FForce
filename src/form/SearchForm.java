package form;

import database.Utilities;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchForm extends Application{
	
	private Incident incident;

	public static void main(String [] args) {
		launch();
	}
	
	@Override
	public void start(Stage root) throws Exception {
		incident = new Incident();
		
		VBox screen = new VBox();
		screen.setPadding(new Insets(5));
		root.setScene(new Scene(screen, 500,600));
		
		VBox search = new VBox();
		search.setPadding(new Insets(5));
		search.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		screen.getChildren().add(search);
		
		ScrollPane results = new ScrollPane();
		screen.heightProperty().addListener((InvalidationListener) observable -> {
			results.setPrefViewportHeight(screen.getHeight()-search.getHeight()-19);
		});
		results.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		results.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		screen.getChildren().add(results);
		
		search.getChildren().add(createSearchPane());
		
		results.setContent(createResultsPane());
		
		root.show();
		
		results.setPrefViewportHeight(screen.getHeight()-search.getHeight()-19);
	}
	
	private Pane createSearchPane() {
		GridPane search = new GridPane();
		search.setPadding(new Insets(5));
		
		search.add(new Label("Search By:"), 0, 0);
		ChoiceBox<String> criteria = new ChoiceBox<String>();
		criteria.getItems().addAll("Incident Number", "Badge Number");
		criteria.getSelectionModel().select(0);
		search.add(criteria, 0, 1);
		
		search.add(new Label("Search For:"), 1, 0);
		TextField value = new TextField();
		FormUtil.makeTextFieldNumeric(value);
		search.add(value, 1, 1);
		
		Button processSearch = new Button("Search");
		search.add(processSearch, 2, 1);
		
		return search;
	}

	private Pane createResultsPane() {
		
		VBox result = new VBox();
		result.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		addIncidentInformation(result);
		addOfficerInformation(result);
		for(int i=0; i < incident.subjects.size(); i++) {
			addSubjectInformation(result);
		}
		addInjuryInformation(result);
		addSignatureInformation(result);
		
		return result;
	}

	private void addIncidentInformation(VBox vbxResult) {
		
		vbxResult.getChildren().add(new Label("A. Incident Information"));
		((Labeled) vbxResult.getChildren().get(0)).setFont(Font.font("", FontWeight.BOLD, 14));
		
		VBox vbxIncInfo = new VBox();
		vbxResult.getChildren().add(vbxIncInfo);
		
		GridPane grdDateTime = new GridPane();
		grdDateTime.setGridLinesVisible(true);
		vbxIncInfo.getChildren().add(grdDateTime);
		
		grdDateTime.add(new Label("Date"), 0, 0);
		grdDateTime.add(new Label(Utilities.convertDate(incident.incidentDate)), 0, 1);
		grdDateTime.add(new Label("Time"), 1, 0);
		grdDateTime.add(new Label("<Example Time>"), 1, 1);
		grdDateTime.add(new Label("Day of Week"), 2, 0);
		grdDateTime.add(new Label("<Example Day of Week>"), 2, 1);
		grdDateTime.add(new Label("Location"), 3, 0);
		grdDateTime.add(new Label("<Example Location>"), 3, 1);
		grdDateTime.add(new Label("FUPD Case #"), 4, 0);
		grdDateTime.add(new Label("<Example FUPD Case #>"), 4, 1);
		
		VBox vbxIncType = new VBox();
		vbxIncType.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		vbxIncInfo.getChildren().add(vbxIncType);
		
		vbxIncType.getChildren().add(new Label("Type of Incident"));
		((Labeled) vbxIncType.getChildren().get(0)).setUnderline(true);
		
		vbxIncType.getChildren().add(new Label("<Example Type of Incident>"));
		
	}
	
	private void addOfficerInformation(VBox vbxResult) {
		
	}
		
	private void addSubjectInformation(VBox vbxResult) {
		
	}
	
	private void addInjuryInformation(VBox vbxResult) {
		
	}
	
	private void addSignatureInformation(VBox vbxResult) {
		
	}
	
	
}
