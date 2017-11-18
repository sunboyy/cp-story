package model.monster;

import constants.Images;
import model.item.RedPotion;

public class ProgMeth extends Monster {
	
	public ProgMeth(double x, double y) {
		super("Prog Meth", Images.progMethMonster, x, y, 5, 40, 10, 12, 20, 6);
		drops.put(new RedPotion(), 0.2);
	}
}
