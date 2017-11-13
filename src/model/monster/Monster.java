package model.monster;

import javafx.scene.image.Image;
import model.DamageableEntity;

public class Monster extends DamageableEntity {
	
	public Monster(Image img, int hp, int mp, int atkLow, int atkHigh) {
		this(img, 0, 0, hp, mp, atkLow, atkHigh);
	}
	
	public Monster(Image img, double x, double y, int hp, int mp, int atkLow, int atkHigh) {
		super(img, x, y, hp, mp, atkLow, atkHigh);
	}
	
}
