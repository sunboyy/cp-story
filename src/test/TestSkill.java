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
	public void use() {
		if (GameManager.getInstance().getPlayer().useMp(4)) {
			GameManager.getInstance().getPlayer().attack(this, GameManager.getInstance().getCurrentMap().collideDamageableEntity(GameManager.getInstance().getPlayer().getAttackArea(this), getMaxEntity()));
		}
	}
	
}
