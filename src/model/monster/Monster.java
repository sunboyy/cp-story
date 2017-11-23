package model.monster;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.DamageableEntity;
import model.GameManager;
import model.map.Map;

public class Monster extends DamageableEntity {
	
	private Thread aiThread;
	private int age = 0;
	private int aggressiveTick = 300;
	private int maxAggressiveTick = 300;
	
	public Monster(String name, Image imgL, Image imgR, Map map, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(name, imgL, imgR, map, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Monster(String name, Image imgL, Image imgR, Map map, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(name, imgL, imgR, map, x, y, hp, mp, atkLow, atkHigh);
		this.level = level;
		this.experience = experience;
		startThread();
	}
	
	public boolean isAggressive() {
		return aggressiveTick < maxAggressiveTick;
	}
	
	private void monsterAi() {
		while (!isDead() && GameManager.getInstance().isGameRunning() && getMap() == GameManager.getInstance().getCurrentMap()) {
			int delay = (int) Math.floor(Math.random()*8000);
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int direction = (int) Math.floor(Math.random()*2)*2-1;
			int tick = (int) (30+Math.floor(Math.random()*240));
			for (int i=0; i<tick; i++) {
				if (x <= 0 || x + width >= getMap().getWidth()) {
					direction *= -1;
				}
				if (direction < 0)	setFacing(LEFT);
				else	setFacing(RIGHT);
				GameManager.getInstance().getCurrentMap().pushAccX(this, direction*0.5);
				try {
					Thread.sleep(1000/60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update() {
		if (age < 30) age++;
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
	
	public void startThread() {
		if (aiThread == null || !aiThread.isAlive()) {
			aiThread = new Thread(this::monsterAi);
			aiThread.start();
		}
	}
	
	public Thread getAiThread() {
		return aiThread;
	}
	
	public int getExperience() {
		return experience;
	}

}
