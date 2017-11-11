package input;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {
	
	private static Set<KeyCode> activeKeys = new HashSet<>();
	
	public static void addKey(KeyCode code) {
		activeKeys.add(code);
	}
	
	public static void removeKey(KeyCode code) {
		activeKeys.remove(code);
	}
	
	public static boolean pressingKey(KeyCode code) {
		return activeKeys.contains(code);
	}
	
	public static void bindScene(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> addKey(event.getCode()));
		scene.setOnKeyReleased((KeyEvent event) -> removeKey(event.getCode()));
	}
	
}
