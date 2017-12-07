package ui;

import constants.Images;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;

public class CreateCharacterScene extends Scene {
	
	Pane root;
	private TextField nameField;
	
	public CreateCharacterScene() {
		super(new Pane(),1000,600);
		root = (Pane) getRoot();
		
		Canvas canvas = new Canvas(1000,600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setGlobalAlpha(0.2);
		gc.drawImage(Images.startscreen, 0, 0);
		gc.setFill(Color.color(0, 0, 0, 1.0));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setGlobalAlpha(0.5);
		gc.setFill(Color.color(1, 1, 1, 0.95));
		gc.fillRoundRect(200, 150, 600, 300, 100, 100);
	    nameField = new TextField();
	    nameField.setMaxWidth(400);
	    nameField.setMinWidth(400);
	    nameField.setLayoutX(300);
	    nameField.setLayoutY(230);
	    nameField.setStyle("-fx-background-color:white;"
	    		+ "-fx-font-size:28px;"
	    		+ "-fx-font-family:Consolas;"
	    		+ "-fx-alignment:center;");
		Button btnBack = new Button("Cancel");
		btnBack.setLayoutX(300);
		btnBack.setLayoutY(320);
		Button btnStart = new Button("Start Game!");
		btnStart.setLayoutX(600);
		btnStart.setLayoutY(320);
		addEventHandler(btnBack);
		addEventHandler(btnStart);
		
		root.getChildren().addAll(canvas,nameField,btnBack,btnStart);
		
	}
	
	private void addEventHandler(Button btn) {
		
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				System.out.println(btn.getText());
				if(btn.getText().equals("Cancel")) {
					Main.getStage().setScene(Main.getStartScene());
				} else if(btn.getText().equals("Start Game!")) {
					String displayName = nameField.getText();
					if(displayName.equals("")||displayName.contains(" ")) {
						Alert alert = new Alert(AlertType.INFORMATION,"Name must not be null or contains space. So your name is JoeToken :)!");
						alert.setTitle("Not Except Name");
						alert.showAndWait();
						Main.setGameScene(new GameScene());
					} else {
						Main.setGameScene(new GameScene(nameField.getText()));
					}
					Main.getStage().setScene(Main.getGameScene());
				}
			}
		});
	}
}
