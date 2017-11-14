package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import model.map.Map;
import model.map.Portal;
import model.player.Player;
import testui.TestMap;

public class GameManager {
	
	private static final GameManager instance = new GameManager();
	
	private boolean isMonsterSpawning = true;
	private List<Map> maps = new ArrayList<>();
	private Player player;
	private Map currentMap;
	
	public GameManager() {
		generateMap();
		bindPortal();
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
		//TODO Render Status bar
	}
	
	public void update() {
		currentMap.update();
		player.update();
	}
	
	private void generateMap() {
		maps.add(new TestMap());
		maps.add(new TestMap());
		currentMap = maps.get(0);
	}
	
	private void bindPortal() {
		maps.get(0).getPortals().add(new Portal(1400, 690, maps.get(1), 100, 690));
		maps.get(1).getPortals().add(new Portal(100, 690, maps.get(0), 1400, 690));
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

	public boolean isMonsterSpawning() {
		return isMonsterSpawning;
	}

	public void setMonsterSpawning(boolean isMonsterSpawning) {
		this.isMonsterSpawning = isMonsterSpawning;
	}
	
}
