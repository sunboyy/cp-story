package model;

import javafx.scene.canvas.GraphicsContext;
import model.item.Item;
import model.map.Map;

public class ItemEntity extends Entity {

	private Item item;
	private int age = 0;
	private int maxAge = 3600;
	
	public ItemEntity(Item item, Map map, double x, double y) {
		super(item.getName(), item.getImg(), map, x, y);
		this.item = item;
		this.velocityY = -6;
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
	
	@Override
	public void render(GraphicsContext gc) {
		if (isExpired()) return;
		if (age + 20 > maxAge) {
			gc.setGlobalAlpha((maxAge-age)/20.);
			super.render(gc);
			gc.setGlobalAlpha(1);
		}
		else {
			super.render(gc);
		}
	}
	
	public String toString() {
		return String.format("%s [Item: %s, Age: %d]", getClass().getSimpleName(), item.getClass().getSimpleName(), age);
	}

}
