package model;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import controller.MonsterAi;
import controller.MonsterGen;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.map.Building4;
import model.map.Garden;
import model.map.Map;
import model.map.Portal;
import model.map.SkyCafe;
import model.player.Player;
import ui.StatusBar;

public class GameManager {
	
	private static final GameManager instance = new GameManager();
	
	private boolean isGameRunning = true;
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private Map currentMap;
	private int warpTick = 60;
	private int maxWarpTick = 60;
	private MonsterGen monsterGen;
	private MonsterAi monsterAi;
	private String message = "";
	
	public GameManager() {
		generateMap();
		bindPortal();
		monsterGen = new MonsterGen();
		monsterAi = new MonsterAi();
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
		currentMap.render(gc);
		gc.strokeRect(player.getAttackArea().getX()-currentMap.getX(), player.getAttackArea().getY()-currentMap.getY(), player.getAttackArea().getWidth(), player.getAttackArea().getHeight());
		
		if (isWarping()) {
			double alpha = 2.*warpTick/maxWarpTick;
			if (warpTick >= maxWarpTick/2) {
				alpha = 2-alpha;
			}
			gc.setGlobalAlpha(alpha);
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
			gc.setGlobalAlpha(1);
		}
		
		StatusBar.render(gc);
		
		// Render Buffs
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.WHITE);
		gc.setFont(Font.font("Tahoma", 11));
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.TOP);
		gc.setLineWidth(3);
		for (int i=0; i<player.getBuffs().size(); i++) {
			gc.drawImage(player.getBuffs().get(i).getImage(), Constants.MAP_WIDTH-(40*(i+1)), 4);
			gc.strokeText(player.getBuffs().get(i).getRemainingTime()+"", Constants.MAP_WIDTH-(40*(i+1))+2, 4);
			gc.fillText(player.getBuffs().get(i).getRemainingTime()+"", Constants.MAP_WIDTH-(40*(i+1))+2, 4);
		}
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
		currentMap.warpIn();
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
			player.x = portal.getXDest()-player.width/2;
			player.y = portal.getYDest()-player.height;
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

	public void stopGame() {
		isGameRunning = false;
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
	
}
