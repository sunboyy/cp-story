
package constants;

import javafx.scene.media.AudioClip;

public class Sounds {
	
	public static final AudioClip punchSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/punch.mp3").toString());
	public static final AudioClip deadSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/splat.wav").toString());
	public static final AudioClip levelUpSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/levelup.mp3").toString());
	public static final AudioClip tadaSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/tada.mp3").toString());
	public static final AudioClip fireSound = new AudioClip(ClassLoader.getSystemResource("audio/effects/fire.mp3").toString());
	
	public static final AudioClip gardenBgm = new AudioClip(ClassLoader.getSystemResource("audio/bgm/garden.wav").toString());
	public static final AudioClip building4Bgm = new AudioClip(ClassLoader.getSystemResource("audio/bgm/building4.wav").toString());
	public static final AudioClip skycafeBgm = new AudioClip(ClassLoader.getSystemResource("audio/bgm/skycafe.wav").toString());
	
	static {
		punchSound.setVolume(0.25);
	}
}
