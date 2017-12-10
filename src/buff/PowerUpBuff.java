package buff;

import constants.Images;

public class PowerUpBuff extends Buff {

	public PowerUpBuff() {
		super(90000, Images.skillPowerUp);
	}
	
	@Override
	public double getAttackMultiplier() {
		return 0.5;
	}

	@Override
	public double getExperienceMultiplier() {
		return 0;
	}

}
