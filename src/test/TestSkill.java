package test;

import model.GameManager;
import model.player.Player;
import skill.ISkill;

public class TestSkill implements ISkill {

	@Override
	public double getDamageMultiplier() {
		return 1.5;
	}

	@Override
	public double getAttackRange() {
		return 60;
	}

	@Override
	public int getMaxEntity() {
		return 4;
	}

	@Override
	public void use() {
		Player player = GameManager.getInstance().getPlayer();
		if (player.canUseMp(4) && player.canAttack()) {
			player.useMp(4);
			player.attack(this, GameManager.getInstance().getCurrentMap().collideDamageableEntity(player.getAttackArea(this), getMaxEntity()));
		}
	}
	
}
