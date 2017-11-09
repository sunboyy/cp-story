package model;

public interface Movable extends Renderable {
	public void move();
	public void setX(double x);
	public void setY(double y);
	public void pushAccX(double accX);
	public void pushAccY(double accY);
	public double getVelocityX();
	public double getVelocityY();
	public void setVelocityX(double velocityX);
	public void setVelocityY(double velocityY);
}
