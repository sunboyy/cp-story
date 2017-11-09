package input;

import testui.SimpleScene;

public class KeyInput {
	
	public static void bindAll(SimpleScene scene) {
		scene.setOnKeyPressed(e -> {
			scene.getActiveKeys().add(e.getCode());
		});
		scene.setOnKeyReleased(e -> {
			scene.getActiveKeys().remove(e.getCode());
		});
		
	}
	
}
