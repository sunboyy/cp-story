package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.DamageableEntity;
import model.GameManager;
import model.map.Map;

public class Monster extends DamageableEntity {
	
	private int age = 0;
	private int aiDelay;
	private int walkTick;
	private int futureFacing = RIGHT;
	private int aggressiveTick = 300;
	private int maxAggressiveTick = 300;
	
	public Monster(String name, Image imgL, Image imgR, Map map, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(name, imgL, imgR, map, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Monster(String name, Image imgL, Image imgR, Map map, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(name, imgL, imgR, map, x, y, hp, mp, atkLow, atkHigh);
		this.level = level;
		this.experience = experience;
	}
	
	public boolean isAggressive() {
		return aggressiveTick < maxAggressiveTick;
	}
	
	public void update() {
		if (age < 30) age++;
		if (aiDelay > 0) aiDelay--;
		if (aiDelay <= 0 && walkTick > 0) {
			walkTick--;
			setFacing(futureFacing);
			GameManager.getInstance().getCurrentMap().pushAccX(this, facing*0.5);
		}
	}
	
	
	public int getAiDelay() {
		return aiDelay;
	}

	public void setAiDelay(int aiDelay) {
		if (aiDelay < 0) return;
		this.aiDelay = aiDelay;
	}

	public int getWalkTick() {
		return walkTick;
	}

	public void setWalkTick(int walkTick) {
		if (walkTick < 0) return;
		this.walkTick = walkTick;
	}

	public int getFutureFacing() {
		return futureFacing;
	}

	public void setFutureFacing(int futureFacing) {
		if (futureFacing != LEFT && futureFacing != RIGHT) return;
		this.futureFacing = futureFacing;
	}

	public void render(GraphicsContext gc) {
		if (age < 30) {
			gc.setGlobalAlpha(age/30.);
			super.render(gc);
			gc.setGlobalAlpha(1);
		}
		else {
			super.render(gc);
		}
	}
	
	public int getExperience() {
		return experience;
	}

}
