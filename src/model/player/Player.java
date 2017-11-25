package model.player;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import constants.Sounds;
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
import sharedObject.SharedEntity;

public abstract class Player extends DamageableEntity {
	
	private List<Image> imgWalking,imgCrying,imgWalkAndCry;
	private boolean isWalking = false;
	private boolean isJumping = false;
	private boolean isCrying = false;
	private double attackRange = 50;
	private Rectangle attackArea;
	private int walkTick = 30;
	private int maxWalkTick = 60;
	private int attackTick = 30;
	private int maxAttackTick = 30;
	private int damageTick = 60;
	private int maxDamageTick = 60;
	private List<Item> inventory = new ArrayList<>();
	private int maxInventorySlots = 10;
	
	public Player(String name, Image imgL, Image imgR, List<Image> imgWalking, List<Image> imgCrying, List<Image> imgWalkAndCry, Map map, int atkLow, int atkHigh) {
		this(name, imgL, imgR, imgWalking,imgCrying,imgWalkAndCry, map, 0, 0, atkLow, atkHigh);
	}
	
	public Player(String name, Image imgL, Image imgR, List<Image> imgWalking, List<Image> imgCrying, List<Image> imgWalkAndCry, Map map, double x, double y, int atkLow, int atkHigh) {
		super(name, imgL, imgR, map, x, y, Constants.LEVEL_HP[1], Constants.LEVEL_MP[1], Constants.LEVEL_ATTACK_LOW[1], Constants.LEVEL_ATTACK_HIGH[1]);
		attackArea = new Rectangle(x, y, width, height);
		this.maxVelocityX = 4;
		this.imgWalking = imgWalking;
		this.imgCrying = imgCrying;
		this.imgWalkAndCry = imgWalkAndCry;
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
	
	public void attack(List<DamageableEntity> list) {
		if (list == null) return;
		for (DamageableEntity e: list) {
			e.damage(getAttackDamage());
			if (e.isDead()) {
				Sounds.deadSound.play();
				addExperience(e.getExperience());
				SharedEntity.getInstance().remove(e);
				SharedEntity.getInstance().addAll(e.spawnLoot());
			}
			else {
				Sounds.punchSound.play();
			}
		}
		attackTick = 0;
	}
	
	public void setMove(int direction) {
		if(isCrying) {
			if(walkTick%10<5 && isWalking) {
				setFacing(direction, imgWalkAndCry.get((direction+1)/2));
			} else {
				setFacing(direction, imgCrying.get((direction+1)/2));
			}
		} else if (isWalking) {
			if(walkTick%10<5) {
				setFacing(direction,imgWalking.get((direction+1)/2));
			} else {
				setFacing(direction);
			}
		} else {
			setFacing(direction);
		}
	}
	
	public void update() {
		//TODO
		isCrying = getHp() < 0.2*getMaxHp();
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			setWalking(true);
			setMove(LEFT);
			GameManager.getInstance().getCurrentMap().pushAccX(this, -0.5);
		}
		else if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			setWalking(true);
			setMove(RIGHT);
			GameManager.getInstance().getCurrentMap().pushAccX(this, 0.5);
		} else {
			setWalking(false);
			setMove(this.facing);
		}
		if (KeyInput.pressingKey(KeyCode.SPACE)) {
			if (KeyInput.pressingKey(KeyCode.DOWN) && GameManager.getInstance().shouldJumpDown()) jumpDown();
			else jump();
		}
		if (KeyInput.pressingKey(KeyCode.A)) {
			List<DamageableEntity> entities = GameManager.getInstance().getCurrentMap().collideDamageableEntity(getAttackArea(), 1);
			if (attackTick >= maxAttackTick) {
				attack(entities);
			}
		}
		if (KeyInput.pressingKey(KeyCode.C)) {
			ItemEntity itemEntity = GameManager.getInstance().getCurrentMap().collideItemEntity(this);
			if (itemEntity != null) {
				if (collectItem(itemEntity.getItem()))
					SharedEntity.getInstance().remove(itemEntity);
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
		if (KeyInput.pressingKey(KeyCode.P)) {
			SharedEntity.getInstance().print();
		}
		walkTick = isWalking() ? (walkTick + 1)%maxWalkTick : 0; 
		if (attackTick < maxAttackTick) attackTick++;
		if (damageTick < maxDamageTick) damageTick++;
		else if (GameManager.getInstance().getCurrentMap().collideDamageableEntity(this, 1).size() != 0) {
			damage(GameManager.getInstance().getCurrentMap().collideDamageableEntity(this, 1).get(0).getAttackDamage());
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
	
	public boolean isWalking() {
		return isWalking;
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
	
	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

}
