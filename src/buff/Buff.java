package buff;

public abstract class Buff {
	
	public int age = 0;
	public int maxAge;
	
	public Buff(int age) {
		maxAge = age;
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
	
}
