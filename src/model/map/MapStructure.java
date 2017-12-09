package model.map;

import java.util.ArrayList;
import java.util.List;

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
	
	public MapStructure(Map map, StructureItem...items) {
		super();
		this.map = map;
		for (StructureItem i: items) add(i);
	}
	
	public MapStructure(Map map, List<StructureItem> items) {
		super();
		this.map = map;
		for (StructureItem i: items) add(i);
	}
	
	public StructureItem collideWith(Entity other) {
		for (StructureItem i: this) {
			if (i.collideWith(other)) return i;
		}
		return null;
	}
	
	public void addAll(StructureItem...items) {
		for (StructureItem i: items) add(i);
	}
	
	// TODO Remove Render
	public void render(GraphicsContext gc) {
		for (StructureItem i: this) {
			gc.setFill(i.isPassable() ? Color.YELLOW : Color.RED);
			gc.setStroke(i.isSpawnable() ? Color.PINK : Color.GREEN);
			gc.setLineWidth(2);
			gc.fillRect(i.getX()-map.getX(), i.getY()-map.getY(), i.getWidth(), i.getHeight());
			gc.strokeRect(i.getX()-map.getX(), i.getY()-map.getY(), i.getWidth(), i.getHeight());
		}
	}
	
}
