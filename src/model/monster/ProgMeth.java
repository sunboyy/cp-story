package model.monster;

import javafx.scene.image.Image;

public class ProgMeth extends Monster {
	
	private static final String img_path = ClassLoader.getSystemResource("monster/progmeth.png").toString();
	
	public ProgMeth(double x, double y) {
		super("Prog Meth", new Image(img_path), x, y, 5, 40, 10, 12, 20, 6);
	}
}
