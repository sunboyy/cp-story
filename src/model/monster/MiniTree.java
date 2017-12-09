package model.monster;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import javafx.util.Pair;
import model.item.BluePotion;
import model.item.Item;
import model.item.RedPotion;
import model.map.Map;

public class MiniTree extends Monster {
	
	public MiniTree(Map map, double x, double y) {
		super("Mini Tree", 
				new ArrayList<>(Arrays.asList(Images.monsterMiniTreeUp, Images.monsterMiniTreeDown, Images.monsterMiniTreeUp, Images.monsterMiniTreeDown)),
				map, x, y, 2, 20, 0, 3, 8, 4);
		drops.add(new Pair<Item, Double>(new RedPotion(), 0.3));
		drops.add(new Pair<Item, Double>(new BluePotion(), 0.1));
	}

}
