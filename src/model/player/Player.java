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
	
	public void update() {
		//TODO
		if (KeyInput.containsKey(KeyCode.LEFT)) {
			GameManager.getInstance().getCurrentMap().pushAccX(GameManager.getInstance().getPlayer(), -0.5);
		}
		if (KeyInput.containsKey(KeyCode.RIGHT)) {
			GameManager.getInstance().getCurrentMap().pushAccX(GameManager.getInstance().getPlayer(), 0.5);
		};
		if (KeyInput.containsKey(KeyCode.SPACE)) {
			if (KeyInput.containsKey(KeyCode.DOWN) && GameManager.getInstance().shouldJumpDown()) jumpDown();
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
