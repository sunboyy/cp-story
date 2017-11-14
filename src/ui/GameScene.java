package ui;

import input.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.GameManager;
import model.map.Garden;
import model.player.CPEngineer;

public class GameScene extends Scene {
	
	private StackPane root;
	
	public GameScene() {
		super(new StackPane(), 1000, 600);
		root = (StackPane)getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		root.getChildren().add(canvas);
		
		KeyInput.bindScene(this);
		
		GameManager.getInstance().setPlayer(new CPEngineer(500,550));
		GameManager.getInstance().getMaps().add(new Garden());
		
		GameManager.getInstance().setCurrentMap(GameManager.getInstance().getMaps().get(2));
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60),e -> {
			GameManager.getInstance().getCurrentMap().motion(GameManager.getInstance().getPlayer());
			GameManager.getInstance().getCurrentMap().motionAll();
			GameManager.getInstance().render(canvas.getGraphicsContext2D());
			GameManager.getInstance().update();
		});
		
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
	}
	
}