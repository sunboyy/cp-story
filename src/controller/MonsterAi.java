package controller;

import model.Entity;
import model.GameManager;
import model.monster.Monster;
import sharedObject.SharedEntity;

public class MonsterAi extends Thread {
	
	public MonsterAi() {
		super(new Runnable() {
			@Override
			public void run() {
				while (GameManager.getInstance().isGameRunning()) {
					for (Monster m: SharedEntity.getInstance().getMonsterOfCurrentMap()) {
						if (m.getAiDelay() <= 0 && m.getWalkTick() <= 0) {
							m.setAiDelay((int) Math.floor(Math.random()*480));
							if (m.getX() <= 0) {
								m.setFutureFacing(Entity.RIGHT);
							}
							else if (m.getX() + m.getWidth() >= m.getMap().getWidth()) {
								m.setFutureFacing(Entity.LEFT);
							}
							else {
								m.setFutureFacing((int) Math.floor(Math.random()*2)*2-1);
							}
							m.setWalkTick((int) (30+Math.floor(Math.random()*240)));
						}
					}
					try {
						Thread.sleep(1000/60);
					} catch (InterruptedException e) {
						System.out.println("MonsterAi thread has been interrupted");
						break;
					}
				}
			}
		});
		start();
	}
	
}
