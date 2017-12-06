package skill;

import model.GameManager;

public class AttackSkill extends Skill {

	@Override
	public boolean shouldUse() {
		return super.shouldUse() && GameManager.getInstance().getPlayer().canAttack();
	}
	
	@Override
	public void use() {
		GameManager.getInstance().getPlayer().attack(this, GameManager.getInstance().getCurrentMap()
				.collideDamageableEntity(GameManager.getInstance().getPlayer().getAttackArea(this), getMaxEntity()));
	}

	public double getDamageMultiplier() {
		return 1;
	}

	public double getAttackRange() {
		return 40;
	}

	public int getMaxEntity() {
		return 1;
	}

	@Override
	public int getMpUse() {
		return 0;
	}

}
