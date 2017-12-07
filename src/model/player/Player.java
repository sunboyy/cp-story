package model.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import buff.Buff;
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
import particle.LevelUp;
import sharedObject.SharedEntity;
import skill.AttackSkill;
import skill.NoSkill;
import skill.Skill;

public abstract class Player extends DamageableEntity {
	
	private List<Image> imgWalking,imgCrying,imgWalkAndCry,imgAttack;
	private boolean isWalking = false;
	private boolean isJumping = false;
	private boolean isCrying = false;
	protected List<Skill> skills = new ArrayList<>();
	private Rectangle attackArea;
	private int walkTick = 30;
	private int maxWalkTick = 60;
	private int attackTick = 0;
	private int damageTick = 60;
	private int maxDamageTick = 60;
	private List<Item> inventory = new ArrayList<>();
	private List<Buff> buffs = new ArrayList<>();
	private int maxInventorySlots = 10;
	
	public Player(String name, Image imgL, Image imgR, List<Image> imgWalking, List<Image> imgCrying, List<Image> imgWalkAndCry, List<Image> imgAttack, Map map) {
		this(name, imgL, imgR, imgWalking,imgCrying,imgWalkAndCry,imgAttack, map, 0, 0);
	}
	
	public Player(String name, Image imgL, Image imgR, List<Image> imgWalking, List<Image> imgCrying, List<Image> imgWalkAndCry, List<Image> imgAttack, Map map, double x, double y) {
		super(name, imgL, imgR, map, x, y, Constants.LEVEL_HP[1], Constants.LEVEL_MP[1], getAttackLow(1), getAttackHigh(1));
		attackArea = new Rectangle(x, y, width, height);
		this.maxVelocityX = 4;
		this.imgWalking = imgWalking;
		this.imgCrying = imgCrying;
		this.imgWalkAndCry = imgWalkAndCry;
		this.imgAttack = imgAttack;
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
	
	public boolean attack(AttackSkill skill, List<DamageableEntity> list) {
		if (list == null) return false;
		if (!canAttack()) return false;
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
		attackTick = skill.getCooldownTick();
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
		if(!canAttack()) {	// is attacking now !
			setFacing(this.facing,imgAttack.get(this.facing+1));
		}
	}
	
	public void update() {
		isCrying = getHp() < 0.2*getMaxHp();
		if (KeyInput.pressingKey(KeyCode.LEFT)) {
			setWalking(true);
			setMove(LEFT);
			if (GameManager.getInstance().getCurrentMap().isOnFloor(this))
				GameManager.getInstance().getCurrentMap().pushAccX(this, -0.5);
			else
				GameManager.getInstance().getCurrentMap().pushAccX(this, -0.2);
		}
		else if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			setWalking(true);
			setMove(RIGHT);
			if (GameManager.getInstance().getCurrentMap().isOnFloor(this))
				GameManager.getInstance().getCurrentMap().pushAccX(this, 0.5);
			else
				GameManager.getInstance().getCurrentMap().pushAccX(this, 0.2);
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
		if (KeyInput.pressingKey(KeyCode.E) && skills.size() > 3) {
			skills.get(3).activate();
		}
		// Development Cheat
		if (KeyInput.pressingKey(KeyCode.A) && KeyInput.pressingKey(KeyCode.S) && KeyInput.pressingKey(KeyCode.D) && KeyInput.pressingKey(KeyCode.F)) {
			addExperience(10);
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
				skills.get(0).activate();
			}
			else if (key == KeyCode.Q && skills.size() > 1) {
				skills.get(1).activate();
			}
			else if (key == KeyCode.W && skills.size() > 2) {
				skills.get(2).activate();
			}
			else if (key == KeyCode.R && skills.size() > 4) {
				skills.get(4).activate();
			}
		}
		if (KeyInput.pressingKey(KeyCode.P)) {
			SharedEntity.getInstance().print();
		}
		walkTick = isWalking() ? (walkTick + 1)%maxWalkTick : 0; 
		if (attackTick > 0) attackTick--;
		if (damageTick < maxDamageTick) damageTick++;
		else if (GameManager.getInstance().getCurrentMap().collideDamageableEntity(this, 1).size() != 0) {
			damage(GameManager.getInstance().getCurrentMap().collideDamageableEntity(this, 1).get(0).getAttackDamage());
			damageTick = 0;
		}
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext()) {
			Buff buff = it.next();
			buff.update();
			if (buff.isExpired()) {
				it.remove();
			}
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
			setAttackDamageHigh(getAttackHigh(level));
			setAttackDamageLow(getAttackLow(level));
			Sounds.levelUpSound.play();
			GameManager.getInstance().getCurrentMap().getParticles().add(new LevelUp());
		}
	}
	
	public void addBuff(Buff buff) {
		for (Buff i: buffs) {
			if (i.getClass().equals(buff.getClass())) {
				i.refresh();
				return;
			}
		}
		buffs.add(buff);
	}
	
	public boolean canUseMp(int mp) {
		return this.getMp() >= mp;
	}
	
	public boolean canAttack() {
		return attackTick <= 0;
	}
	
	public static int getAttackLow(int level) {
		return (int) Math.floor(9.6 * Math.pow(2,(level/9.)));
	}
	
	public static int getAttackHigh(int level) {
		return (int) Math.floor(14.4 * Math.pow(2, (level/9.)));
	}
	
	@Override
	public int getAttackDamage() {
		double multiplier = 1;
		for (Buff i: buffs) {
			multiplier += i.getAttackMultiplier();
		}
		if (multiplier <= 0) return 0;
		return (int) (super.getAttackDamage() * multiplier);
	}
	
	// Getter
	public boolean isJumping() {
		return isJumping;
	}
	
	public boolean isWalking() {
		return isWalking;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}
	
	public Rectangle getAttackArea(AttackSkill skill) {
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
		return getAttackArea((AttackSkill) skills.get(0));
	}
	
	public List<Item> getInventory() {
		return inventory;
	}
	
	public List<Buff> getBuffs() {
		return buffs;
	}
	
	// Setter
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

}
