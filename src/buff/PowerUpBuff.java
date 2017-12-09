package buff;

import constants.Images;

public class PowerUpBuff extends Buff {

	public PowerUpBuff() {
		super(3600, Images.skillPowerUp);
	}
	
	@Override
	public double getAttackMultiplier() {
		return 0.5;
	}

}
