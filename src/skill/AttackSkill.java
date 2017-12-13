package skill;

import controller.GameManager;

public abstract class AttackSkill extends Skill {
	
	public abstract double getDamageMultiplier();
	public abstract double getAttackRange();
	public abstract int getMaxEntity();
	public abstract int getCooldownTick();
	
	@Override
	public boolean shouldUse() {
		return super.shouldUse() && GameManager.getInstance().getPlayer().canAttack();
	}
	
	@Override
	public void use() {
		GameManager.getInstance().getPlayer().attack(this);
	}

}
