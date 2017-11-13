package model.monster;

import javafx.scene.image.Image;

public class Boss extends Monster {

	public Boss(Image img, int hp, int mp, int atkLow, int atkHigh) {
		this(img, 0, 0, hp, mp, atkLow, atkHigh);
	}
	
	public Boss(Image img, double x, double y, int hp, int mp, int atkLow, int atkHigh) {
		super(img,x , y, hp, mp, atkLow, atkHigh);
	}
	
}
