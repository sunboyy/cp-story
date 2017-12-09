package skill;

import constants.Sounds;
import controller.GameManager;
import model.monster.Monster;
import particle.GradeA;
import sharedObject.SharedEntity;

public class SharpSpirit extends Skill {

	@Override
	public void use() {
		for (Monster i: SharedEntity.getInstance().getMonsterOfCurrentMap()) {
			i.forceKill();
			Sounds.deadSound.play();
			GameManager.getInstance().getPlayer().addExperience(i.getExperience());
		}
		GameManager.getInstance().getCurrentMap().getParticles().add(new GradeA());
	}

	@Override
	public int getMpUse() {
		return 30;
	}

	@Override
	public int getCooldownTimeMillis() {
		return 120000;
	}

}
