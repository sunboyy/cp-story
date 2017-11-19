package model.player;

import constants.Images;
import model.map.Map;

public class CPEngineer extends Player {
	
	public CPEngineer(String name, Map map) {
		this(name, map, 0, 0);
	}
	
	public CPEngineer(String name, Map map, double x, double y) {
		super(name, Images.cpEngPlayerL,Images.cpEngPlayerR, map, x, y, 40, 70);
	}
	
}
