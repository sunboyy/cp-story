package model.map;

import constants.Images;
import model.monster.ProgMeth;

public class Garden extends Map {
	
	public Garden() {
		super(Images.beachForeground, Images.gardenBackground,
				ProgMeth.class);
		getStructure().addAll(new StructureItem(0, 695, 1500, 5, false),
				new StructureItem(640, 620, 50, 5),
				new StructureItem(570, 550, 50, 5),
				new StructureItem(500, 480, 50, 5),
				new StructureItem(500, 405, 50, 5),
				new StructureItem(500, 330, 50, 5),
				new StructureItem(500, 255, 50, 5),
				new StructureItem(500, 180, 50, 5),
				new StructureItem(500, 105, 50, 5),
				new StructureItem(720, 550, 50, 5),
				new StructureItem(800, 480, 340, 5));
		getEntities().add(new ProgMeth(100, 550));
	}
}
