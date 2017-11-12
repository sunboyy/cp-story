package model;

import javafx.scene.image.Image;

public abstract class DamageableEntity extends Entity {
	
	protected int level = 1;
	private int maxHp;
	private int hp;
	private int maxMp;
	private int mp;
	private int attackDamage = 5;
	private int defense = 5;
	
	public DamageableEntity(Image img, int hp, int mp, int attackDamage) {
		this(img, 0, 0, hp, mp, attackDamage);
	}
	
	public DamageableEntity(Image img, double x, double y, int hp, int mp, int attackDamage) {
		super(img, x, y);
		this.maxHp = hp;
		this.hp = hp;
		this.maxMp = mp;
		this.mp = mp;
		this.attackDamage = attackDamage;
	}
	
//	public DamageableEntity(String name,int level,int hp,int mp,int attack,int defense) {
//		
//		this.level = level;
//		this.hp = hp;
//		this.mp = mp;
//		this.attack = attack;
//		this.defense = defense;
//	}
	
	public void damage(int hp) {
		this.hp -= hp;
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
	
	// Getter
	public int getLevel() {
		return level;
	}
	
	public int getMaxHp() {
		return maxHp;
	}

	public int getHp() {
		return hp;
	}
	
	public int getMaxMp() {
		return maxMp;
	}

	public int getMp() {
		return mp;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public int getDefense() {
		return defense;
	}

	// Setter
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public String toString() {
		return String.format("%s [HP: %d/%d, MP: %d/%d]", super.toString(), hp, maxHp, mp, maxMp);
	}
	
}
