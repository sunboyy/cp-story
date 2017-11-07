package model;

public abstract class Entity {
	
	private int level = 1;
	private String name;
	private int hp = 100;
	private int mp = 100;
	private int attack = 5;
	private int defense = 5;
	
	public Entity() {
		this.name = "Entity";
	}
	
	public Entity(String name) {
		this.name = name;
	}
	
	public Entity(String name,int level,int hp,int mp,int attack,int defense) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.mp = mp;
		this.attack = attack;
		this.defense = defense;
	}

	// Getter
	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getMp() {
		return mp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	// Setter
	public void setLevel(int level) {
		this.level = level;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	
	
}
