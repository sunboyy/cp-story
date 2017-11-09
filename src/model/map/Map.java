package model.map;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Entity;
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
		moveMap(e);
		if (e.getVelocityY() >= 0 && isOnFloor(e)) {
			e.setVelocityY(0);
			if (e instanceof Player) {
				((Player) e).setJumping(false);
			}
		}
	}
	
	private void moveMap(Renderable e) {
		// Move map by object inside
		if (e.getX() > Constants.MAP_WIDTH + x - e.getWidth() - 100) x += movementSpeed;
		else if (e.getX() < x + 100) x -= movementSpeed;
		if (e.getY() > Constants.MAP_HEIGHT + y - e.getHeight() - 100) y += movementSpeed;
		else if (e.getY() < y + 100) y -= movementSpeed;
		
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
	
	public void pushAccX(Movable e, double accX) {
		if (accX > 0) accX += isOnFloor(e) ? groundFriction : airFriction;
		else if (accX < 0) accX -= isOnFloor(e) ? groundFriction : airFriction;
		
		if (e.getVelocityX() + accX > maxVelocityX) e.setVelocityX(maxVelocityX);
		else if (e.getVelocityX() + accX < -maxVelocityX) e.setVelocityX(-maxVelocityX);
		else e.pushAccX(accX);
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

}
