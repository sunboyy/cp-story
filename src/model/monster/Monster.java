package model.monster;

import javafx.scene.image.Image;
import model.Entity;

public class Monster extends Entity {
	
	public Monster() {
		super("Monster");
	}
	
	public Monster(String name,int level,int hp,int mp,int attack,int defense) {
		super(name,level,hp,mp,attack,defense);
	}
	
	public Monster(Image img) {
		super(img);
	}
	
}
