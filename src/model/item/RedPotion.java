package model.item;

import constants.Images;
import controller.GameManager;
import model.IUsable;

public class RedPotion extends Item implements IUsable {

	public RedPotion() {
		super("Red Potion", Images.redPotionItem);
	}

	@Override
	public void use() {
		if (count > 0) {
			GameManager.getInstance().getPlayer().healHp(50);
			--count;
		}
	}

}
