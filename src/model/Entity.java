package model;

import javafx.scene.image.Image;

public abstract class Entity extends Rectangle {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	protected double velocityX;
	protected double velocityY;
	private Image img;
	protected int facing = RIGHT;
	
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
	
	public Entity(Image img) {
		super(0, 0, img.getWidth(), img.getHeight());
		this.img = img;
	}
	
	public Entity(String name,int level,int hp,int mp,int attack,int defense) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.mp = mp;
		this.attack = attack;
		this.defense = defense;
	}
	
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void move() {
		this.x += velocityX;
		this.y += velocityY;
	}

	public void pushAccX(double accX) {
		this.velocityX += accX;
	}
	
	public void pushAccY(double accY) {
		this.velocityY += accY;
	}
	
	// Getter
	public int getFacing() {
		return facing;
	}
	
	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

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

	public Image getImg() {
		return img;
	}

	// Setter
	public void setLevel(int level) {
		this.level = level;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
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
