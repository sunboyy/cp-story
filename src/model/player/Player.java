package model.player;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import input.KeyInput;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.DamageableEntity;
import model.GameManager;
import model.ItemEntity;
import model.Rectangle;
import model.item.Item;
import model.item.UsableItem;
import model.map.Map;

public abstract class Player extends DamageableEntity {
	
	private Image imageL;
	private Image imageR;
	private boolean isJumping = false;
	private double attackRange = 50;
	private Rectangle attackArea;
	private int attackTick = 30;
	private int maxAttackTick = 30;
	private int damageTick = 60;
	private int maxDamageTick = 60;
	private List<Item> inventory = new ArrayList<>();
	private int maxInventorySlots = 10;
	
	public Player(String name, Image imgL, Image imgR, Map map, int atkLow, int atkHigh) {
		this(name, imgL, imgR, map, 0, 0, atkLow, atkHigh);
	}
	
	public Player(String name, Image imgL, Image imgR, Map map, double x, double y, int atkLow, int atkHigh) {
		super(name, imgR, map, x, y, Constants.LEVEL_HP[1], Constants.LEVEL_MP[1], Constants.LEVEL_ATTACK_LOW[1], Constants.LEVEL_ATTACK_HIGH[1]);
		attackArea = new Rectangle(x, y, width, height);
		this.imageL = imgL;
		this.imageR = imgR;
		this.maxVelocityX = 4;
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
		if (e.isDead()) {
			addExperience(e.getExperience());
			GameManager.getInstance().getCurrentMap().getEntities().remove(e);
			GameManager.getInstance().getCurrentMap().getEntities().addAll(e.spawnLoot());
		}
		attackTick = 0;
	}
	
	public void update() {
		//TODO
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			facing = LEFT;
			setImage(imageL);
			GameManager.getInstance().getCurrentMap().pushAccX(GameManager.getInstance().getPlayer(), -0.5);
		}
		if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			facing = RIGHT;
			setImage(imageR);
			GameManager.getInstance().getCurrentMap().pushAccX(GameManager.getInstance().getPlayer(), 0.5);
		};
		if (KeyInput.pressingKey(KeyCode.SPACE)) {
			if (KeyInput.pressingKey(KeyCode.DOWN) && GameManager.getInstance().shouldJumpDown()) jumpDown();
			else jump();
		}
		if (KeyInput.pressingKey(KeyCode.A)) {
			DamageableEntity entity = GameManager.getInstance().getCurrentMap().collideDamageableEntity(getAttackArea());
			if (attackTick >= maxAttackTick && entity != null) {
				attack(entity);
			}
		}
		if (KeyInput.pressingKey(KeyCode.C)) {
			ItemEntity itemEntity = GameManager.getInstance().getCurrentMap().collideItemEntity(this);
			if (itemEntity != null) {
				if (collectItem(itemEntity.getItem()))
					GameManager.getInstance().getCurrentMap().getEntities().remove(itemEntity);
			}
		}
		if (KeyInput.pressingKey(KeyCode.UP)) {
			GameManager.getInstance().warp();
		}
		while (KeyInput.digitAvailable()) {
			int digit = KeyInput.pollDigitKey();
			int index = (digit + 9) % 10;
			if (GameManager.getInstance().getPlayer().getInventory().size() > index) {
				Item item = GameManager.getInstance().getPlayer().getInventory().get(index);
				if (item instanceof UsableItem) {
					((UsableItem) item).use();
					if (item.getCount() <= 0) {
						inventory.remove(item);
					}
				}
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
	
	public boolean collectItem(Item item) {
		for (Item i: inventory) {
			if (i.sameType(item) && i.add(1)) {
				return true;
			}
		}
		if (inventory.size() + 1 <= maxInventorySlots) {
			inventory.add(item);
			return true;
		}
		return false;
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
	
	public List<Item> getInventory() {
		return inventory;
	}
	
	// Setter
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public int getExperience() {
		return experience;
	}

}
