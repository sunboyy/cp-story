package model.npc;

import javafx.scene.image.Image;
import model.Entity;

public class NPC extends Entity {

	public NPC(String name, Image img) {
		this(name, img, 0, 0);
	}
	
	public NPC(String name, Image img, double x, double y) {
		super(name, img, x, y);
	}
	
}
