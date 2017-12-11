package skill;

import controller.GameManager;
import particle.PowerOfJoeEffect;

public class PowerOfJoe extends AttackSkill {

	@Override
	public String getName() {
		return "Power of Joe";
	}

	@Override
	public String getDescription() {
		return "2 MP, -50% hit continuously";
	}

	int age = 0;
	int maxAge = 30;

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
	
	@Override
	public void use() {
		super.use();
		GameManager.getInstance().getCurrentMap().getParticles().add(new PowerOfJoeEffect());
	}
}
