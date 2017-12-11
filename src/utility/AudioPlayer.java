package utility;

import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

	public static void fadeIn(MediaPlayer mediaPlayer, long fadeTimeMillis) {
		long startTimeMillis = System.currentTimeMillis();
		mediaPlayer.setVolume(0);
		mediaPlayer.play();
		new Thread(() -> {
			try {
				while (System.currentTimeMillis() < startTimeMillis+fadeTimeMillis) {
					double volume = (0.+System.currentTimeMillis()-startTimeMillis)/fadeTimeMillis;
					mediaPlayer.setVolume(volume);
				}
				mediaPlayer.setVolume(1);
			} catch (NullPointerException e) {}
		}).start();
	}
	
	public static void fadeOut(MediaPlayer mediaPlayer, long fadeTimeMillis) {
		long startTimeMillis = System.currentTimeMillis();
		new Thread(() -> {
			try {
				while (System.currentTimeMillis() < startTimeMillis+fadeTimeMillis) {
					double volume = 1-(0.+System.currentTimeMillis()-startTimeMillis)/fadeTimeMillis;
					mediaPlayer.setVolume(volume);
				}
				mediaPlayer.stop();
			} catch (NullPointerException e) {}
		}).start();
	}
	
}
