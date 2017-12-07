package model.monster;

import java.util.List;

import javafx.scene.image.Image;
import model.map.Map;

public class Boss extends Monster {

	public Boss(String name, List<Image> imgWalking, Map map, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(name, imgWalking, map, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Boss(String name, List<Image> imgWalking, Map map, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(name, imgWalking, map, x, y, level, hp, mp, atkLow, atkHigh, experience);
	}
	
}
