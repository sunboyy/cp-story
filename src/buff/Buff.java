package buff;

import javafx.scene.image.Image;

public abstract class Buff {
	
	private long startTimeMillis;
	private long buffTimeMillis;
	private Image image;
	
	public Buff(long buffTimeMillis, Image image) {
		this.buffTimeMillis = buffTimeMillis;
		this.image = image;
		this.startTimeMillis = System.currentTimeMillis();
	}
	
	public abstract double getAttackMultiplier();
	public abstract double getExperienceMultiplier();
	
	public void refresh() {
		startTimeMillis = System.currentTimeMillis();
	}
	
	public boolean isExpired() {
		return System.currentTimeMillis() >= startTimeMillis+buffTimeMillis;
	}
	
	public int getRemainingTime() {
		return (int) (startTimeMillis+buffTimeMillis-System.currentTimeMillis())/1000;
	}
	
	public Image getImage() {
		return image;
	}
	
}
