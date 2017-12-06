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
import main.Main;

public class CreateCharacterScene extends Scene {
	
	Pane root;
	private TextField txtBox;
	
	public CreateCharacterScene() {
		super(new Pane(),1000,600);
		root = (Pane) getRoot();
		
		Canvas canvas = new Canvas(1000,600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(Images.startscreen, 0, 0);
		
	    txtBox = new TextField();
	    txtBox.setLayoutX(300);
	    txtBox.setLayoutY(200);
		Button btnBack = new Button("btnBack");
		btnBack.setLayoutX(300);
		btnBack.setLayoutY(300);
		Button btnStart = new Button("btnStart");
		btnStart.setLayoutX(600);
		btnStart.setLayoutY(300);
		addEventHandler(btnBack);
		addEventHandler(btnStart);
		
		root.getChildren().addAll(btnBack,btnStart,txtBox);
		
	}
	
	private void addEventHandler(Button btn) {
		
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				System.out.println(btn.getText());
				if(btn.getText().equals("btnBack")) {
					Main.getStage().setScene(Main.getStartScene());
				} else if(btn.getText().equals("btnStart")) {
					String displayName = txtBox.getText();
					if(displayName.equals("")||displayName.contains(" ")) {
						Alert alert = new Alert(AlertType.INFORMATION,"Name must not be null or contains space. So your name is JoeToken :)!");
						alert.setTitle("Not Except Name");
						alert.showAndWait();
						Main.setGameScene(new GameScene());
					} else {
						Main.setGameScene(new GameScene(txtBox.getText()));
					}
					Main.getStage().setScene(Main.getGameScene());
				}
			}
		});
	}
}
