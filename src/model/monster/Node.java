package model.monster;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import javafx.util.Pair;
import model.item.Item;
import model.item.RedPotion;
import model.map.Map;

public class Node extends Monster {
	
	public Node(Map map, double x, double y) {
		super("Node",
				new ArrayList<>(Arrays.asList(Images.monsterNodeL,Images.monsterNodeLWalk,
						Images.monsterNodeR,Images.monsterNodeRWalk)),
						map, x, y, 10, 120, 50, 10, 20, 17);
		drops.add(new Pair<Item, Double>(new RedPotion(), 0.6));
	}
	
}
