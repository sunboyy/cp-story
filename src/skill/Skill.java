package skill;

import model.GameManager;
import model.IUsable;

public abstract class Skill implements IUsable {
	
	private long lastUsed = 0;
	
	public abstract int getMpUse();
	public abstract int getCooldownTimeMillis();
	
	public boolean shouldUse() {
		return true;
	}
	
	public long getRemainingCooldownTimeMillis() {
		return getCooldownTimeMillis()+lastUsed-System.currentTimeMillis();
	}
	
	private void checkCooldown() throws CooldownException {
		if (lastUsed + getCooldownTimeMillis() > System.currentTimeMillis())
			throw new CooldownException();
	}
	
	public void activate() {
		if (shouldUse()) {
			try {
				checkCooldown();
				GameManager.getInstance().getPlayer().useMp(getMpUse());
				use();
				lastUsed = System.currentTimeMillis();
			} catch (CooldownException e) {
				GameManager.getInstance().setMessage("Cannot use skill: Please wait for cooldown");
			} catch (MpNotEnoughException e) {
				GameManager.getInstance().setMessage("Cannot use skill: Not enough MP");
			}
		}
	}
	
}
