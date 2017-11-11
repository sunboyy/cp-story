package model.map;

import model.Rectangle;

public class StructureItem extends Rectangle {
	
	private boolean isPassable = true;
	
	public StructureItem(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public StructureItem(int x, int y, int width, int height, boolean isPassable) {
		super(x, y, width, height);
		this.isPassable = isPassable;
	}
	
	public boolean collideWith(Rectangle r) {
		return super.collideWith(r.getX(), r.getY()+r.getHeight(), r.getWidth(), 2);
	}

	public boolean isPassable() {
		return isPassable;
	}
	
}
