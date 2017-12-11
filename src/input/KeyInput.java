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
	private static final Set<KeyCode> UNPOLLABLE_KEYS = new HashSet<>();
	
	static {
		UNPOLLABLE_KEYS.add(KeyCode.LEFT);
		UNPOLLABLE_KEYS.add(KeyCode.RIGHT);
		UNPOLLABLE_KEYS.add(KeyCode.UP);
		UNPOLLABLE_KEYS.add(KeyCode.DOWN);
		UNPOLLABLE_KEYS.add(KeyCode.SPACE);
		UNPOLLABLE_KEYS.add(KeyCode.E);
	}
	
	private static void addKey(KeyCode code) {
		if (!UNPOLLABLE_KEYS.contains(code) && !activeKeys.contains(code)) {
			triggerKeys.add(code);
		}
		activeKeys.add(code);
	}
	
	private static void removeKey(KeyCode code) {
		activeKeys.remove(code);
	}
	
	public static boolean pressingKey(KeyCode code) {
		return activeKeys.contains(code);
	}
	
	public static void clear() {
		activeKeys.clear();
	}
	
	public static boolean isPollAvailable() {
		return triggerKeys.size() != 0;
	}
	
	public static KeyCode pollKey() {
		if (triggerKeys.size() == 0) {
			return null;
		}
		return triggerKeys.poll();
	}
	
	public static void bindScene(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> addKey(event.getCode()));
		scene.setOnKeyReleased((KeyEvent event) -> removeKey(event.getCode()));
	}
	
}
