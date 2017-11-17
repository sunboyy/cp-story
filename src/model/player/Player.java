package model.player;

import constants.Constants;
import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.DamageableEntity;
import model.GameManager;
import model.Rectangle;
import model.map.Portal;

public abstract class Player extends DamageableEntity {
	
	private boolean isJumping = false;
	private double attackRange = 50;
	private Rectangle attackArea;
	private int attackTick = 30;
	private int maxAttackTick = 30;
	private int damageTick = 60;
	private int maxDamageTick = 60;
	private int warpTick = 60;
	private int maxWarpTick = 60;
	
	public Player(String name, Image img, int atkLow, int atkHigh) {
		this(name, img, 0, 0, atkLow, atkHigh);
	}
	
	public Player(String name, Image img, double x, double y, int atkLow, int atkHigh) {
		super(name, img, x, y, Constants.LEVEL_HP[1], Constants.LEVEL_MP[1], Constants.LEVEL_ATTACK_LOW[1], Constants.LEVEL_ATTACK_HIGH[1]);
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
			addExperience(e.getExperience());
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
			DamageableEntity entity = GameManager.getInstance().getCurrentMap().collideDamageableEntity(getAttackArea());
			if (attackTick >= maxAttackTick && entity != null) {
				attack(entity);
			}
		}
		if (KeyInput.pressingKey(KeyCode.UP)) {
			warp();
		}
		if (warpTick < maxWarpTick) warpTick++;
		if (attackTick < maxAttackTick) attackTick++;
		if (damageTick < maxDamageTick) damageTick++;
		else if (GameManager.getInstance().getCurrentMap().collideDamageableEntity(this) != null) {
			damage(GameManager.getInstance().getCurrentMap().collideDamageableEntity(this).getAttackDamage());
			System.out.println(this);
			damageTick = 0;
		}
	}
	
	public void warp() {
		Portal portal = GameManager.getInstance().getCurrentMap().collidePortal(this);
		if (warpTick >= maxWarpTick && portal != null) {
			GameManager.getInstance().setCurrentMap(portal.getDestination());
			x = portal.getXDest()-width/2;
			y = portal.getYDest()-height;
			warpTick = 0;
		}
	}
	
	public void addExperience(int experience) {
		if (level >= Constants.MAX_LEVEL) {
			this.experience = 0;
			return;
		}
		this.experience += experience;
		while (Constants.LEVEL_EXPERIENCE[level] <= this.experience) {
			this.experience -= Constants.LEVEL_EXPERIENCE[level];
			++level;
			setMaxHp(Constants.LEVEL_HP[level]);
			setHp(getMaxHp());
			setMaxMp(Constants.LEVEL_MP[level]);
			setMp(getMaxMp());
			setAttackDamageHigh(Constants.LEVEL_ATTACK_HIGH[level]);
			setAttackDamageLow(Constants.LEVEL_ATTACK_LOW[level]);
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

	public int getExperience() {
		return experience;
	}

}
