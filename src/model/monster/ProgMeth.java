package model.monster;

import constants.Images;
import model.item.RedPotion;
import model.map.Map;

public class ProgMeth extends Monster {
	
	public ProgMeth(Map map, double x, double y) {
		super("Prog Meth", Images.progMethMonster, map, x, y, 5, 40, 10, 12, 20, 6);
		drops.put(new RedPotion(), 0.4);
	}
}
