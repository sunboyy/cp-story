package testui;

import input.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.GameManager;
import model.player.CPEngineer;

// Simple scene for testing purpose
public class SimpleScene extends Scene {
	
	private StackPane root;
	
	public SimpleScene() {
		super(new StackPane(), 1000, 600);
		root = (StackPane) getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		root.getChildren().add(canvas);
		
		setOnKeyPressed(e -> KeyInput.addKey(e.getCode()));
		setOnKeyReleased(e -> KeyInput.removeKey(e.getCode()));
		
		GameManager.getInstance().setCurrentMap(new TestMap());
		GameManager.getInstance().getMaps().add(GameManager.getInstance().getCurrentMap());
		GameManager.getInstance().setPlayer(new CPEngineer());
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			GameManager.getInstance().getPlayer().update();
			GameManager.getInstance().getCurrentMap().motion(GameManager.getInstance().getPlayer());
			GameManager.getInstance().getCurrentMap().motionAll();
			GameManager.getInstance().render(canvas.getGraphicsContext2D());
		});
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
	}
	
}
