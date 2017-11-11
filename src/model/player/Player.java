package model.player;

import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Entity;
import model.GameManager;
import model.Movable;

public abstract class Player extends Entity implements Movable {
	
	private double velocityX;
	private double velocityY;
	private boolean isJumping = false;
	private double attackRange = 50;
	
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
		if (!isJumping && GameManager.getInstance().getCurrentMap().isOnFloor(this)) {
			velocityY = -10;
			isJumping = true;
		}
	}
	
	public void jumpDown() {
		if (!isJumping && GameManager.getInstance().getCurrentMap().isOnFloor(this)) {
			y += 5;
			isJumping = true;
		}
	}
	
	public void update() {
		//TODO
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			facing = LEFT;
			GameManager.getInstance().getCurrentMap().pushAccX(GameManager.getInstance().getPlayer(), -0.5);
		}
		if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			facing = RIGHT;
			GameManager.getInstance().getCurrentMap().pushAccX(GameManager.getInstance().getPlayer(), 0.5);
		};
		if (KeyInput.pressingKey(KeyCode.SPACE)) {
			if (KeyInput.pressingKey(KeyCode.DOWN) && GameManager.getInstance().shouldJumpDown()) jumpDown();
			else jump();
		}
		
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
	
	public double getAttackRange() {
		return attackRange;
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
