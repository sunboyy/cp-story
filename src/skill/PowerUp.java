package skill;

import model.GameManager;

public class PowerUp implements ISkill {

	@Override
	public void use() {
		if (GameManager.getInstance().getPlayer().canUseMp(8)) {
			GameManager.getInstance().getPlayer().useMp(8);
			GameManager.getInstance().getPlayer().addBuff(new buff.PowerUp());
		}
	}

	@Override
	public double getDamageMultiplier() {
		return 0;
	}

	@Override
	public double getAttackRange() {
		return 0;
	}

	@Override
	public int getMaxEntity() {
		return 0;
	}

}
