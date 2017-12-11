package model.item;

import constants.Images;
import controller.GameManager;

public class RedPotion extends Item {

	public RedPotion() {
		super("Red Potion", "Heals 50 HP.", Images.redPotionItem);
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().healHp(50);
	}

}
