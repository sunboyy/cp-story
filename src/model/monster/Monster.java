package model.monster;

import javafx.scene.image.Image;
import model.DamageableEntity;
import model.GameManager;
import model.map.Map;

public class Monster extends DamageableEntity {
	
	private Thread aiThread;
	
	public Monster(String name, Image img, Map map, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		this(name, img, map, 0, 0, level, hp, mp, atkLow, atkHigh, experience);
	}
	
	public Monster(String name, Image img, Map map, double x, double y, int level, int hp, int mp, int atkLow, int atkHigh, int experience) {
		super(name, img, map, x, y, hp, mp, atkLow, atkHigh);
		this.level = level;
		this.experience = experience;
		aiThread = new Thread(this::monsterAi);
	}
	
	private void monsterAi() {
		while (!isDead() && GameManager.getInstance().isMonsterSpawning() && getMap() == GameManager.getInstance().getCurrentMap()) {
			int delay = (int) Math.floor(Math.random()*8000)+500;
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
				GameManager.getInstance().getCurrentMap().pushAccX(this, direction*0.5);
				try {
					Thread.sleep(1000/60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Thread getAiThread() {
		return aiThread;
	}
	
	public int getExperience() {
		return experience;
	}

}
