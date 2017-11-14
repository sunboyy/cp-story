package model;

public class Rectangle {
	
	protected double x;
	protected double y;
	protected double width;
	protected double height;

	public Rectangle() {
		this(0, 0, 0, 0);
	}
	
	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean collideWith(Rectangle r) {
		if (x < r.x + r.width && x + width > r.x && y < r.y + r.height && height + y > r.y) return true;
		return false;
	}
	
	public boolean collideWith(double x, double y, double width, double height) {
		if (this.x < x + width && this.x + this.width > x && this.y < y + height && this.height + this.y > y) return true;
		return false;
	}
	
	public String toString() {
		return String.format("%s [x=%f, y=%f, width=%f, height=%f]", getClass().getSimpleName(), x, y, width, height);
	}
	
}
