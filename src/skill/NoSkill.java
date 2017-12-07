package skill;

import model.GameManager;
import particle.NormalAttack;

public class NoSkill extends AttackSkill {
	
	int age = 0;
	int maxAge = 30;

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
	public int getMpUse() {
		return 0;
	}

	@Override
	public int getCooldownTimeMillis() {
		return 0;
	}

	@Override
	public void use() {
		super.use();
		GameManager.getInstance().getCurrentMap().getParticles().add(new NormalAttack());
	}

	@Override
	public int getCooldownTick() {
		return 30;
	}

}
