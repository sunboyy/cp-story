package model.item;

import javafx.scene.image.Image;

public abstract class UsableItem extends Item {

	public UsableItem(String name, Image img) {
		super(name, img);
	}
	
	public abstract void use();
	
}
