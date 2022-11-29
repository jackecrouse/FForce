package form;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SearchForm extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ScrollPane pneExternal = new ScrollPane();
		VBox vbxExternal = new VBox();
		pneExternal.setContent(vbxExternal);
		primaryStage.setScene(new Scene(pneExternal, 100,100));
		for(int i=0; i<100; i++) {
			vbxExternal.getChildren().add(createSearchPane(i + ": Hello World!!"));
		}
		primaryStage.show();
	}
	
	private static VBox createSearchPane(String text) {
		VBox vbxInternal = new VBox();
		Text txtInternal = new Text(text);
		vbxInternal.getChildren().add(txtInternal);
		return vbxInternal;
	}
	
}
