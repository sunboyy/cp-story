package skill;

import buff.PowerUpBuff;
import controller.GameManager;

public class PowerUp extends Skill {

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new PowerUpBuff());
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
