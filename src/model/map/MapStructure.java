package model.map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

public class MapStructure extends ArrayList<StructureItem> {
	
	private static final long serialVersionUID = -4023100906130113189L;
	
	public MapStructure() {
		super();
	}
	
	public MapStructure(StructureItem...items) {
		super();
		for (StructureItem i: items) add(i);
	}
	
	public StructureItem collideWith(Entity e) {
		for (StructureItem i: this) {
			if (i.isCollide(e)) return i;
		}
		return null;
	}
	
	public void render(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		for (StructureItem i: this) gc.fillRect(i.getX(), i.getY(), i.getWidth(), i.getHeight());
	}
	
}
