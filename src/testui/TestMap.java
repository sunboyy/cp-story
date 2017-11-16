package testui;

import javafx.scene.image.Image;
import model.map.Map;
import model.map.StructureItem;

public class TestMap extends Map {
	
	public TestMap() {
		super(new Image("foreground.png"), new Image("background.png"), TestMonster.class);
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
		getEntities().add(new TestMonster(100, 550));
	}

}
