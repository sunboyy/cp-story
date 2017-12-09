package model;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.map.Map;

public abstract class Entity extends Rectangle {

	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	protected double velocityX;
	protected double velocityY;
	private Image image, imageL, imageR;
	protected double maxVelocityX = 2;
	protected int facing;
	protected String name;
	private Map map;
	
	public Entity(String name,Image img, Map map, double x, double y) {
		super(x, y, img.getWidth(), img.getHeight());
		this.name = name;
		this.image = img;
		this.map = map;
		imageL = null;
		imageR = null;
	}
	
	public Entity(String name, Image imgL, Image imgR, Map map) {
		this(name, imgL, imgR, map, 0, 0);
	}
	
	public Entity(String name, Image imgL, Image imgR, Map map, double x, double y) {
		super(x, y, imgL.getWidth(), imgL.getHeight());
		this.name = name;
		this.imageL = imgL;
		this.imageR = imgR;
		this.map = map;
		setFacing(RIGHT);
	}
	
	public abstract void update();
	
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void move() {
		this.x += velocityX;
		this.y += velocityY;
	}

	public void pushAccX(double accX) {
		this.velocityX += accX;
		if (velocityX > maxVelocityX)
			velocityX = maxVelocityX;
		else if (velocityX + accX < -maxVelocityX)
			velocityX = -maxVelocityX;
	}
	
	public void pushAccY(double accY) {
		this.velocityY += accY;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(getImage(), x-GameManager.getInstance().getCurrentMap().x, y-GameManager.getInstance().getCurrentMap().y);
	}
	
	// Getter
	public int getFacing() {
		return facing;
	}
	
	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}
	
	public String getName() {
		return name;
	}

	public Image getImage() {
		return image;
	}
	
	public Map getMap() {
		return map;
	}

	// Setter
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public void setImage(Image img) {
		this.image = img;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	public String toString() {
		return getClass().getSimpleName();
	}
	
	public void setFacing(int facing) {
		setFacing(facing, facing==LEFT ? imageL : imageR);
	}
	
	public void setFacing(int facing, Image img) {
		this.facing = facing;
		this.setImage(img);
	}
}

