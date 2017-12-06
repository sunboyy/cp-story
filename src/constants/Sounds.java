
package constants;

import javafx.scene.media.AudioClip;

public class Sounds {
	public static AudioClip punchSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/punch.mp3").toString());
	public static AudioClip deadSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/splat.wav").toString());
	public static AudioClip levelUpSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/levelup.mp3").toString());
	
	static {
		punchSound.setVolume(0.25);
	}
}
