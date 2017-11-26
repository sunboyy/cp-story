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
import model.IUsable;
import model.ItemEntity;
import model.Rectangle;
import model.item.Item;
import model.map.Map;
import sharedObject.SharedEntity;
import skill.ISkill;
import skill.NoSkill;

public abstract class Player extends DamageableEntity {
	
	private List<Image> imgWalking,imgCrying,imgWalkAndCry;
	private boolean isWalking = false;
	private boolean isJumping = false;
	private boolean isCrying = false;
	protected List<ISkill> skills = new ArrayList<>();
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
		this.skills.add(new NoSkill());
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
	
	public boolean attack(ISkill skill, List<DamageableEntity> list) {
		if (list == null) return false;
		if (attackTick < maxAttackTick) return false;
		for (DamageableEntity e: list) {
			e.damage((int) (getAttackDamage() * skill.getDamageMultiplier()));
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
		return true;
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
		while (KeyInput.pollAvailable()) {
			KeyCode key = KeyInput.pollKey();
			if (key.isDigitKey()) {
				int digit = Integer.parseInt(key.toString().substring(5));
				int index = (digit + 9) % 10;
				if (inventory.size() > index) {
					Item item = inventory.get(index);
					if (item instanceof IUsable) {
						((IUsable) item).use();
						if (item.getCount() <= 0) {
							inventory.remove(item);
						}
					}
				}
			}
			else if (key == KeyCode.A && skills.size() > 0) {
//				ISkill skill = skills.get(0);
//				List<DamageableEntity> entities = GameManager.getInstance().getCurrentMap().collideDamageableEntity(getAttackArea(skill), skill.getMaxEntity());
//				attack(skill, entities);
				skills.get(0).use();
			}
			else if (key == KeyCode.Q && skills.size() > 1) {
				skills.get(1).use();
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
	
	public List<ISkill> getSkills() {
		return skills;
	}
	
	public Rectangle getAttackArea(ISkill skill) {
		if (facing == LEFT) {
			attackArea.setX(x - skill.getAttackRange());
			attackArea.setY(y);
			attackArea.setWidth(width + skill.getAttackRange());
			attackArea.setHeight(height);
		}
		else {
			attackArea.setX(x);
			attackArea.setY(y);
			attackArea.setWidth(width + skill.getAttackRange());
			attackArea.setHeight(height);
		}
		return attackArea;
	}
	
	public Rectangle getAttackArea() {
		return getAttackArea(skills.get(0));
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
