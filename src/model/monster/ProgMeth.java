package model.monster;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import javafx.util.Pair;
import model.item.BluePotion;
import model.item.Item;
import model.item.RedPotion;
import model.map.Map;

public class ProgMeth extends Monster {
	
	public ProgMeth(Map map, double x, double y) {
		super("Prog Meth", 
				new ArrayList<>(Arrays.asList(Images.monsterProgmethL, Images.monsterProgmethR)), 
				map, x, y, 6, 65, 0, 6, 12, 10);
		drops.add(new Pair<Item, Double>(new RedPotion(), 0.6));
		drops.add(new Pair<Item, Double>(new BluePotion(), 0.2));
	}
}
