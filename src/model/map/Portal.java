package model.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.GameManager;
import model.Rectangle;

public class Portal extends Rectangle {

	private double xDest;
	private double yDest;
	private Map destination;
	
	public Portal(double xSrc, double ySrc, Map dest, double xDest, double yDest) {
		super(xSrc, ySrc, 5, 5);
		this.destination = dest;
		this.xDest = xDest;
		this.yDest = yDest;
	}
	
	public void render(GraphicsContext gc) {
		gc.setGlobalAlpha(0.5);
		gc.setFill(Color.CYAN);
		gc.fillOval(x-GameManager.getInstance().getCurrentMap().getX()-30, y-GameManager.getInstance().getCurrentMap().getY()-70, 60, 80);
		gc.setFill(Color.BLUE);
		gc.fillOval(x-GameManager.getInstance().getCurrentMap().getX(), y-GameManager.getInstance().getCurrentMap().getY(), width, height);
		gc.setGlobalAlpha(1);
	}

	public double getXDest() {
		return xDest;
	}

	public double getYDest() {
		return yDest;
	}

	public Map getDestination() {
		return destination;
	}
	
}
