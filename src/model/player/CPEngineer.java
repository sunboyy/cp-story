package model.player;

import constants.Images;

public class CPEngineer extends Player {
	
	public CPEngineer(String name) {
		this(name, 0, 0);
	}
	
	public CPEngineer(String name, double x, double y) {
		super(name, Images.cpEngPlayerL,Images.cpEngPlayerR, x, y, 40, 70);
	}
	
}
