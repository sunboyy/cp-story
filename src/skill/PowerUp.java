package skill;

import buff.PowerUpBuff;
import constants.Sounds;
import controller.GameManager;
import particle.PowerUpEffect;

public class PowerUp extends Skill {

	@Override
	public String getName() {
		return "Power Up";
	}

	@Override
	public String getDescription() {
		return "12 MP, +50% attack for 90s";
	}

	@Override
	public void use() {
		GameManager.getInstance().getPlayer().addBuff(new PowerUpBuff());
		GameManager.getInstance().getCurrentMap().getParticles().add(new PowerUpEffect());
		Sounds.fireSound.play();
	}

	@Override
	public int getMpUse() {
		return 12;
	}

	@Override
	public int getCooldownTimeMillis() {
		return 0;
	}

}
