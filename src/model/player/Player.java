package model.player;

import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.Entity;
import model.GameManager;
import model.Rectangle;

public abstract class Player extends Entity {
	
	private boolean isJumping = false;
	private double attackRange = 50;
	private Rectangle attackArea;
	private int attackTick = 30;
	private int maxAttackTick = 30;
	private int damageTick = 60;
	private int maxDamageTick = 60;
	
	public Player() {
		super("Player");
	}
	
	public Player(String name) {
		super(name);
	}
	
	public Player(Image img) {
		super(img);
		attackArea = new Rectangle(x, y, width, height);
	}
	
	public Player(String name,int level,int hp,int mp,int attack,int defense) {
		super(name,level,hp,mp,attack,defense);
	}
	
	public void jump() {
		if (!isJumping && GameManager.getInstance().getCurrentMap().isOnFloor(this)) {
			velocityY = -10;
			isJumping = true;
		}
	}
	
	public void jumpDown() {
		if (!isJumping && GameManager.getInstance().getCurrentMap().isOnFloor(this)) {
			move(0, 2);
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
		if (KeyInput.pressingKey(KeyCode.CONTROL)) {
			if (attackTick >= maxAttackTick && GameManager.getInstance().getCurrentMap().collideEntity(getAttackArea()) != null) {
				attackTick = 0;
				System.out.println("Attacked");
			}
		}
		if (attackTick < maxAttackTick) attackTick++;
		if (damageTick < maxDamageTick) damageTick++;
		else if (GameManager.getInstance().getCurrentMap().collideEntity(this) != null) {
			damageTick = 0;
			System.out.println("Player HP decreased");
		}
	}
	
	// Getter
	public boolean isJumping() {
		return isJumping;
	}
	
	public double getAttackRange() {
		return attackRange;
	}
	
	public Rectangle getAttackArea() {
		if (facing == LEFT) {
			attackArea.setX(x-attackRange);
			attackArea.setY(y);
			attackArea.setWidth(width+attackRange);
			attackArea.setHeight(height);
		}
		else {
			attackArea.setX(x);
			attackArea.setY(y);
			attackArea.setWidth(width+attackRange);
			attackArea.setHeight(height);
		}
		return attackArea;
	}
	
	// Setter
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
