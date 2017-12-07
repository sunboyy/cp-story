package model.map;

import constants.Images;
import model.monster.MiniTree;
import model.monster.ProgMeth;

public class Garden extends Map {
	
	public Garden() {
		super(Images.gardenBackground, ProgMeth.class, MiniTree.class);
		getStructure().addAll(new StructureItem(0, 815, 1500, 5, true, false),
				new StructureItem(735, 720, 60, 5, false),
				new StructureItem(815, 645, 65, 5, false),
				new StructureItem(745, 540, 65, 5, false),
				new StructureItem(835, 485, 65, 5, false),
				new StructureItem(1226,524, 60, 5, false),
				
				new StructureItem(290, 430, 60, 5, false),
				new StructureItem(240, 500, 80, 5, false),
				new StructureItem(1160, 300, 30, 5, false),
				new StructureItem(1175, 220, 30, 5, false),
				
				new StructureItem(480, 395, 60, 5, false),
				new StructureItem(400, 310, 620, 6, false),
				new StructureItem(370, 225, 610, 5, false),
				
				new StructureItem(925, 595, 290, 5, true),
				new StructureItem(925, 450, 290, 5, true),
				new StructureItem(430, 595, 290, 5, true),
				new StructureItem(430, 450, 290, 5, true));
	}
}
