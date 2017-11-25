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
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.item.Item;
import model.map.Garden;
import model.map.Map;
import model.map.Portal;
import model.map.SkyCafe;
import model.player.Player;

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
		
		//TODO Render Status bar
		
		// Temporarily show experience
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.BLACK);
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BASELINE);
		if (player.level < Constants.MAX_LEVEL) {
			gc.fillText(String.format("Level %d: %d/%d (%.2f%%) | HP: %d/%d | MP: %d/%d", player.getLevel(), player.getExperience(), Constants.LEVEL_EXPERIENCE[player.level], 100.*player.getExperience()/Constants.LEVEL_EXPERIENCE[player.getLevel()], player.getHp(), player.getMaxHp(), player.getMp(), player.getMaxMp()), Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT-20);
			gc.strokeText(String.format("Level %d: %d/%d (%.2f%%) | HP: %d/%d | MP: %d/%d", player.getLevel(), player.getExperience(), Constants.LEVEL_EXPERIENCE[player.level], 100.*player.getExperience()/Constants.LEVEL_EXPERIENCE[player.getLevel()], player.getHp(), player.getMaxHp(), player.getMp(), player.getMaxMp()), Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT-20);
		}
		else {
			gc.fillText(String.format("Level %d", player.getLevel()), Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT-20);
			gc.strokeText(String.format("Level %d", player.getLevel()), Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT-20);
		}
		
		// Temporarily show inventory
		List<Item> inventory = player.getInventory();
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setTextBaseline(VPos.CENTER);
		for (int i=0; i<inventory.size(); i++) {
			gc.fillRect(Constants.MAP_WIDTH-55, 10+50*i, inventory.get(i).getImg().getWidth()+10, inventory.get(i).getImg().getHeight()+10);
			if (inventory.get(i).getCount() > 1) {
				gc.fillText(String.format("%dx", inventory.get(i).getCount()), Constants.MAP_WIDTH-60, 30+50*i);
				gc.strokeText(String.format("%dx", inventory.get(i).getCount()), Constants.MAP_WIDTH-60, 30+50*i);
			}
			gc.drawImage(inventory.get(i).getImg(), Constants.MAP_WIDTH-50, 15+50*i);
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
		maps.add(new SkyCafe());
		currentMap = maps.get(0);
	}
	
	private void bindPortal() {
		maps.get(0).getPortals().add(new Portal(1000, 810, maps.get(1), 100, 810));
		maps.get(1).getPortals().add(new Portal(100, 810, maps.get(0), 1000, 810));
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
		}
	}
	
	private void moveOfWarp() {
		Portal portal = currentMap.collidePortal(player);
		if (isWarping() && portal != null) {
			currentMap = portal.getDestination();
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

	public void stopGame() {
		isGameRunning = false;
		monsterGen.interrupt();
		monsterAi.interrupt();
	}
	
}
