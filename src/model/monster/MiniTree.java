package model.monster;

import constants.Images;
import model.item.BluePotion;
import model.item.RedPotion;
import model.map.Map;

public class MiniTree extends Monster {
	
	public MiniTree(Map map, double x, double y) {
		super("Mini Tree", Images.monsterMiniTreeUp, Images.monsterMiniTreeDown, map, x, y, 1, 20, 10,3,8,3 );
		drops.put(new RedPotion(), 0.4);
		drops.put(new BluePotion(), 0.6);
	}

}
