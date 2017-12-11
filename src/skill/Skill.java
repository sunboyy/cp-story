package skill;

import controller.GameManager;
import exception.CooldownException;
import exception.MpNotEnoughException;
import model.IUsable;

public abstract class Skill implements IUsable {
	
	private long lastUsedTimeMillis = 0;
	
	public abstract String getName();
	public abstract String getDescription();
	public abstract int getMpUse();
	public abstract int getCooldownTimeMillis();
	
	public boolean shouldUse() {
		return true;
	}
	
	public long getRemainingCooldownTimeMillis() {
		return getCooldownTimeMillis()+lastUsedTimeMillis-System.currentTimeMillis();
	}
	
	private void checkCooldown() throws CooldownException {
		if (lastUsedTimeMillis + getCooldownTimeMillis() > System.currentTimeMillis())
			throw new CooldownException();
	}
	
	@Override
	public void activate() {
		if (!shouldUse()) return;
		try {
			checkCooldown();
			GameManager.getInstance().getPlayer().useMp(getMpUse());
			use();
			lastUsedTimeMillis = System.currentTimeMillis();
		} catch (CooldownException e) {
			GameManager.getInstance().setMessage("Cannot use skill: Please wait for cooldown");
		} catch (MpNotEnoughException e) {
			GameManager.getInstance().setMessage("Cannot use skill: Not enough MP");
		}
	}
	
}
