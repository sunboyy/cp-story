package testui;

import javafx.scene.image.Image;
import model.monster.Monster;

public class TestMonster extends Monster {
	
	public TestMonster(double x, double y) {
		super(new Image("orangemush.gif"), x, y, 180, 90, 12, 20);
	}

}
