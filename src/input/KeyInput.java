package input;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {
	
	private static Set<KeyCode> activeKeys = new HashSet<>();
	private static Queue<KeyCode> triggerKeys = new ConcurrentLinkedQueue<>();
	
	public static void addKey(KeyCode code) {
		if (code.isDigitKey() && !activeKeys.contains(code)) {
			triggerKeys.add(code);
		}
		activeKeys.add(code);
		
	}
	
	public static void removeKey(KeyCode code) {
		activeKeys.remove(code);
	}
	
	public static boolean pressingKey(KeyCode code) {
		return activeKeys.contains(code);
	}
	
	public static int pollDigitKey() {
		if (triggerKeys.size() == 0) {
			return -1;
		}
		return Integer.parseInt(triggerKeys.poll().toString().substring(5));
	}
	
	public static boolean digitAvailable() {
		return triggerKeys.size() != 0;
	}
	
	public static void bindScene(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> addKey(event.getCode()));
		scene.setOnKeyReleased((KeyEvent event) -> removeKey(event.getCode()));
	}
	
}
