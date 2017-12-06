package skill;

import model.GameManager;

public abstract class AttackSkill extends Skill {

	private long lastUsed = 0;
	
	public abstract double getDamageMultiplier();
	public abstract double getAttackRange();
	public abstract int getMaxEntity();
	public abstract int getCooldownTimeMillis();
	
	@Override
	public boolean shouldUse() {
		return super.shouldUse() && GameManager.getInstance().getPlayer().canAttack() && lastUsed + getCooldownTimeMillis() <= System.currentTimeMillis();
	}
	
	@Override
	public void use() {
		GameManager.getInstance().getPlayer().attack(this, GameManager.getInstance().getCurrentMap()
				.collideDamageableEntity(GameManager.getInstance().getPlayer().getAttackArea(this), getMaxEntity()));
		lastUsed = System.currentTimeMillis();
	}

}
