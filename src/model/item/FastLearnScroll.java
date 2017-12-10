package model.item;

import buff.FastLearnBuff;
import constants.Images;
import controller.GameManager;

public class FastLearnScroll extends Item {

	public FastLearnScroll() {
		super("Fast Learn Scroll", Images.fastLearnItem);
		maxCount = 1;
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new FastLearnBuff());
	}

}
