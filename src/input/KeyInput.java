package input;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;

public class KeyInput {
	
	private static Set<KeyCode> activeKeys = new HashSet<>();
	
	public static void addKey(KeyCode code) {
		activeKeys.add(code);
	}
	
	public static void removeKey(KeyCode code) {
		activeKeys.remove(code);
	}
	
	public static boolean containsKey(KeyCode code) {
		return activeKeys.contains(code);
	}
	
}
