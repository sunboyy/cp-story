package model.monster;

import javafx.scene.image.Image;

public class ProgMeth extends Monster {
	
	private static String img_path = ClassLoader.getSystemResource("monster/progmeth.png").toString();
	
	public ProgMeth(double x, double y) {
		super(new Image(img_path), x, y, 180, 90, 12, 20);
	}
}
