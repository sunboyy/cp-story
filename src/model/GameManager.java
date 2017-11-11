package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import model.map.Map;
import model.player.Player;

public class GameManager {
	
	private static final GameManager instance = new GameManager();
	
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private Map currentMap;
	
	public GameManager() {
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
		gc.drawImage(player.getImg(), player.getX()-currentMap.getX(), player.getY()-currentMap.getY());
		gc.strokeRect(player.getAttackArea().getX()-currentMap.getX(), player.getAttackArea().getY()-currentMap.getY(), player.getAttackArea().getWidth(), player.getAttackArea().getHeight());
		//TODO Render Status bar
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
	
}
