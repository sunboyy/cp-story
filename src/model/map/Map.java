package model.map;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import constants.Constants;
import controller.GameManager;
import exception.ListEmptyException;
import exception.NegativeWeightedRandomException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;
import model.DamageableEntity;
import model.Entity;
import model.ItemEntity;
import model.Rectangle;
import model.monster.Monster;
import model.player.Player;
import particle.IParticle;
import sharedObject.SharedEntity;
import utility.AudioPlayer;
import utility.Random;

public abstract class Map extends Rectangle {

	private Image img;
	private double movementSpeed = 3.9;

	private double gravity = 0.55;
	private double groundFriction = 0.4;
	private double airFriction = 0.05;
	private double maxVelocityY = 6;

	private List<Class<? extends Monster>> monsterTypes = new ArrayList<>();
	private List<Portal> portals = new ArrayList<>();
	private List<IParticle> particles = new ArrayList<>();
	private MapStructure structure;
	private MediaPlayer bgm;

	@SafeVarargs
	public Map(Image img, AudioClip bgm, Class<? extends Monster>... monsterTypes) {
		super(0, 0, img.getWidth(), img.getHeight());
		this.img = img;
		this.bgm = new MediaPlayer(new Media(bgm.getSource()));
		this.bgm.setCycleCount(MediaPlayer.INDEFINITE);
		structure = new MapStructure();
		for (Class<? extends Monster> i : monsterTypes)
			this.monsterTypes.add(i);
	}

	public void motion(Entity e) {
		moveEntity(e);
		pullGravity(e);
		decelerate(e);
		moveMap();
		if (e.getVelocityY() >= 0 && isOnFloor(e)) {
			e.setVelocityY(0);
			if (e instanceof Player) {
				((Player) e).setJumping(false);
			}
		}
	}

	public void motionAll() {
		for (Entity i : SharedEntity.getInstance().getEntitiesOfMap(this)) {
			motion(i);
		}
	}

	private void moveMap() {
		// Move map by object inside
		Player player = GameManager.getInstance().getPlayer();
		if (player.getX() > Constants.WINDOW_WIDTH + x - player.getWidth() - 200)
			x += movementSpeed;
		else if (player.getX() < x + 200)
			x -= movementSpeed;
		if (player.getY() > Constants.WINDOW_HEIGHT + y - player.getHeight() - 150)
			y += movementSpeed;
		else if (player.getY() < y + 150)
			y -= movementSpeed;

		// Map boundary
		if (x < 0)
			x = 0;
		else if (x > width - Constants.WINDOW_WIDTH)
			x = width - Constants.WINDOW_WIDTH;
		if (y < 0)
			y = 0;
		else if (y > height - Constants.WINDOW_HEIGHT)
			y = height - Constants.WINDOW_HEIGHT;
	}

	private void moveEntity(Entity e) {
		e.move();
		if (e.getX() < 0)
			e.setX(0);
		else if (e.getX() + e.getWidth() > width)
			e.setX(width - e.getWidth());
		if (e.getY() < 0)
			e.setY(9);
		else if (e.getY() + e.getHeight() > height)
			e.setY(height - e.getHeight());
	}

	public void pushAccX(Entity e, double accX) {
		double friction = getFriction(e);
		if (accX > 0)
			accX += friction;
		else if (accX < 0)
			accX -= friction;
		e.pushAccX(accX);
	}

	private void pullGravity(Entity e) {
		if (isOnFloor(e))
			return;
		if (e.getVelocityY() + gravity > maxVelocityY)
			e.setVelocityY(maxVelocityY);
		else
			e.pushAccY(gravity);
	}

	private void decelerate(Entity e) {
		double friction = isOnFloor(e) ? groundFriction : airFriction;
		if (e.getVelocityX() > friction)
			e.pushAccX(-friction);
		else if (e.getVelocityX() < -friction)
			e.pushAccX(friction);
		else
			e.setVelocityX(0);
	}

	public boolean isOnFloor(Entity e) {
		if (e.getY() + e.getHeight() >= height)
			return true;
		if (structure.collideWith(e) != null)
			return true;
		return false;
	}

	public List<DamageableEntity> collideDamageableEntity(Rectangle r, int limit) {
		List<DamageableEntity> list = new ArrayList<>();
		for (Entity i : SharedEntity.getInstance().getEntitiesOfMap(this)) {
			if (i instanceof DamageableEntity && r.collideWith(i)) {
				list.add((DamageableEntity) i);
			}
			if (list.size() >= limit) {
				break;
			}
		}
		return list;
	}
	
	public ItemEntity collideItemEntity(Rectangle r) {
		for (Entity i: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			if (i instanceof ItemEntity && r.collideWith(i)) {
				return (ItemEntity) i;
			}
		}
		return null;
	}

	public Portal collidePortal(Rectangle r) {
		for (Portal i : portals) {
			if (i.collideWith(r))
				return i;
		}
		return null;
	}
	
	public void warpIn() {
		AudioPlayer.fadeIn(bgm, 500);
	}
	
	public void warpOut() {
		AudioPlayer.fadeOut(bgm, 500);
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(img, -x, -y);
		GameManager.getInstance().getPlayer().render(gc);
		for (Entity i : SharedEntity.getInstance().getEntitiesOfMap(this))
			i.render(gc);
		for (Portal i : portals)
			i.render(gc);
		for (IParticle i : particles)
			i.render(gc);
	}

	public void update() {
		Iterator<IParticle> it = particles.iterator();
		while (it.hasNext()) {
			if (it.next().isExpired()) {
				it.remove();
			}
		}
		for (Entity i: SharedEntity.getInstance().getEntitiesOfMap(this)) {
			i.update();
			if (i instanceof ItemEntity && ((ItemEntity) i).isExpired()) {
				SharedEntity.getInstance().remove(i);
			}
		}
	}

	public void spawnRandom() {
		int randMonster = Random.randInt(monsterTypes.size());
		try {
			Monster m = monsterTypes.get(randMonster).getConstructor(Map.class, double.class, double.class).newInstance(this, 0, 0);
			List<Pair<StructureItem, Double>> list = new ArrayList<>();
			for (StructureItem item: structure) {
				if (item.isSpawnable() && item.getWidth() >= m.getWidth())
					list.add(new Pair<StructureItem, Double>(item, item.getWidth()-m.getWidth()));
			}
			Pair<StructureItem, Double> randStructure = Random.weightedRandomInList(list);
			double randX = randStructure.getKey().getX()+randStructure.getValue();
			double randY = randStructure.getKey().getY()-m.getHeight();
			m.move(randX, randY);
			SharedEntity.getInstance().add(m);	
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.out.println("map.Map.spawnRandom Error: No constructor");
		} catch (ListEmptyException e) {
			System.out.println("map.Map.spawnRandom Error: No structure to spawn");
		} catch (NegativeWeightedRandomException e) {
			System.out.println("map.Map.spawnRandom Error: Negative area of spawn");
		}
	}

	public double getFriction(Entity e) {
		return isOnFloor(e) ? groundFriction : airFriction;
	}

	public MapStructure getStructure() {
		return structure;
	}

	public double getMaxVelocityY() {
		return maxVelocityY;
	}

	public List<IParticle> getParticles() {
		return particles;
	}

	public List<Portal> getPortals() {
		return portals;
	}

}
