package testui;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GameManager;

public class TestMain extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setOnCloseRequest(e -> {
			GameManager.getInstance().setMonsterSpawning(false);
		});
		primaryStage.setTitle("CPStory UI Test");
		primaryStage.setScene(new SimpleScene());
		primaryStage.show();
	}
}
