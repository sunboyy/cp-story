package model.monster;

import javafx.scene.image.Image;
import model.DamageableEntity;

public class Monster extends DamageableEntity {
	
	public Monster(Image img, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(img, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Monster(Image img, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(img, x, y, hp, mp, atkLow, atkHigh);
		this.level = level;
		this.experience = experience;
	}
	
	public int getExperience() {
		return experience;
	}

}
