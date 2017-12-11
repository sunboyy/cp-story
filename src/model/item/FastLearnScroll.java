package model.item;

import buff.FastLearnBuff;
import constants.Images;
import controller.GameManager;

public class FastLearnScroll extends Item {

	public FastLearnScroll() {
		super("Fast Learn Scroll", "+50% exp gain rate for 60s", Images.fastLearnItem);
		maxCount = 1;
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new FastLearnBuff());
	}

}
