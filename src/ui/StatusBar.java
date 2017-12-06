package ui;

import constants.Constants;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.GameManager;
import model.player.Player;

public class StatusBar {
	
	public static final double HEIGHT = 60;
	public static final double EXPERIENCE_HEIGHT = 5;
	public static final double HP_MP_WIDTH = 200;
	public static final double HP_MP_HEIGHT = 16;
	public static final double HP_MP_X = 140;
	public static final double HP_Y = 10;
	public static final double MP_Y = 29;
	
	public static final Color BACKGROUND_COLOR = Color.color(0, 0, 0, .6);
	public static final Color EXPERIENCE_COLOR = Color.LIME;
	public static final Color HP_COLOR = Color.RED;
	public static final Color MP_COLOR = Color.color(0, .7, 1);
	
	public static final Font HP_MP_LABEL_FONT = Font.font("Tahoma", FontWeight.BOLD, 15);
	public static final Font HP_MP_BAR_FONT = Font.font("Tahoma", FontWeight.BOLD, 12);
	public static final Font EXPERIENCE_FONT = Font.font("Tahoma", FontWeight.BOLD, 9);
	public static final Font KEYBOARD_FONT = Font.font("Tahoma", 9);
	
	private static double hpWidth = 0;
	private static double mpWidth = 0;
	private static double expWidth = 0;
	
	private static Player player;
	
	public static void render(GraphicsContext gc) {
		player = GameManager.getInstance().getPlayer();
		gc.setFill(BACKGROUND_COLOR);
		gc.fillRect(0, Constants.MAP_HEIGHT-HEIGHT, Constants.MAP_WIDTH, HEIGHT);
		
		expWidth = Math.ceil(Constants.MAP_WIDTH*player.getExperience()*0.2/Constants.LEVEL_EXPERIENCE[player.getLevel()] + 0.8*expWidth);
		
		// Experience bar
		if (player.getLevel() < Constants.MAX_LEVEL) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, Constants.MAP_HEIGHT-EXPERIENCE_HEIGHT, Constants.MAP_WIDTH, EXPERIENCE_HEIGHT);
			gc.setFill(EXPERIENCE_COLOR);
			gc.fillRect(0, Constants.MAP_HEIGHT-EXPERIENCE_HEIGHT, expWidth, EXPERIENCE_HEIGHT);
		}
		
		hpWidth = Math.ceil(HP_MP_WIDTH*player.getHp()*0.2/player.getMaxHp() + 0.8*hpWidth);
		mpWidth = Math.ceil(HP_MP_WIDTH*player.getMp()*0.2/player.getMaxMp() + 0.8*mpWidth);
		
		// HP bar
		gc.setFill(Color.GRAY);
		gc.fillRoundRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+HP_Y, HP_MP_WIDTH, HP_MP_HEIGHT, 5, 5);
		gc.setFill(HP_COLOR);
		gc.fillRoundRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+HP_Y, hpWidth, HP_MP_HEIGHT, 5, 5);
		
		// MP bar
		gc.setFill(Color.GRAY);
		gc.fillRoundRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+MP_Y, HP_MP_WIDTH, HP_MP_HEIGHT, 5, 5);
		gc.setFill(MP_COLOR);
		gc.fillRoundRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+MP_Y, mpWidth, HP_MP_HEIGHT, 5, 5);
		
		// Temporary show skill
		gc.setFill(Color.RED);
		gc.fillRoundRect(360, Constants.MAP_HEIGHT-HEIGHT+10, 36, 36, 8, 8);
		gc.setFill(Color.LIME);
		gc.fillRoundRect(410, Constants.MAP_HEIGHT-HEIGHT+10, 36, 36, 8, 8);
		gc.setFill(Color.BLUE);
		gc.fillRoundRect(460, Constants.MAP_HEIGHT-HEIGHT+10, 36, 36, 8, 8);
		gc.setFill(Color.YELLOW);
		gc.fillRoundRect(510, Constants.MAP_HEIGHT-HEIGHT+10, 36, 36, 8, 8);
		
		drawText(gc);
	}
	
	private static void drawText(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		
		// Experience bar
		if (player.getLevel() < Constants.MAX_LEVEL) {
			gc.setFont(EXPERIENCE_FONT);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.BASELINE);
			gc.fillText(String.format("%d/%d (%.2f%%)", player.getExperience(), Constants.LEVEL_EXPERIENCE[player.getLevel()], 100.*player.getExperience()/Constants.LEVEL_EXPERIENCE[player.getLevel()], player.getHp(), player.getMaxHp(), player.getMp(), player.getMaxMp()), Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT-6);
		}
		
		// Level
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(String.format("Lv. %d", player.getLevel()), 10, Constants.MAP_HEIGHT-HEIGHT/2);

		// HP MP Label
		gc.setFont(HP_MP_LABEL_FONT);
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("HP:", HP_MP_X-4, Constants.MAP_HEIGHT-HEIGHT+HP_Y+HP_MP_HEIGHT/2);
		gc.fillText("MP:", HP_MP_X-4, Constants.MAP_HEIGHT-HEIGHT+MP_Y+HP_MP_HEIGHT/2);

		// HP MP Value
		gc.setFont(HP_MP_BAR_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(String.format("%d / %d", player.getHp(), player.getMaxHp()), HP_MP_X+HP_MP_WIDTH/2, Constants.MAP_HEIGHT-HEIGHT+HP_Y+HP_MP_HEIGHT/2);
		gc.fillText(String.format("%d / %d", player.getMp(), player.getMaxMp()), HP_MP_X+HP_MP_WIDTH/2, Constants.MAP_HEIGHT-HEIGHT+MP_Y+HP_MP_HEIGHT/2);
		
		// Skill
		gc.setFont(KEYBOARD_FONT);
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setTextBaseline(VPos.TOP);
		gc.fillText("Q", 359, Constants.MAP_HEIGHT-HEIGHT+10);
		gc.fillText("W", 409, Constants.MAP_HEIGHT-HEIGHT+10);
		gc.fillText("E", 459, Constants.MAP_HEIGHT-HEIGHT+10);
		gc.fillText("R", 509, Constants.MAP_HEIGHT-HEIGHT+10);
	}
	
}
