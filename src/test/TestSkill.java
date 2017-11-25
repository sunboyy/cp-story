package test;

import model.GameManager;
import skill.ISkill;

public class TestSkill implements ISkill {

	@Override
	public double getDamageMultiplier() {
		return 1.5;
	}

	@Override
	public double getAttackRange() {
		return 60;
	}

	@Override
	public int getMaxEntity() {
		return 4;
	}

	@Override
	public boolean use() {
		return GameManager.getInstance().getPlayer().useMp(4);
	}

}
