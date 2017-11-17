package constants;

public class Constants {
	public static final int MAP_WIDTH = 1000;
	public static final int MAP_HEIGHT = 600;
	
	public static final int[] LEVEL_EXPERIENCE = {0, 10, 16, 25, 40, 62, 100, 161, 245, 396, 504,
			746, 973, 1248, 1845, 2605, 4107, 6355, 9002, 13047, 17385};
	public static final int MAX_LEVEL = LEVEL_EXPERIENCE.length-1;
	
	public static final int[] LEVEL_HP = {0, 50, 60, 75, 90, 110, 130, 155, 180, 210, 240,
			275, 310, 350, 390, 435, 480, 530, 580, 635, 690};
	public static final int[] LEVEL_MP = {0, 10, 12, 15, 20, 25, 30, 36, 42, 50, 58,
			66, 76, 86, 98, 110, 125, 140, 155, 170, 185};
	public static final int[] LEVEL_ATTACK_LOW = {0, 3, 4, 5, 6, 8, 10, 12, 14, 16, 18,
			21, 24, 28, 32, 36, 40, 45, 50, 55, 60};
	public static final int[] LEVEL_ATTACK_HIGH = {0, 6, 8, 10, 12, 14, 17, 20, 23, 27, 32,
			37, 42, 48, 54, 60, 68, 76, 86, 98, 110};
}
