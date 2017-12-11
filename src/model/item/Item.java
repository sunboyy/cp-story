package model.item;

import javafx.scene.image.Image;
import model.IUsable;

public abstract class Item implements IUsable {

	private String name;
	private String description;
	private Image image;
	
	protected int count;
	protected int maxCount;
	
	public Item(String name, String description, Image img) {
		this.name = name;
		this.description = description;
		this.image = img;
		this.count = 1;
		this.maxCount = 50;
	}
	
	public boolean add(int num) {
		if (count + num <= maxCount) {
			count += num;
			return true;
		}
		return false;
	}
	
	public boolean sameType(Item item) {
		return getClass() == item.getClass();
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Image getImage() {
		return image;
	}

	public int getCount() {
		return count;
	}

	public int getMaxCount() {
		return maxCount;
	}
	
	@Override
	public void activate() {
		if (count > 0) {
			use();
			--count;
		}
	}
	
}
