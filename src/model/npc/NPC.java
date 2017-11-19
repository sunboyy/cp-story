package model.npc;

import javafx.scene.image.Image;
import model.Entity;
import model.map.Map;

public class NPC extends Entity {

	public NPC(String name, Image img, Map map) {
		this(name, img, map, 0, 0);
	}
	
	public NPC(String name, Image img, Map map, double x, double y) {
		super(name, img, map, x, y);
	}
	
}
