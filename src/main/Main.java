package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GameManager;
import ui.*;

public class Main extends Application {
	
	private static Stage stage;
	private static Scene startScene,gameScene;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage inputStage) throws Exception {
		stage = inputStage;
		startScene = new StartScene();
		stage.setOnCloseRequest(e -> {
			GameManager.getInstance().stopGame();
		});
		stage.setTitle("CP Story");
		stage.setScene(startScene);
		stage.show();
	}
	
	@Override
	public void stop() throws Exception {
		GameManager.getInstance().stopGame();
		super.stop();
	}

	public static Stage getStage() {
		return stage;
	}

	public static Scene getStartScene() {
		return startScene;
	}

	public static Scene getGameScene() {
		return gameScene;
	}

	public static void setStartScene(Scene scene) {
		startScene = scene;
	}

	public static void setGameScene(Scene scene) {
		gameScene = scene;
	}
	
	
}
