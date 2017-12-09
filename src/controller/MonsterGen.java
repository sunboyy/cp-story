package controller;

import sharedObject.SharedEntity;

public class MonsterGen extends Thread {
	
	private static int delay = 1500;

	public MonsterGen() {
		super(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						System.out.println("MonsterGen thread has been interrupted.");
						break;
					}
					if (GameManager.getInstance().isGameRunning() && SharedEntity.getInstance().getMonsterOfCurrentMap().size() < 25 && !GameManager.getInstance().isWarping()) {
						GameManager.getInstance().getCurrentMap().spawnRandom();
					}
				}
			}
		}, "Monster Gen Thread");
		start();
	}
	
}
