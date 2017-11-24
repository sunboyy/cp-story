package constants;

import javafx.scene.media.AudioClip;

public class Sounds {
	public static AudioClip punchSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/punch.mp3").toString());
	public static AudioClip deadSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/splat.wav").toString());
	
	static {
		punchSound.setVolume(0.25);
	}
}
