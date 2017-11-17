package model.player;

import javafx.scene.image.Image;

public class CPEngineer extends Player {
	
	public CPEngineer(String name) {
		this(name, 0, 0);
	}
	
	public CPEngineer(String name, double x, double y) {
		super(name, new Image("player.png"), x, y, 40, 70);
	}
	
}
