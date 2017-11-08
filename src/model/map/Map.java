package model.map;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Entity;

public abstract class Map {

	private double x = 0;
	private double y = 0;
	private double width;
	private double height;
	private Image backgroundImage;
	private double movementSpeed = 3.9;
	
	protected double groundFriction = 0.4;
	protected double airFriction = 0.1;
	protected double maxVelocityX = 4;
	protected double maxVelocityY = 6;
	protected boolean allowMultipleJumps = false;
	
	private List<Entity> entities;
	private MapStructure structure;
	
	public Map(Image img) {
		backgroundImage = img;
		width = img.getWidth();
		height = img.getHeight();
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(backgroundImage, -x, -y);
		structure.render(gc);
		for (Entity i: entities) i.render(gc);
	}
	
}
