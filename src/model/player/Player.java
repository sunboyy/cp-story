package model.player;

import model.Entity;
import model.Movable;

public abstract class Player extends Entity implements Movable {
	
	private double velocityX;
	private double velocityY;
	
	public Player() {
		super("Player");
	}
	
	public Player(String name) {
		super(name);
	}
	
	public Player(String name,int level,int hp,int mp,int attack,int defense) {
		super(name,level,hp,mp,attack,defense);
	}
	
	public void pushAccX(double accX) {
		this.velocityX += accX;
	}
	
	public void pushAccY(double accY) {
		this.velocityY += accY;
	}
	
	public void move() {
		x += velocityX;
		y += velocityY;
	}
	
	public void jump() {
		velocityY = -10;
	}
	
	public void jumpDown() {
		y += 5;
	}
	
	// Getter
	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}
	
	// Setter
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

}
