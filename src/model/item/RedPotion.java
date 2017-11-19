package model.item;

import constants.Images;
import model.GameManager;

public class RedPotion extends UsableItem {

	public RedPotion() {
		super("Red Potion", Images.redPotionItem);
	}

	@Override
	public void use() {
		if (count > 0) {
			GameManager.getInstance().getPlayer().heal(50);
			--count;
		}
	}

}