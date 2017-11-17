package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import particle.Damage;
import particle.HPBar;

public abstract class DamageableEntity extends Entity {
	
	protected int level = 1;
	protected int experience = 0;
	private int maxHp;
	private int hp;
	private int maxMp;
	private int mp;
	private int attackDamageLow = 4;
	private int attackDamageHigh = 8;
	private int defense = 5;
	private HPBar hpBar;
	
	public DamageableEntity(String name, Image img, int hp, int mp, int attackDamageLow, int attackDamageHigh) {
		this(name, img, 0, 0, hp, mp, attackDamageLow, attackDamageHigh);
	}
	
	public DamageableEntity(String name, Image img, double x, double y, int hp, int mp, int attackDamageLow, int attackDamageHigh) {
		super(name, img, x, y);
		this.maxHp = hp;
		this.hp = hp;
		this.maxMp = mp;
		this.mp = mp;
		this.attackDamageLow = attackDamageLow;
		this.attackDamageHigh = attackDamageHigh;
		hpBar = new HPBar(this);
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
		hpBar.resetVisible();
		GameManager.getInstance().getCurrentMap().getParticles().add(new Damage(hp, this.x+this.width/2, this.y));
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		hpBar.render(gc);
	}
	
	// Getter
	public int getLevel() {
		return level;
	}
	
	public int getExperience() {
		return experience;
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
		return (int) Math.floor(Math.random() * (attackDamageHigh-attackDamageLow)) + attackDamageLow;
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

	public void setAttackDamageLow(int attackDamageLow) {
		this.attackDamageLow = attackDamageLow;
	}

	public void setAttackDamageHigh(int attackDamageHigh) {
		this.attackDamageHigh = attackDamageHigh;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public String toString() {
		return String.format("%s [HP: %d/%d, MP: %d/%d]", super.toString(), hp, maxHp, mp, maxMp);
	}
	
}
