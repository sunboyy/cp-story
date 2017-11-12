package model;

import javafx.scene.image.Image;

public abstract class Entity extends Rectangle {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	protected double velocityX;
	protected double velocityY;
	private Image img;
	protected int facing = RIGHT;
	
	public Entity(Image img) {
		this(img, 0, 0);
	}
	
	public Entity(Image img, double x, double y) {
		super(x, y, img.getWidth(), img.getHeight());
		this.img = img;
	}
	
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
	}
	
	public void pushAccY(double accY) {
		this.velocityY += accY;
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

	public Image getImg() {
		return img;
	}

	// Setter
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public String toString() {
		return getClass().getSimpleName();
	}

}
