package testui;

import javafx.scene.image.Image;
import model.Movable;
import model.monster.Monster;

public class TestMonster extends Monster implements Movable {
	
	private double velocityX;
	private double velocityY;
	
	public TestMonster() {
		super(new Image("orangemush.gif"));
	}

	@Override
	public void move() {
		x += velocityX;
		y += velocityY;
	}

	@Override
	public void pushAccX(double accX) {
		velocityX += accX;
	}

	@Override
	public void pushAccY(double accY) {
		velocityY += accY;
	}

	@Override
	public double getVelocityX() {
		return velocityX;
	}

	@Override
	public double getVelocityY() {
		return velocityY;
	}

	@Override
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	@Override
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
}
