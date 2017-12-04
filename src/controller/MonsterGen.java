package controller;

import java.lang.reflect.InvocationTargetException;

import model.GameManager;
import sharedObject.SharedEntity;

public class MonsterGen extends Thread {

	public MonsterGen() {
		super(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.out.println("MonsterGen thread has been interrupted.");
						break;
					}
					if (SharedEntity.getInstance().getEntitiesOfCurrentMap().size() < 5 && !GameManager.getInstance().isWarping()) {
						try {
							GameManager.getInstance().getCurrentMap().spawnRandom();
						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		start();
	}
	
}
