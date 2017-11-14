package ui;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartScene extends Scene {
	
	private StackPane root;
	
	public StartScene(Stage stage) {
		super(new StackPane(), 1000, 600);
		root = (StackPane) getRoot();
		
		Canvas canvas = new Canvas(1000, 600);
		Button btnPlay = new Button("Play");
		
		root.getChildren().addAll(canvas,btnPlay);
		
		btnPlay.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				stage.setScene(new GameScene());
			}
		});
	}
	
}
