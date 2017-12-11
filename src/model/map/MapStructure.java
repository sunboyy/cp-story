package model.map;

import java.util.ArrayList;
import java.util.List;

import model.Entity;

public class MapStructure extends ArrayList<StructureItem> {
	
	private static final long serialVersionUID = -4023100906130113189L;
	
	public MapStructure(StructureItem...items) {
		super();
		for (StructureItem i: items) add(i);
	}
	
	public MapStructure(List<StructureItem> items) {
		super();
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
	
}
