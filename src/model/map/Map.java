package model.map;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Entity;
import model.GameManager;
import model.Movable;
import model.Renderable;
import model.player.Player;

public abstract class Map implements Renderable {

	private double x = 0;
	private double y = 0;
	private double width;
	private double height;
	private Image backgroundImage;
	private double movementSpeed = 3.9;
	
	protected double gravity = 0.55;
	protected double groundFriction = 0.4;
	protected double airFriction = 0.1;
	protected double maxVelocityX = 4;
	protected double maxVelocityY = 6;
	protected boolean allowMultipleJumps = false;
	
	private List<Entity> entities = new ArrayList<>();
	private MapStructure structure = new MapStructure(this);

	public Map(Image img, StructureItem...structureItems) {
		backgroundImage = img;
		width = img.getWidth();
		height = img.getHeight();
		for (StructureItem i: structureItems) structure.add(i);
	}
	
	public void motion(Movable e) {
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
		for (Entity i: entities) {
			if (i instanceof Movable) {
				motion((Movable) i);
			}
		}
	}
	
	
	private void moveMap() {
		// Move map by object inside
		Player player = GameManager.getInstance().getPlayer();
		if (player.getX() > Constants.MAP_WIDTH + x - player.getWidth() - 100) x += movementSpeed;
		else if (player.getX() < x + 100) x -= movementSpeed;
		if (player.getY() > Constants.MAP_HEIGHT + y - player.getHeight() - 100) y += movementSpeed;
		else if (player.getY() < y + 100) y -= movementSpeed;
		
		// Map boundary
		if (x < 0) x = 0;
		else if (x > width - Constants.MAP_WIDTH) x = width - Constants.MAP_WIDTH;
		if (y < 0) y = 0;
		else if (y > height - Constants.MAP_HEIGHT) y = height - Constants.MAP_HEIGHT;
	}
	
	private void moveEntity(Movable e) {
		e.move();
		if (e.getX() < 0) e.setX(0);
		else if (e.getX() + e.getWidth() > width) e.setX(width - e.getWidth());
		if (e.getY() < 0) e.setY(9);
		else if (e.getY() + e.getHeight() > height) e.setY(height - e.getHeight());
	}
	
	public void pushAccX(Entity e, double accX) {
		if (!(e instanceof Movable)) return;
		Movable m = (Movable) e;
		double friction = getFriction(e);
		if (accX > 0) accX += friction;
		else if (accX < 0) accX -= friction;
		
		if (m.getVelocityX() + accX > maxVelocityX) m.setVelocityX(maxVelocityX);
		else if (m.getVelocityX() + accX < -maxVelocityX) m.setVelocityX(-maxVelocityX);
		else m.pushAccX(accX);
	}
	
	private void pullGravity(Movable e) {
		if (isOnFloor(e)) return;
		if (e.getVelocityY() + gravity > maxVelocityY) e.setVelocityY(maxVelocityY);
		else e.pushAccY(gravity);
	}
	
	private void decelerate(Movable e) {
		double friction = isOnFloor(e) ? groundFriction : airFriction;
		if (e.getVelocityX() > friction) e.pushAccX(-friction);
		else if (e.getVelocityX() < -friction) e.pushAccX(friction);
		else e.setVelocityX(0);
	}
	
	private boolean isOnFloor(Renderable e) {
		if (e.getY() + e.getHeight() >= height) return true;
		if (structure.collideWith(e) != null) return true;
		return false;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(backgroundImage, -x, -y);
		structure.render(gc);
		for (Entity i: entities) gc.drawImage(i.getImg(), i.getX()-x, i.getY()-y);
	}
	
	public double getFriction(Entity e) {
		return isOnFloor(e) ? groundFriction : airFriction;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}
	
	public MapStructure getStructure() {
		return structure;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

	public double getMaxVelocityX() {
		return maxVelocityX;
	}

	public double getMaxVelocityY() {
		return maxVelocityY;
	}

}
