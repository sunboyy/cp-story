package model.player;

import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.DamageableEntity;
import model.GameManager;
import model.Rectangle;

public abstract class Player extends DamageableEntity {
	
	private boolean isJumping = false;
	private double attackRange = 50;
	private Rectangle attackArea;
	private int attackTick = 30;
	private int maxAttackTick = 30;
	private int damageTick = 60;
	private int maxDamageTick = 60;
	
	public Player(Image img, int hp, int mp, int atk) {
		this(img, 0, 0, hp, mp, atk);
	}
	
	public Player(Image img, double x, double y, int hp, int mp, int atk) {
		super(img, x, y, hp, mp, atk);
		attackArea = new Rectangle(x, y, width, height);
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
	
	public void attack(DamageableEntity e) {
		if (e == null) return;
		e.damage(getAttackDamage());
		System.out.println(e);
		if (e.isDead()) {
			GameManager.getInstance().getCurrentMap().getEntities().remove(e);
		}
		attackTick = 0;
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
			if (attackTick >= maxAttackTick && GameManager.getInstance().getCurrentMap().collideDamageableEntity(getAttackArea()) != null) {
				attack(GameManager.getInstance().getCurrentMap().collideDamageableEntity(getAttackArea()));
			}
		}
		if (attackTick < maxAttackTick) attackTick++;
		if (damageTick < maxDamageTick) damageTick++;
		else if (GameManager.getInstance().getCurrentMap().collideDamageableEntity(this) != null) {
			damage(GameManager.getInstance().getCurrentMap().collideDamageableEntity(this).getAttackDamage());
			System.out.println(this);
			damageTick = 0;
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
