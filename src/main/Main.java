package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GameManager;
import ui.StartScene;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setOnCloseRequest(e -> {
			GameManager.getInstance().stopGame();
		});
		stage.setTitle("CP Story");
		stage.setScene(new StartScene(stage));
		stage.show();
	}
}
