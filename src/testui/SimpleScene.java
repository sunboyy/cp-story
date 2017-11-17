
package testui;

import java.lang.reflect.InvocationTargetException;

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
	
	public static Thread monsterGen;
	
	private StackPane root;
	
	public SimpleScene() {
		super(new StackPane(), 1000, 600);
		root = (StackPane) getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		root.getChildren().add(canvas);
		
		KeyInput.bindScene(this);
		
		GameManager.getInstance().setPlayer(new CPEngineer("Joetoken", 500, 550));
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			GameManager.getInstance().getCurrentMap().motion(GameManager.getInstance().getPlayer());
			GameManager.getInstance().getCurrentMap().motionAll();
			GameManager.getInstance().render(canvas.getGraphicsContext2D());
			GameManager.getInstance().update();
		});
		Timeline gameloop = new Timeline();
		gameloop.setCycleCount(Timeline.INDEFINITE);
		gameloop.getKeyFrames().add(kf);
		gameloop.play();
		
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (GameManager.getInstance().getCurrentMap().getEntities().size() < 20) {
						try {
							GameManager.getInstance().getCurrentMap().spawnRandom();
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
					}
					if (!GameManager.getInstance().isMonsterSpawning()) {
						break;
					}
				}
			}
		};
		monsterGen = new Thread(runner);
		monsterGen.start();
	}
	
}
