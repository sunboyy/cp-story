package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity extends Rectangle {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	protected double velocityX;
	protected double velocityY;
	private Image img;
	protected int facing = RIGHT;
	private String name;
	
	public Entity(String name, Image img) {
		this(name, img, 0, 0);
	}
	
	public Entity(String name, Image img, double x, double y) {
		super(x, y, img.getWidth(), img.getHeight());
		this.name = name;
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
	
	public void render(GraphicsContext gc) {
		gc.drawImage(getImg(), x-GameManager.getInstance().getCurrentMap().x, y-GameManager.getInstance().getCurrentMap().y);
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
