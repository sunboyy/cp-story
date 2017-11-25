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
	private static final Set<KeyCode> pollableKeys = new HashSet<>();
	
	static {
		pollableKeys.add(KeyCode.DIGIT0);
		pollableKeys.add(KeyCode.DIGIT1);
		pollableKeys.add(KeyCode.DIGIT2);
		pollableKeys.add(KeyCode.DIGIT3);
		pollableKeys.add(KeyCode.DIGIT4);
		pollableKeys.add(KeyCode.DIGIT5);
		pollableKeys.add(KeyCode.DIGIT6);
		pollableKeys.add(KeyCode.DIGIT7);
		pollableKeys.add(KeyCode.DIGIT8);
		pollableKeys.add(KeyCode.DIGIT9);
		pollableKeys.add(KeyCode.C);
		pollableKeys.add(KeyCode.A);
		pollableKeys.add(KeyCode.Q);
	}
	
	public static void addKey(KeyCode code) {
		if (pollableKeys.contains(code) && !activeKeys.contains(code)) {
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
	
	public static KeyCode pollKey() {
		if (triggerKeys.size() == 0) {
			return null;
		}
		return triggerKeys.poll();
	}
	
	public static boolean pollAvailable() {
		return triggerKeys.size() != 0;
	}
	
	public static void bindScene(Scene scene) {
		scene.setOnKeyPressed((KeyEvent event) -> addKey(event.getCode()));
		scene.setOnKeyReleased((KeyEvent event) -> removeKey(event.getCode()));
	}
	
}
