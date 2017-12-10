package model.monster;

import java.util.List;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.DamageableEntity;
import model.Entity;
import model.map.Map;
import particle.HPBar;

public abstract class Monster extends DamageableEntity {
	
	private int age = 0;
	private int aiDelay = 0;
	private int walkTick = 0;
	private int futureFacing = RIGHT;
	private int aggressiveTick = 0;
	private HPBar hpBar;
	private List<Image> imgWalking;
	
	public Monster(String name, List<Image> imgWalking, Map map, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(name, imgWalking, map, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Monster(String name, List<Image> imgWalking, Map map, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(name, imgWalking.get(0), imgWalking.get(imgWalking.size()-1)
				, map, x, y, hp, mp, atkLow, atkHigh);
		this.level = level;
		this.experience = experience;
		this.hpBar = new HPBar(this);
		this.imgWalking = imgWalking;
	}
	
	@Override
	public void damage(int hp) {
		super.damage(hp);
		hpBar.resetVisible();
		aggressiveTick = 360;
		aiDelay = 0;
		walkTick = 0;
	}
	
	public boolean isAggressive() {
		return aggressiveTick > 0;
	}
	
	@Override
	public void update() {
		if (age < 30) age++;
		if (aiDelay > 0) aiDelay--;
		if (aggressiveTick > 0) aggressiveTick--;
		if (aiDelay <= 0 && walkTick > 0) {
			walkTick--;
			int index = (futureFacing+1)*imgWalking.size()/4;
			if (walkTick%30<15) index = index + 1 >= (futureFacing==Entity.LEFT?imgWalking.size()/2:imgWalking.size()) ? index : index + 1;
			setFacing(futureFacing, imgWalking.get(index));
			GameManager.getInstance().getCurrentMap().pushAccX(this, facing*0.5);
		} else {
			setFacing(facing,imgWalking.get((facing+1)*imgWalking.size()/4));
		}
	}
	
	public int getAiDelay() {
		return aiDelay;
	}

	public int getWalkTick() {
		return walkTick;
	}

	public int getFutureFacing() {
		return futureFacing;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (age < 30) {
			gc.setGlobalAlpha(age/30.);
			super.render(gc);
			gc.setGlobalAlpha(1);
		}
		else {
			super.render(gc);
		}
		hpBar.render(gc);
	}
	
	public int getExperience() {
		return experience;
	}
	
	public List<Image> getImgWalking(){
		return this.imgWalking;
	}
	
	// Setter
	public void setAiDelay(int aiDelay) {
		if (aiDelay < 0) return;
		this.aiDelay = aiDelay;
	}

	public void setWalkTick(int walkTick) {
		if (walkTick < 0) return;
		this.walkTick = walkTick;
	}

	public void setFutureFacing(int futureFacing) {
		if (futureFacing != LEFT && futureFacing != RIGHT) return;
		this.futureFacing = futureFacing;
	}
	
}
