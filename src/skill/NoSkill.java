package skill;

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
	public boolean use() {
		return true;
	}

}
