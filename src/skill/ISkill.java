package skill;

import model.IUsable;

public interface ISkill extends IUsable {
	public double getDamageMultiplier();
	public double getAttackRange();
	public int getMaxEntity();
}
