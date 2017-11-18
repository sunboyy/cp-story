package model;

import model.item.Item;

public class ItemEntity extends Entity {

	private Item item;
	
	public ItemEntity(Item item, double x, double y) {
		super(item.getName(), item.getImg(), x, y);
		this.item = item;
		this.velocityY = -10;
	}
	
	public Item getItem() {
		return item;
	}

}
