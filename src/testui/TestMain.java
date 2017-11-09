package testui;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestMain extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("CPStory UI Test");
		primaryStage.setScene(new SimpleScene());
		primaryStage.show();
	}
}
