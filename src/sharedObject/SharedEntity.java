package sharedObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.Entity;
import model.GameManager;
import model.map.Map;

public class SharedEntity {
	
	private static SharedEntity instance = new SharedEntity();
	
	private List<Entity> entities = new ArrayList<>();
	
	public List<Entity> getEntitiesOfMap(Map map) {
		List<Entity> list = new ArrayList<>();
		for (Entity i: entities) {
			if (i.getMap() == map) {
				list.add(i);
			}
		}
		return list;
	}
	
	public List<Entity> getEntitiesOfCurrentMap() {
		return getEntitiesOfMap(GameManager.getInstance().getCurrentMap());
	}
	
	public void add(Entity e) {
		entities.add(e);
	}
	
	public void addAll(Collection<? extends Entity> items) {
		entities.addAll(items);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public void print() {
		System.out.println();
		for (Entity i: entities) {
			System.out.println(i+" "+i.getMap());
		}
	}
	
	public static SharedEntity getInstance() {
		return instance;
	}
	
}
