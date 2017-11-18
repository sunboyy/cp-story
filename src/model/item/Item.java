package model.item;

import javafx.scene.image.Image;

public abstract class Item {

	private String name;
	private Image img;
	
	protected int count;
	protected int maxCount;
	
	public Item(String name, Image img) {
		this.name = name;
		this.img = img;
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

	public Image getImg() {
		return img;
	}

	public int getCount() {
		return count;
	}

	public int getMaxCount() {
		return maxCount;
	}
	
}
