package main;

import constants.Images;
import controller.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.*;

public class Main extends Application {
	
	private static Stage stage;
	private static Scene startScene;
	private static Scene gameScene = new GameScene();

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage inputStage) throws Exception {
		stage = inputStage;
		startScene = new StartScene();
		stage.setTitle("CP Story");
		stage.setScene(startScene);
		stage.getIcons().add(Images.monsterProgmethR);
		stage.show();
	}
	
	@Override
	public void stop() throws Exception {
		GameManager.getInstance().terminate();
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
	
}
