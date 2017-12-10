package model.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import buff.Buff;
import constants.Constants;
import constants.Sounds;
import controller.GameManager;
import exception.InventoryFullException;
import exception.NegativeWeightedRandomException;
import input.KeyInput;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import model.DamageableEntity;
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
	
	protected List<Image> imgWalking,imgCrying,imgWalkAndCry,imgAttack;
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
	private int expDropTick = 0;
	private Item[] inventory = new Item[10];
	private List<Buff> buffs = new ArrayList<>();
	private boolean hasEverDead = false;
	
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
				try {
					SharedEntity.getInstance().addAll(e.spawnLoot());
				} catch (NegativeWeightedRandomException e1) {
					System.out.println("Error: Negative weight of random loot");
				}
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
			isWalking = true;
			setMove(LEFT);
			if (GameManager.getInstance().getCurrentMap().isOnFloor(this))
				GameManager.getInstance().getCurrentMap().pushAccX(this, -0.5);
			else
				GameManager.getInstance().getCurrentMap().pushAccX(this, -0.2);
		}
		else if (KeyInput.pressingKey(KeyCode.RIGHT)) {
			isWalking = true;
			setMove(RIGHT);
			if (GameManager.getInstance().getCurrentMap().isOnFloor(this))
				GameManager.getInstance().getCurrentMap().pushAccX(this, 0.5);
			else
				GameManager.getInstance().getCurrentMap().pushAccX(this, 0.2);
		} else {
			isWalking = false;
			setMove(this.facing);
		}
		if (KeyInput.pressingKey(KeyCode.SPACE)) {
			if (KeyInput.pressingKey(KeyCode.DOWN) && GameManager.getInstance().shouldJumpDown()) jumpDown();
			else jump();
		}
		if (KeyInput.pressingKey(KeyCode.C)) {
			ItemEntity itemEntity = GameManager.getInstance().getCurrentMap().collideItemEntity(this);
			if (itemEntity != null) {
				try {
					collectItem(itemEntity.getItem());
					SharedEntity.getInstance().remove(itemEntity);
				} catch (InventoryFullException e) {
					GameManager.getInstance().setMessage("Cannot collect item: Inventory full");
				}
			}
		}
		if (KeyInput.pressingKey(KeyCode.UP)) {
			GameManager.getInstance().warp();
		}
		if (KeyInput.pressingKey(KeyCode.E) && skills.size() > 3) {
			skills.get(3).activate();
		}
		// TODO Remove
		if (KeyInput.pressingKey(KeyCode.A) && KeyInput.pressingKey(KeyCode.S) && KeyInput.pressingKey(KeyCode.D) && KeyInput.pressingKey(KeyCode.F)) {
			addExperience(30);
		}
		while (KeyInput.pollAvailable()) {
			KeyCode key = KeyInput.pollKey();
			if (key.isDigitKey()) {
				int digit = Integer.parseInt(key.toString().substring(5));
				int index = (digit + 9) % 10;
				if (inventory[index] != null) {
					Item item = inventory[index];
					item.activate();
					if (item.getCount() <= 0) {
						inventory[index] = null;
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
		walkTick = isWalking() ? (walkTick + 1)%maxWalkTick : 0; 
		if (isDead()) {
			if (expDropTick <= 0) {
				experience--;
				if (experience < 0) experience = 0;
				expDropTick = 15;
			}
			else {
				expDropTick--;
			}
		}
		if (attackTick > 0) attackTick--;
		if (damageTick < maxDamageTick) damageTick++;
		else if (GameManager.getInstance().getCurrentMap().collideDamageableEntity(this, 1).size() != 0) {
			damage(GameManager.getInstance().getCurrentMap().collideDamageableEntity(this, 1).get(0).getAttackDamage());
			damageTick = 0;
		}
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext()) {
			Buff buff = it.next();
			if (buff.isExpired()) {
				it.remove();
			}
		}
	}
	
	public void collectItem(Item item) throws InventoryFullException {
		int spaceIndex = -1;
		for (int i=0; i<inventory.length; i++) {
			if (inventory[i] == null && spaceIndex == -1) {
				spaceIndex = i;
			}
			else if (inventory[i] != null && inventory[i].sameType(item) && inventory[i].add(1)) {
				return;
			}
		}
		if (spaceIndex != -1) {
			inventory[spaceIndex] = item;
			return;
		}
		throw new InventoryFullException();
	}
	
	public void addExperience(int experience) {
		if (level >= Constants.MAX_LEVEL) {
			this.experience = 0;
			return;
		}
		double multiplier = 1;
		for (Buff i: buffs) {
			multiplier += i.getExperienceMultiplier();
		}
		this.experience += multiplier*experience;
		while (Constants.LEVEL_EXPERIENCE[level] <= this.experience) {
			this.experience -= Constants.LEVEL_EXPERIENCE[level];
			++level;
			setMaxHp(Constants.LEVEL_HP[level]);
			refillHp();
			setMaxMp(Constants.LEVEL_MP[level]);
			refillMp();
			setAttackDamageHigh(getAttackHigh(level));
			setAttackDamageLow(getAttackLow(level));
			Sounds.levelUpSound.play();
			GameManager.getInstance().getCurrentMap().getParticles().add(new LevelUp());
			if (level >= Constants.MAX_LEVEL) {
				GameManager.getInstance().stopGame();
				break;
			}
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
		return (int) Math.floor(13. * Math.pow(2,(level/7.)));
	}
	
	public static int getAttackHigh(int level) {
		return (int) Math.floor(21. * Math.pow(2, (level/7.)));
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
	
	@Override
	public void damage(int hp) {
		super.damage(hp);
		if (isDead() && !hasEverDead) {
			GameManager.getInstance().setPausing(true);
			hasEverDead = true;
			Platform.runLater(() -> {
				KeyInput.clear();
				Alert alert = new Alert(AlertType.WARNING, "Uh oh! You are dead. You can still play with experience decreasing continuously. The experience will stop decreasing when HP is more than zero.\nProgrammers have no life...", ButtonType.OK);
				alert.setTitle("You are dead.");
				alert.setHeaderText("You are dead.");
				alert.showAndWait();
				GameManager.getInstance().setPausing(false);
			});
		}
	}
	
	// Getter
	public boolean isJumping() {
		return isJumping;
	}
	
	public boolean isWalking() {
		return isWalking;
	}
	
	public boolean isCrying() {
		return isCrying;
	}
	
	public int getWalkTick() {
		return walkTick;
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
	
	public Item[] getInventory() {
		return inventory;
	}
	
	public List<Buff> getBuffs() {
		return buffs;
	}
	
	// Setter
	public void setName(String name) {
		if (name != null && !name.trim().equals("")) {
			this.name = name;
		}
	}
	
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

}
