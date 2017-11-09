package testui;

import java.util.HashSet;
import java.util.Set;

import input.KeyInput;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.GameManager;
import model.player.CPEngineer;

// Simple scene for testing purpose
public class SimpleScene extends Scene {
	
	private StackPane root;
	private GameManager game = new GameManager();
	private Set<KeyCode> activeKeys = new HashSet<>();
	
	public SimpleScene() {
		super(new StackPane(), 1000, 600);
		root = (StackPane) getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		root.getChildren().add(canvas);
		
		KeyInput.bindAll(this);
		
		game.setCurrentMap(new TestMap());
		game.getMaps().add(game.getCurrentMap());
		game.setPlayer(new CPEngineer());
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			if (activeKeys.contains(KeyCode.LEFT)) game.getCurrentMap().pushAccX(game.getPlayer(), -0.5);
			if (activeKeys.contains(KeyCode.RIGHT)) game.getCurrentMap().pushAccX(game.getPlayer(), 0.5);
			if (activeKeys.contains(KeyCode.SPACE)) {
				if (activeKeys.contains(KeyCode.DOWN)) {
					if (game.shouldJumpDown()) {
						game.getPlayer().jumpDown();
					}
				}
				else game.getPlayer().jump();
			}
			game.getCurrentMap().motion(game.getPlayer());
			render(canvas.getGraphicsContext2D());
		});
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
	}
	
	public GameManager getGame() {
		return game;
	}
	public Set<KeyCode> getActiveKeys() {
		return activeKeys;
	}
	
	private void render(GraphicsContext gc) {
		game.render(gc);
	}
	
}
