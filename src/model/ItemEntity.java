package model;

import model.item.Item;

public class ItemEntity extends Entity {

	private Item item;
	private int age = 0;
	private int maxAge = 3600;
	
	public ItemEntity(Item item, double x, double y) {
		super(item.getName(), item.getImg(), x, y);
		this.item = item;
		this.velocityY = -10;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void update() {
		age++;
	}
	
	public boolean isExpired() {
		return age >= maxAge;
	}

}
