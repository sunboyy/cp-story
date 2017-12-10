package buff;

import constants.Images;

public class FastLearnBuff extends Buff {

	public FastLearnBuff() {
		super(60000, Images.fastLearnBuff);
	}

	@Override
	public double getAttackMultiplier() {
		return 0;
	}

	@Override
	public double getExperienceMultiplier() {
		return 0.5;
	}

}
