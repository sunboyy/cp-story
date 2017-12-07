package test;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import model.item.RedPotion;
import model.map.Map;
import model.monster.Monster;

public class TestMonster extends Monster {
	
	public TestMonster(Map map, double x, double y) {
		super("Orange Mushroom", 
				new ArrayList<>(Arrays.asList(Images.orangeMushMonster, Images.orangeMushMonster)), map, x, y, 2, 20, 0, 6, 10, 4);
		drops.put(new RedPotion(), 0.2);
	}

}
