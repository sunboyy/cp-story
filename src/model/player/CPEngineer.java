package model.player;

import javafx.scene.image.Image;

public class CPEngineer extends Player {
	
	public CPEngineer() {
		this(0, 0);
	}
	
	public CPEngineer(double x, double y) {
		super(new Image("player.png"), x, y, 450, 200, 40, 70);
	}
	
}
