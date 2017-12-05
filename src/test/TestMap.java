package test;

import constants.Images;
import javafx.scene.image.Image;
import model.map.Map;
import model.map.StructureItem;

public class TestMap extends Map {
	
	public TestMap() {
		super(new Image("foreground.png"), Images.beachBackground, TestMonster.class);
		getStructure().addAll(new StructureItem(0, 695, 1500, 5, true, false),
				new StructureItem(640, 620, 50, 5, false),
				new StructureItem(570, 550, 50, 5, false),
				new StructureItem(500, 480, 50, 5, false),
				new StructureItem(500, 405, 50, 5, false),
				new StructureItem(500, 330, 50, 5, false),
				new StructureItem(500, 255, 50, 5, false),
				new StructureItem(500, 180, 50, 5, false),
				new StructureItem(500, 105, 50, 5, false),
				new StructureItem(720, 550, 50, 5, false),
				new StructureItem(800, 480, 340, 5, true));
	}

}
