package model.map;

import constants.Images;
import constants.Sounds;
import model.monster.MiniTree;

public class Garden extends Map {
	
	public Garden() {
		super(Images.gardenBackground, Sounds.gardenBgm, MiniTree.class);
		getStructure().addAll(new StructureItem(0, 815, 1500, 5, true, false),
				new StructureItem(740, 720, 50, 5, false),
				new StructureItem(815, 640, 60, 5, false),
				new StructureItem(750, 535, 60, 5, false),
				new StructureItem(840, 485, 55, 5, false),
				new StructureItem(1230, 524, 55, 5, false),
				
				new StructureItem(290, 430, 60, 5, false),
				new StructureItem(240, 500, 80, 5, false),
				new StructureItem(1160, 300, 30, 5, false),
				new StructureItem(1175, 220, 30, 5, false),
				
				new StructureItem(480, 395, 60, 5, false),
				new StructureItem(400, 310, 620, 6, false),
				new StructureItem(370, 225, 610, 5, false),
				
				new StructureItem(925, 590, 285, 5, true),
				new StructureItem(925, 450, 285, 5, true),
				new StructureItem(430, 590, 285, 5, true),
				new StructureItem(430, 450, 285, 5, true));
	}
}
