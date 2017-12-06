package model.npc;

import javafx.scene.image.Image;
import model.Entity;
import model.map.Map;

public abstract class Npc extends Entity {

	public Npc(String name, Image imgL, Image imgR, Map map) {
		this(name, imgL, imgR, map, 0, 0);
	}
	
	public Npc(String name, Image imgL, Image imgR, Map map, double x, double y) {
		super(name, imgL, imgR, map, x, y);
	}
	
}
