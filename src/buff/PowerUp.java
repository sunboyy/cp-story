package buff;

import constants.Images;

public class PowerUp extends Buff {

	public PowerUp() {
		super(3600, Images.skillPowerUp);
	}
	
	@Override
	public double getAttackMultiplier() {
		return 0.5;
	}

}
