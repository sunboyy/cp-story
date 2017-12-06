package skill;

import model.GameManager;
import model.IUsable;

public abstract class Skill implements IUsable {
	
	public abstract int getMpUse();
	
	public boolean shouldUse() {
		return GameManager.getInstance().getPlayer().canUseMp(getMpUse());
	}
	
	public void activate() {
		if (shouldUse()) {
			GameManager.getInstance().getPlayer().useMp(getMpUse());
			use();
		}
	}
	
}
