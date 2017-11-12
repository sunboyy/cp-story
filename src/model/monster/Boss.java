package model.monster;

import javafx.scene.image.Image;

public class Boss extends Monster {

	public Boss(Image img, int hp, int mp, int atk) {
		this(img, 0, 0, hp, mp, atk);
	}
	
	public Boss(Image img, double x, double y, int hp, int mp, int atk) {
		super(img,x , y, hp, mp, atk);
	}
	
}
