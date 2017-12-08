package utility;

import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

	public static void fadeIn(MediaPlayer player, long fadeTimeMillis) {
		long startTimeMillis = System.currentTimeMillis();
		player.setVolume(0);
		player.play();
		new Thread(() -> {
			while (System.currentTimeMillis() < startTimeMillis+fadeTimeMillis) {
				double volume = (0.+System.currentTimeMillis()-startTimeMillis)/fadeTimeMillis;
				player.setVolume(volume);
			}
			player.setVolume(1);
		}).start();
	}
	
	public static void fadeOut(MediaPlayer player, long fadeTimeMillis) {
		long startTimeMillis = System.currentTimeMillis();
		new Thread(() -> {
			while (System.currentTimeMillis() < startTimeMillis+fadeTimeMillis) {
				double volume = 1-(0.+System.currentTimeMillis()-startTimeMillis)/fadeTimeMillis;
				player.setVolume(volume);
			}
			player.stop();
		}).start();
	}
	
}
