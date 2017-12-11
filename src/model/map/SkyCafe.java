package model.map;

import constants.Images;
import constants.Sounds;
import model.monster.Node;

public class SkyCafe extends Map {

	public SkyCafe() {
		super(Images.skycafeBackground, Sounds.skycafeBgm, Node.class);
		getStructure().addAll(new StructureItem(0, 812, 1500, 5, true, false),
				new StructureItem(333, 715, 628, 5, true),
				
				new StructureItem(975, 512, 95, 5, false),
				new StructureItem(975, 420, 95, 5, false),
				new StructureItem(833, 393, 140, 5, true),
				
				new StructureItem(216, 562, 55, 5, false),
				new StructureItem(277, 640, 55, 5, false),
				new StructureItem(527, 444, 55, 5, false),
				new StructureItem(977, 635, 55, 5, false),
				new StructureItem(915, 564, 55, 5, false),
				new StructureItem(870, 304, 55, 5, false),
				
				new StructureItem(294, 502, 210, 5, true),
				new StructureItem(562, 619, 210, 5, true));
	}
}
