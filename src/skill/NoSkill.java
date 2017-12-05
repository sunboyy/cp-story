package skill;

import model.GameManager;
import model.player.Player;
import particle.NormalAttack;

public class NoSkill implements ISkill {
	
	int age = 0;
	int maxAge = 30;

	@Override
	public double getDamageMultiplier() {
		return 1;
	}

	@Override
	public double getAttackRange() {
		return 40;
	}

	@Override
	public int getMaxEntity() {
		return 1;
	}

	@Override
	public void use() {
		Player player = GameManager.getInstance().getPlayer();
		if (player.canAttack()) {
			player.attack(this, GameManager.getInstance().getCurrentMap().collideDamageableEntity(player.getAttackArea(this), getMaxEntity()));
			GameManager.getInstance().getCurrentMap().getParticles().add(new NormalAttack());
		}
	}

}
