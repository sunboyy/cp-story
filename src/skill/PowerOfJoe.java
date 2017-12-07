package skill;

public class PowerOfJoe extends AttackSkill {

	@Override
	public double getDamageMultiplier() {
		return 0.5;
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
	public int getCooldownTick() {
		return 8;
	}

	@Override
	public int getMpUse() {
		return 2;
	}

	@Override
	public int getCooldownTimeMillis() {
		return 0;
	}

}
