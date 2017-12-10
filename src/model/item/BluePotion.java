package model.item;

import constants.Images;
import controller.GameManager;

public class BluePotion extends Item {

	public BluePotion() {
		super("Blue Potion", Images.bluePotionItem);
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().healMp(20);
	}

}
