package model.map;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Renderable;

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
	
	public StructureItem collideWith(Renderable e) {
		for (StructureItem i: this) {
			if (i.isCollide(e)) return i;
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
