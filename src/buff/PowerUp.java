package buff;

public class PowerUp extends Buff {

	public PowerUp() {
		super(3600);
	}
	
	@Override
	public double getAttackMultiplier() {
		return 0.5;
	}

}
