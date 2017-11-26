package model.item;

import constants.Images;
import model.GameManager;
import model.IUsable;

public class BluePotion extends Item implements IUsable {

	public BluePotion() {
		super("Blue Potion", Images.bluePotionItem);
	}

	@Override
	public void use() {
		if (count > 0) {
			GameManager.getInstance().getPlayer().healMp(20);
			--count;
		}
	}

}
