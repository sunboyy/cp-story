package skill;

import constants.Sounds;
import controller.GameManager;
import model.monster.Monster;
import particle.GradeAEffect;
import sharedObject.SharedEntity;

public class GradeAForSure extends Skill {

	@Override
	public String getName() {
		return "Grade A for sure";
	}

	@Override
	public String getDescription() {
		return "Kill all monsters in the map";
	}

	@Override
	public void use() {
		for (Monster i: SharedEntity.getInstance().getMonsterOfCurrentMap()) {
			i.forceKill();
			Sounds.deadSound.play();
			GameManager.getInstance().getPlayer().addExperience(i.getExperience());
		}
		GameManager.getInstance().getCurrentMap().getParticles().add(new GradeAEffect());
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
