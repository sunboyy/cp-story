package model.monster;

import javafx.scene.image.Image;
import model.DamageableEntity;

public class Monster extends DamageableEntity {
	
	public Monster(Image img, int hp, int mp, int atk) {
		this(img, 0, 0, hp, mp, atk);
	}
	
	public Monster(Image img, double x, double y, int hp, int mp, int atk) {
		super(img, x, y, hp, mp, atk);
	}
	
}
