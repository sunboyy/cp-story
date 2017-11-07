package model.monster;

import model.Entity;

public class Monster extends Entity {
	
	public Monster() {
		super("Monster");
	}
	
	public Monster(String name,int level,int hp,int mp,int attack,int defense) {
		super(name,level,hp,mp,attack,defense);
	}
}
