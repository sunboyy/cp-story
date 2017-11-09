package testui;

import javafx.scene.image.Image;
import model.map.Map;
import model.map.StructureItem;

public class TestMap extends Map {
	public TestMap() {
		super(new Image("henesys.jpg"),
				new StructureItem(0, 700, 200, 6, false),
				new StructureItem(100, 650, 200, 6));
	}

}
