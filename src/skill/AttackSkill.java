package skill;

import model.GameManager;

public abstract class AttackSkill extends Skill {
	
	public abstract double getDamageMultiplier();
	public abstract double getAttackRange();
	public abstract int getMaxEntity();
	
	@Override
	public boolean shouldUse() {
		return super.shouldUse() && GameManager.getInstance().getPlayer().canAttack();
	}
	
	@Override
	public void use() {
		GameManager.getInstance().getPlayer().attack(this, GameManager.getInstance().getCurrentMap()
				.collideDamageableEntity(GameManager.getInstance().getPlayer().getAttackArea(this), getMaxEntity()));
	}

}
