package model.monster;

import javafx.scene.image.Image;

public class Boss extends Monster {

	public Boss(String name, Image img, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(name, img, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Boss(String name, Image img, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(name, img,x , y, level, hp, mp, atkLow, atkHigh, experience);
	}
	
}
