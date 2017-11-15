package model.monster;

import javafx.scene.image.Image;

public class Boss extends Monster {

	public Boss(Image img, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(img, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Boss(Image img, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(img,x , y, level, hp, mp, atkLow, atkHigh, experience);
	}
	
}
