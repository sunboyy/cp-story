package model;

import java.util.ArrayList;
import java.util.List;

import controller.GameManager;
import exception.MpNotEnoughException;
import exception.NegativeWeightedRandomException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import model.item.Item;
import model.monster.Monster;
import particle.Damage;
import particle.DisplayName;
import sharedObject.SharedEntity;
import utility.Random;

public abstract class DamageableEntity extends Entity {
	
	protected int level = 1;
	protected int experience = 0;
	private int maxHp;
	private int hp;
	private int maxMp;
	private int mp;
	private int attackDamageLow = 4;
	private int attackDamageHigh = 8;
	protected List<Pair<Item, Double>> drops = new ArrayList<>();
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
		GameManager.getInstance().getCurrentMap().getParticles().add(new Damage(hp, this.x+this.width/2, this.y, (this instanceof Monster)));
		displayName.resetVisible();
	}
	
	public void forceKill() {
		damage(99999);
		SharedEntity.getInstance().remove(this);
		try {
			SharedEntity.getInstance().addAll(spawnLoot());
		} catch (NegativeWeightedRandomException e) {
			System.out.println("Error: Negative weight of random loot");
		}
	}
	
	public void healHp(int hp) {
		this.hp += hp;
		if (this.hp > maxHp) this.hp = maxHp;
	}
	
	public void healMp(int mp) {
		this.mp += mp;
		if (this.mp > maxMp) this.mp = maxMp;
	}
	
	public void refillHp() {
		this.hp = this.maxHp;
	}
	
	public void refillMp() {
		this.mp = this.maxMp;
	}
	
	public boolean isDead() {
		return hp <= 0;
	}
	
	public List<ItemEntity> spawnLoot() throws NegativeWeightedRandomException {
		List<Item> loot = Random.multipleRandomInList(drops);
		List<ItemEntity> lootEntity = new ArrayList<>();
		for (Item i: loot) {
			lootEntity.add(new ItemEntity(i, getMap(), x+Math.random()*(width-i.getImage().getWidth()), y));
		}
		return lootEntity;
	}
	
	public void useMp(int usedMp) throws MpNotEnoughException {
		if (mp < usedMp) throw new MpNotEnoughException();
		mp -= usedMp;
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
		return attackDamageLow + Random.randInt(attackDamageHigh-attackDamageLow);
	}
	
	public List<Pair<Item, Double>> getDrops() {
		return drops;
	}

	// Setter
	protected void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	
	protected void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	protected void setAttackDamageLow(int attackDamageLow) {
		this.attackDamageLow = attackDamageLow;
	}

	protected void setAttackDamageHigh(int attackDamageHigh) {
		this.attackDamageHigh = attackDamageHigh;
	}
	
	public String toString() {
		return String.format("%s [HP: %d/%d, MP: %d/%d]", super.toString(), hp, maxHp, mp, maxMp);
	}
	
}
