package skill;

import model.GameManager;

public class PowerUp extends Skill {

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new buff.PowerUp());
	}

	@Override
	public int getMpUse() {
		return 8;
	}

	@Override
	public int getCooldownTimeMillis() {
		return 0;
	}

}
