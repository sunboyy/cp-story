package model.player;

import javafx.scene.image.Image;
import model.Entity;
import model.Movable;

public abstract class Player extends Entity implements Movable {
	
	private double velocityX;
	private double velocityY;
	private boolean isJumping = false;
	
	public Player() {
		super("Player");
	}
	
	public Player(String name) {
		super(name);
	}
	
	public Player(Image img) {
		super(img);
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
		if (!isJumping) {
			velocityY = -10;
			isJumping = true;
		}
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
	
	public boolean isJumping() {
		return isJumping;
	}
	
	// Setter
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
