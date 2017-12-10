package model.item;

import buff.FastLearnBuff;
import constants.Images;
import controller.GameManager;

public class FastLearn extends Item {

	public FastLearn() {
		super("Fast Learn", Images.fastLearnItem);
		maxCount = 1;
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new FastLearnBuff());
	}

}
