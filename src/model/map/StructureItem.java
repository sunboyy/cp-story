package model.map;

import model.Entity;

public class StructureItem {
	
	private double x;
	private double y;
	private double width;
	private double height;
	private boolean isPassable = true;
	
	public StructureItem(int x, int y, int width, int height) {
		this.x = x;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public StructureItem(int x, int y, int width, int height, boolean isPassable) {
		this(x, y, width, height);
		this.isPassable = isPassable;
	}

	public boolean isCollide(Entity e) {
		if (x < e.getX() + e.getWidth() && x + width > e.getX() && y < e.getY() + e.getHeight() && height + y > e.getY() + e.getHeight() - 2) return true;
		return false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public boolean isPassable() {
		return isPassable;
	}
	
}
