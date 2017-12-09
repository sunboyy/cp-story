package controller;

import model.Entity;
import model.monster.Monster;
import sharedObject.SharedEntity;
import utility.Random;

public class MonsterAi extends Thread {
	
	public MonsterAi() {
		super(new Runnable() {
			@Override
			public void run() {
				while (true) {
					for (Monster m: SharedEntity.getInstance().getMonsterOfCurrentMap()) {
						if (m.getAiDelay() <= 0 && m.getWalkTick() <= 0) {
							if (m.isAggressive()) {
								if (m.getX() < GameManager.getInstance().getPlayer().getX()) {
									m.setFutureFacing(Entity.RIGHT);
								}
								else {
									m.setFutureFacing(Entity.LEFT);
								}
								m.setWalkTick(30);
							}
							else {
								m.setAiDelay(Random.randInt(420));
								if (m.getX() <= 0) {
									m.setFutureFacing(Entity.RIGHT);
								}
								else if (m.getX() + m.getWidth() >= m.getMap().getWidth()) {
									m.setFutureFacing(Entity.LEFT);
								}
								else {
									m.setFutureFacing((int) Random.randInt(2)*2-1);
								}
								m.setWalkTick(30 + Random.randInt(240));
							}
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
		}, "Monster AI Thread");
		start();
	}
	
}
