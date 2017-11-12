package testui;

import javafx.scene.image.Image;
import model.map.Map;
import model.map.StructureItem;

public class TestMap extends Map {
	public TestMap() {
//		super(new Image("henesys.jpg"),
//				new StructureItem(0, 645, 1366, 5, false),
//				new StructureItem(280, 385, 1366-280, 5),
//				new StructureItem(375, 130, 1366-375, 5),
//				
//				new StructureItem(265, 575, 100, 5),
//				new StructureItem(350, 320, 100, 5),
//				new StructureItem(430, 60, 105, 5),
//				
//				new StructureItem(90, 510, 140, 5),
//				new StructureItem(90, 450, 140, 5),
//				new StructureItem(180, 255, 140, 5),
//				new StructureItem(180, 190, 140, 5));
		super(new Image("foreground.png"),
				new StructureItem(0, 695, 1500, 5, false),
				
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
