package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.item.Item;
import particle.Damage;
import particle.DisplayName;
import sharedObject.SharedEntity;

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
	protected Map<Item, Double> drops = new HashMap<>();
	private DisplayName displayName;
	
	public DamageableEntity(String name, Image imgL, Image imgR, model.map.Map map, int hp, int mp, int attackDamageLow, int attackDamageHigh) {
		this(name, imgL, imgR, map, 0, 0, hp, mp, attackDamageLow, attackDamageHigh);
	}
	
	public DamageableEntity(String name, Image imgL, Image imgR, model.map.Map map, double x, double y, int hp, int mp, int attackDamageLow, int attackDamageHigh) {
		super(name, imgL, imgR, map, x, y);
		this.maxHp = hp;
		this.hp = hp;
		this.maxMp = mp;
		this.mp = mp;
		this.attackDamageLow = attackDamageLow;
		this.attackDamageHigh = attackDamageHigh;
		this.displayName = new DisplayName(this);
	}
	
	public void damage(int hp) {
		this.hp -= hp;
		GameManager.getInstance().getCurrentMap().getParticles().add(new Damage(hp, this.x+this.width/2, this.y));
		displayName.resetVisible();
	}
	
	public void forceKill() {
		damage(999999);
		SharedEntity.getInstance().remove(this);
		SharedEntity.getInstance().addAll(spawnLoot());
	}
	
	public void healHp(int hp) {
		this.hp += hp;
		if (this.hp > maxHp) this.hp = maxHp;
	}
	
	public void healMp(int mp) {
		this.mp += mp;
		if (this.mp > maxMp) this.mp = maxMp;
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
	
	public boolean useMp(int usedMp) {
		if (mp >= usedMp) {
			mp -= usedMp;
			return true;
		}
		return false;
	}
	
	public void render(GraphicsContext gc) {
		super.render(gc);
		displayName.render(gc);
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
