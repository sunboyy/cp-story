package ui;

import constants.Constants;
import constants.Images;
import controller.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import main.Main;

public class StartScene extends Scene {
	
	private Pane root;
	private Canvas canvas;
	private TextField nameField;
	private Button backBtn;
	private Button startBtn;
	private Timeline screenloop;
	
	private int page = 0;
	private int animationTick = 0;
	
	public StartScene() {
		super(new Pane(), 1000, 600);
		root = (Pane) getRoot();
		
		canvas = new Canvas(1000, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(Images.startscreen, 0, 0);
		gc.drawImage(Images.playbutton, 180, 336);
		addCanvasEventHandler();
		
	    nameField = new TextField();
	    nameField.setMaxWidth(400);
	    nameField.setMinWidth(400);
	    nameField.setLayoutX(300);
	    nameField.setLayoutY(230);
	    nameField.setStyle("-fx-background-color:white;"
	    		+ "-fx-font-size:28px;"
	    		+ "-fx-font-family:Consolas;"
	    		+ "-fx-alignment:center;"
	    		+ "-fx-border-width:2px;"
	    		+ "-fx-border-color:black;"
	    		+ "-fx-border-radius:5px;");
	    nameField.setVisible(false);
		backBtn = new Button("Back");
		backBtn.setLayoutX(300);
		backBtn.setLayoutY(320);
		backBtn.setVisible(false);
		addBackBtnHandler();
		startBtn = new Button("Start Game!");
		startBtn.setLayoutX(600);
		startBtn.setLayoutY(320);
		startBtn.setVisible(false);
		addStartBtnHandler();
		
		KeyFrame kf = new KeyFrame(Duration.seconds(1./60), e -> {
			if (page == 0 || page == 2) {
				animationTick--;
			}
			else if (page == 1) {
				animationTick++;
			}
			gc.drawImage(Images.startscreen, 0, 0);
			gc.drawImage(Images.playbutton, 180, 336);
			gc.setFill(Color.color(1, 1, 1, 0.6*animationTick/30));
			gc.fillRect(0, 0, Constants.MAP_WIDTH, 600);
			gc.setFill(Color.color(1, 1, 1, 0.8));
			gc.fillRoundRect(200, 600-15*animationTick, 600, 300, 100, 100);
			gc.setFont(Font.font("Helvetica", 24));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFill(Color.BLACK);
			gc.fillText("Enter your name:", Constants.MAP_WIDTH/2, 660-15*animationTick);
			if (page == 2) {
				gc.setGlobalAlpha(1-animationTick/30.);
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
				gc.setGlobalAlpha(1);
			}
			if (page == 1 && animationTick == 30) {
				nameField.setVisible(true);
				backBtn.setVisible(true);
				startBtn.setVisible(true);
			}
			if (page == 2 && animationTick == 0) {
				Main.getStage().setScene(Main.getGameScene());
			}
		});
		screenloop = new Timeline();
		screenloop.setCycleCount(30);
		screenloop.getKeyFrames().add(kf);
		
		root.getChildren().addAll(canvas, nameField, backBtn, startBtn);
		
	}
	
	private boolean isOnPlayButton(MouseEvent event) {
		return event.getX() >= 180 && event.getX() < 478 && event.getY() >= 336 && event.getY() < 439;
	}
	
	private void addCanvasEventHandler() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.setOnMouseMoved(e -> {
			if (page == 0) {
				if (isOnPlayButton(e)) {
					gc.drawImage(Images.startscreen, 0, 0);
					gc.drawImage(Images.playbutton_highlight, 180, 336);
				}
				else {
					gc.drawImage(Images.startscreen, 0, 0);
					gc.drawImage(Images.playbutton, 180, 336);
				}
			}
		});
		canvas.setOnMouseClicked(e -> {
			if (page == 0 && isOnPlayButton(e)) {
				page = 1;
				screenloop.play();
			}
		});
	}
	
	private void addBackBtnHandler() {
		backBtn.setOnAction(e -> {
			nameField.setVisible(false);
			backBtn.setVisible(false);
			startBtn.setVisible(false);
			page = 0;
			screenloop.play();
		});
	}
	
	private void addStartBtnHandler() {
		startBtn.setOnAction(e -> {
			if (nameField.getText().trim().equals("")) {
				Alert alert = new Alert(AlertType.INFORMATION, "The name must not be empty, so it will be Joetoken!");
				alert.setHeaderText(null);
				alert.setTitle("Name doesn't accepted");
				alert.showAndWait();
			}
			nameField.setVisible(false);
			backBtn.setVisible(false);
			startBtn.setVisible(false);
			page = 2;
			GameManager.getInstance().startGame();
			GameManager.getInstance().getPlayer().setName(nameField.getText().trim());
			screenloop.play();
		});
	}

}
