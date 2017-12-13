package ui;

import controller.GameManager;
import input.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GameScene extends Scene {
	
	public GameScene() {
		super(new StackPane(), 1000, 600);
		StackPane root = (StackPane)getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		root.getChildren().add(canvas);
		
		KeyInput.bindScene(this);
		setOnMouseMoved(e -> Tooltip.update(e.getX(), e.getY()));
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			if (GameManager.getInstance().isGameRunning()) {
				if (!GameManager.getInstance().isPausing()) {
					GameManager.getInstance().getCurrentMap().motion(GameManager.getInstance().getPlayer());
					GameManager.getInstance().getCurrentMap().motionAll();
					GameManager.getInstance().update();
				}
				GameManager.getInstance().render(canvas.getGraphicsContext2D());
			}
		});
		
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
	}
	
}
