package skill;

import model.GameManager;

public class NoSkill implements ISkill {

	@Override
	public double getDamageMultiplier() {
		return 1;
	}

	@Override
	public double getAttackRange() {
		return 40;
	}

	@Override
	public int getMaxEntity() {
		return 1;
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().attack(this, GameManager.getInstance().getCurrentMap().collideDamageableEntity(GameManager.getInstance().getPlayer().getAttackArea(this), getMaxEntity()));
	}

}
