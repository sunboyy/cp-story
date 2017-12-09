package skill;

public class MultiKill extends AttackSkill {

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
	public int getMpUse() {
		return 4;
	}

	@Override
	public int getCooldownTimeMillis() {
		return 500;
	}

	@Override
	public void use() {
		super.use();
	}

	@Override
	public int getCooldownTick() {
		return 30;
	}

}
