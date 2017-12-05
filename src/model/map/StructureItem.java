package model.map;

import model.Rectangle;

public class StructureItem extends Rectangle {
	
	private boolean isPassable = true;
	private boolean isSpawnable;
	
	public StructureItem(int x, int y, int width, int height, boolean isSpawnable) {
		this(x, y, width, height, isSpawnable, true);
	}
	
	public StructureItem(int x, int y, int width, int height, boolean isSpawnable, boolean isPassable) {
		super(x, y, width, height);
		this.isPassable = isPassable;
		this.isSpawnable = isSpawnable;
	}
	
	public boolean collideWith(Rectangle r) {
		return super.collideWith(r.getX(), r.getY()+r.getHeight(), r.getWidth(), 2);
	}

	public boolean isPassable() {
		return isPassable;
	}
	
	public boolean isSpawnable() {
		return isSpawnable;
	}
	
}
