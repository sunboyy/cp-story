package skill;

import model.GameManager;
import model.IUsable;

public abstract class Skill implements IUsable {
	
	private long lastUsed = 0;
	
	public abstract int getMpUse();
	public abstract int getCooldownTimeMillis();
	
	public boolean shouldUse() {
		return GameManager.getInstance().getPlayer().canUseMp(getMpUse()) && lastUsed + getCooldownTimeMillis() <= System.currentTimeMillis();
	}
	
	public long getRemainingCooldownTimeMillis() {
		return getCooldownTimeMillis()+lastUsed-System.currentTimeMillis();
	}
	
	public void activate() {
		if (shouldUse()) {
			GameManager.getInstance().getPlayer().useMp(getMpUse());
			use();
			lastUsed = System.currentTimeMillis();
		}
	}
	
}
