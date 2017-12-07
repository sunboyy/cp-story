package model.monster;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import model.item.BluePotion;
import model.item.RedPotion;
import model.map.Map;

public class ProgMeth extends Monster {
	
	public ProgMeth(Map map, double x, double y) {
		super("Prog Meth", 
				new ArrayList<>(Arrays.asList(Images.monsterProgmethL, Images.monsterProgmethR)), 
				map, x, y, 5, 40, 10, 6, 12, 6);
		drops.put(new RedPotion(), 0.6);
		drops.put(new BluePotion(), 0.2);
	}
}
