package model;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import model.map.Map;
import model.player.Player;

public class GameManager {
	
	private List<Map> maps;
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
		//TODO Render Status bar
		gc.drawImage(player.getImg(), player.getX()-currentMap.getX(), player.getY()-currentMap.getY());
	}
	
	public void setMap(Map m) {
		currentMap = m;
	}
	
}
