package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.item.Item;
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
	protected Map<Item, Double> drops = new HashMap<>();
	
	public DamageableEntity(String name, Image img, model.map.Map map, int hp, int mp, int attackDamageLow, int attackDamageHigh) {
		this(name, img, map, 0, 0, hp, mp, attackDamageLow, attackDamageHigh);
	}
	
	public DamageableEntity(String name, Image img, model.map.Map map, double x, double y, int hp, int mp, int attackDamageLow, int attackDamageHigh) {
		super(name, img, map, x, y);
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
	
	public void heal(int hp) {
		this.hp += hp;
		if (this.hp > maxHp) this.hp = maxHp;
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
	
	public List<ItemEntity> spawnLoot() {
		List<ItemEntity> loot = new ArrayList<>();
		for (Map.Entry<Item, Double> i: drops.entrySet()) {
			double rand = Math.random();
			if (rand <= i.getValue()) {
				loot.add(new ItemEntity(i.getKey(), getMap(), x, y));
			}
		}
		return loot;
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
	
	public Map<Item, Double> getDrops() {
		return drops;
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
