package testui;

import javafx.scene.image.Image;
import model.monster.Monster;

public class TestMonster extends Monster {
	
	public TestMonster(double x, double y) {
		super(new Image("orangemush.gif"), x, y, 2, 20, 0, 12, 20, 4);
	}

}
