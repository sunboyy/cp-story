package model.map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Entity;

public class MapStructure extends ArrayList<StructureItem> {
	
	private static final long serialVersionUID = -4023100906130113189L;
	private Map map;
	
	public MapStructure(Map map) {
		super();
		this.map = map;
	}
	
	public MapStructure(StructureItem...items) {
		super();
		for (StructureItem i: items) add(i);
	}
	
	public StructureItem collideWith(Entity other) {
		for (StructureItem i: this) {
			if (i.collideWith(other)) return i;
		}
		return null;
	}
	
	public void render(GraphicsContext gc) {
		
		for (StructureItem i: this) {
			gc.setFill(i.isPassable() ? Color.YELLOW : Color.RED);
			gc.fillRect(i.getX()-map.getX(), i.getY()-map.getY(), i.getWidth(), i.getHeight());
		}
	}
	
}
