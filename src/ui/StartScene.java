package ui;

import constants.Images;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;

public class StartScene extends Scene {
	
	private Pane root;
	private Scene createCharacterScene;
	
	public StartScene() {
		super(new Pane(), 1000, 600);
		root = (Pane) getRoot();
		createCharacterScene = new CreateCharacterScene();
		
		Canvas canvas = new Canvas(1000, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(Images.startscreen, 0, 0);
		
		ImageView btnPlay = new ImageView(Images.playbutton);
		btnPlay.setLayoutX(180);
		btnPlay.setLayoutY(336);
		btnPlay.setPickOnBounds(false);
		addBtnPlayEventHandler(btnPlay);
		
		root.getChildren().addAll(canvas,btnPlay);
		
	}
	
	private void addBtnPlayEventHandler(ImageView btn) {
		
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				btn.setImage(Images.playbutton);
				Main.getStage().setScene(createCharacterScene);
			}
		});
		
		btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				btn.setImage(Images.playbutton_highlight);
				Canvas canvas = new Canvas(300,300);
				GraphicsContext gc = canvas.getGraphicsContext2D();
				gc.setFill(Color.WHITE);
				gc.fillRect(0, 0, 200, 5);
				canvas.setLayoutX(220);
				canvas.setLayoutY(420);
				root.getChildren().add(canvas);
			}
		});
		
		btn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				btn.setImage(Images.playbutton);
				root.getChildren().remove(2);
			}
		});
		
	}
	
}
