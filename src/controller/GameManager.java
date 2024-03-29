package controller;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.Images;
import input.KeyInput;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Main;
import model.map.Building4;
import model.map.Garden;
import model.map.Map;
import model.map.Portal;
import model.map.SkyCafe;
import model.player.CPEngineer;
import model.player.Player;
import sharedObject.SharedEntity;
import ui.StartScene;
import ui.StatusBar;
import ui.Tooltip;

public class GameManager {
	
	private static GameManager instance = new GameManager();
	
	private boolean isGameRunning = false;
	private boolean isPausing = false;
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private Map currentMap;
	private int warpTick = 60;
	private int maxWarpTick = 60;
	private MonsterGen monsterGen;
	private MonsterAi monsterAi;
	private String message = "";
	private long startTimeMillis;
	
	public GameManager() {
		generateMap();
		bindPortal();
		monsterGen = new MonsterGen();
		monsterAi = new MonsterAi();
		player = new CPEngineer("Joetoken", currentMap, 500, 550);
	}

	public List<Map> getMaps() {
		return maps;
	}

	public Player getPlayer() {
		return player;
	}

	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void render(GraphicsContext gc) {
		
		if (!isPausing) {
			currentMap.render(gc);
			
			if (isWarping()) {
				double alpha = 2.*warpTick/maxWarpTick;
				if (warpTick >= maxWarpTick/2) {
					alpha = 2-alpha;
				}
				gc.setGlobalAlpha(alpha);
				gc.setFill(Color.BLACK);
				gc.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
				gc.setGlobalAlpha(1);
			}
			
			// Render Buffs
			gc.setFill(Color.BLACK);
			gc.setStroke(Color.WHITE);
			gc.setFont(Font.font("Tahoma", 11));
			gc.setTextAlign(TextAlignment.LEFT);
			gc.setTextBaseline(VPos.TOP);
			gc.setLineWidth(3);
			for (int i=0; i<player.getBuffs().size(); i++) {
				gc.drawImage(player.getBuffs().get(i).getImage(), Constants.WINDOW_WIDTH-(40*(i+1)), 4);
				gc.strokeText(player.getBuffs().get(i).getRemainingTime()+"", Constants.WINDOW_WIDTH-(40*(i+1))+2, 4);
				gc.fillText(player.getBuffs().get(i).getRemainingTime()+"", Constants.WINDOW_WIDTH-(40*(i+1))+2, 4);
			}
		}
		StatusBar.render(gc);
		Tooltip.render(gc);
	}
	
	public void update() {
		if (isWarping()) {
			warpTick++;
			if (warpTick == maxWarpTick/2) {
				moveOfWarp();
			}
		}
		else {
			player.update();
		}
		currentMap.update();
	}
	
	private void generateMap() {
		maps.add(new Garden());
		maps.add(new Building4());
		maps.add(new SkyCafe());
		currentMap = maps.get(0);
	}
	
	private void bindPortal() {
		maps.get(0).getPortals().add(new Portal(1000, 810, maps.get(1), 100, 810));
		maps.get(1).getPortals().add(new Portal(100, 810, maps.get(0), 1000, 810));
		maps.get(1).getPortals().add(new Portal(665, 635, maps.get(2), 110, 810));
		maps.get(2).getPortals().add(new Portal(110, 810, maps.get(1), 665, 635));
		maps.get(2).getPortals().add(new Portal(1240, 810, maps.get(1), 665, 635));
	}
	
	public boolean isWarping() {
		return warpTick < maxWarpTick;
	}
	
	public boolean shouldWarp() {
		Portal portal = currentMap.collidePortal(player);
		if (!isWarping() && portal != null) {
			return true;
		}
		else return false;
	}
	
	public void warp() {
		if (shouldWarp()) {
			warpTick = 0;
			currentMap.warpOut();
		}
	}
	
	private void moveOfWarp() {
		Portal portal = currentMap.collidePortal(player);
		if (isWarping() && portal != null) {
			currentMap = portal.getDestination();
			currentMap.warpIn();
			player.setX(portal.getXDest()-player.getWidth()/2);
			player.setY(portal.getYDest()-player.getHeight());
			player.setMap(portal.getDestination());
		}
	}
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public void setCurrentMap(Map m) {
		currentMap = m;
	}
	
	public boolean shouldJumpDown() {
		return currentMap.getStructure().collideWith(player) != null && currentMap.getStructure().collideWith(player).isPassable();
	}
	
	public static GameManager getInstance() {
		return instance;
	}
	
	public boolean isGameRunning() {
		return isGameRunning;
	}
	
	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}
	
	public void startGame() {
		isGameRunning = true;
		startTimeMillis = System.currentTimeMillis();
		currentMap.warpIn();
	}

	public void stopGame() {
		isGameRunning = false;
		terminate();
		long elapsedTimeMillis = System.currentTimeMillis()-startTimeMillis;
		long elapsedTime = elapsedTimeMillis/1000;
		KeyInput.clear();
		SharedEntity.getInstance().clear();
		Main.setStartScene(new StartScene());
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION, String.format("Congratulations! You have reached level 20.\nTime spent: %02d:%02d:%02d\nScore: %d", elapsedTime/3600, (elapsedTime/60)%60, elapsedTime%60, (int) (1000000*Math.pow(2, -elapsedTimeMillis/500000.))), ButtonType.CLOSE);
			alert.setHeaderText(null);
			alert.setTitle("Congratulations!");
			((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(Images.monsterProgmethR);
			alert.showAndWait();
			Main.getStage().setScene(Main.getStartScene());
			instance = new GameManager();
			currentMap.warpOut();
		});
	}
	
	public boolean isPausing() {
		return isPausing;
	}
	
	public void setPausing(boolean isPausing) {
		this.isPausing = isPausing;
	}
	
	public void terminate() {
		monsterGen.interrupt();
		monsterAi.interrupt();
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		if (message == null) return;
		this.message = message;
	}
	
	public long getStartTimeMillis() {
		return startTimeMillis;
	}
	
}
