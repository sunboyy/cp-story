package buff;

import javafx.scene.image.Image;

public abstract class Buff {
	
	private int age = 0;
	private int maxAge;
	private Image image;
	
	public Buff(int age, Image image) {
		this.maxAge = age;
		this.image = image;
	}
	
	public abstract double getAttackMultiplier();
	
	public void update() {
		age++;
	}
	
	public void refresh() {
		age = 0;
	}
	
	public boolean isExpired() {
		return age >= maxAge;
	}
	
	public int getRemainingTime() {
		return (maxAge-age)/60;
	}
	
	public Image getImage() {
		return image;
	}
	
}
