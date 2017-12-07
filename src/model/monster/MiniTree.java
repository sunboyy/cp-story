package model.monster;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import model.item.BluePotion;
import model.item.RedPotion;
import model.map.Map;

public class MiniTree extends Monster {
	
	public MiniTree(Map map, double x, double y) {
		super("Mini Tree", 
				new ArrayList<>(Arrays.asList(Images.monsterMiniTreeUp, Images.monsterMiniTreeDown,Images.monsterMiniTreeUp, Images.monsterMiniTreeDown))
				, map, x, y, 1, 20, 10,3,8,3 );
		drops.put(new RedPotion(), 0.4);
		drops.put(new BluePotion(), 0.6);
	}

}
