package model.player;

import model.Entity;

public abstract class Player extends Entity {
	
	public Player() {
		super("Player");
	}
	
	public Player(String name) {
		super(name);
	}
	
	public Player(String name,int level,int hp,int mp,int attack,int defense) {
		super(name,level,hp,mp,attack,defense);
	}
	
	public void jumpDown() {
		y += 5;
	}
	
}
