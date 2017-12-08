package model.map;

import constants.Images;
import constants.Sounds;
import model.monster.ProgMeth;

public class Building4 extends Map {

	public Building4() {
		super(Images.building4Background, Sounds.gardenBgm, ProgMeth.class);
		getStructure().addAll(new StructureItem(0, 812, 1500, 5,true,false),
				new StructureItem(901, 451, 350, 5, true),
				
				new StructureItem(215, 717, 210, 5, true),
				new StructureItem(187, 582, 210, 5, true),
				new StructureItem(549, 638, 210, 5, true),
				new StructureItem(908, 718, 210, 5, true),
				
				new StructureItem(464, 684, 55, 5, false),
				new StructureItem(464, 562, 55, 5, false),
				new StructureItem(504, 397, 55, 5, false),
				new StructureItem(805, 684, 55, 5, false),
				new StructureItem(811, 515, 55, 5, false),
				new StructureItem(888, 598, 55, 5, false),
				new StructureItem(739, 420, 55, 5, false));
	}
}
