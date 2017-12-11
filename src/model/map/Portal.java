package model.map;

import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Rectangle;

public class Portal extends Rectangle {
	
	private int counter = 0;
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
		gc.setGlobalAlpha(0.7);
		counter = (counter+1) % 30;
		Image img = Images.portal[counter/10];
		gc.drawImage(img, x-GameManager.getInstance().getCurrentMap().getX() - img.getWidth()/2, y-GameManager.getInstance().getCurrentMap().getY() - img.getHeight()+10);
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
