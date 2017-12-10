package skill;

import buff.PowerUpBuff;
import constants.Sounds;
import controller.GameManager;

public class PowerUp extends Skill {

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new PowerUpBuff());
		GameManager.getInstance().getCurrentMap().getParticles().add(new particle.PowerUp());
		Sounds.fireSound.play();
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
