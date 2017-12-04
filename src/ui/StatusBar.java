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
	public static final Font EXPERIENCE_FONT = Font.font("Tahoma", FontWeight.BOLD, 12);
	
	public static void render(GraphicsContext gc) {
		Player player = GameManager.getInstance().getPlayer();
		gc.setFill(BACKGROUND_COLOR);
		gc.fillRect(0, Constants.MAP_HEIGHT-HEIGHT, Constants.MAP_WIDTH, HEIGHT);
		
		// Experience bar
		if (player.getLevel() < Constants.MAX_LEVEL) {
			gc.setFill(Color.BLACK);
			gc.fillRect(0, Constants.MAP_HEIGHT-EXPERIENCE_HEIGHT, Constants.MAP_WIDTH, EXPERIENCE_HEIGHT);
			gc.setFill(EXPERIENCE_COLOR);
			gc.fillRect(0, Constants.MAP_HEIGHT-EXPERIENCE_HEIGHT, Constants.MAP_WIDTH*player.getExperience()/Constants.LEVEL_EXPERIENCE[player.getLevel()], EXPERIENCE_HEIGHT);

			gc.setFont(EXPERIENCE_FONT);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setTextBaseline(VPos.BASELINE);
			gc.setFill(Color.WHITE);
			gc.fillText(String.format("%d/%d (%.2f%%)", player.getExperience(), Constants.LEVEL_EXPERIENCE[player.getLevel()], 100.*player.getExperience()/Constants.LEVEL_EXPERIENCE[player.getLevel()], player.getHp(), player.getMaxHp(), player.getMp(), player.getMaxMp()), Constants.MAP_WIDTH/2, Constants.MAP_HEIGHT-8);
		}
		
		// Level
		gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.WHITE);
		gc.fillText(String.format("Lv. %d", player.getLevel()), 10, Constants.MAP_HEIGHT-HEIGHT/2);

		// HP bar
		gc.setFill(Color.WHITE);
		gc.setFont(HP_MP_LABEL_FONT);
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("HP:", HP_MP_X-4, Constants.MAP_HEIGHT-HEIGHT+HP_Y+HP_MP_HEIGHT/2);
		gc.setFill(Color.GRAY);
		gc.fillRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+HP_Y, HP_MP_WIDTH, HP_MP_HEIGHT);
		gc.setFill(HP_COLOR);
		gc.fillRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+HP_Y, HP_MP_WIDTH*player.getHp()/player.getMaxHp(), HP_MP_HEIGHT);
		
		// MP bar
		gc.setFill(Color.WHITE);
		gc.fillText("MP:", HP_MP_X-4, Constants.MAP_HEIGHT-HEIGHT+MP_Y+HP_MP_HEIGHT/2);
		gc.setFill(Color.GRAY);
		gc.fillRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+MP_Y, HP_MP_WIDTH, HP_MP_HEIGHT);
		gc.setFill(MP_COLOR);
		gc.fillRect(HP_MP_X, Constants.MAP_HEIGHT-HEIGHT+MP_Y, HP_MP_WIDTH*player.getMp()/player.getMaxMp(), HP_MP_HEIGHT);
		
		gc.setFill(Color.WHITE);
		gc.setFont(HP_MP_BAR_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.fillText(String.format("%d / %d", player.getHp(), player.getMaxHp()), HP_MP_X+4, Constants.MAP_HEIGHT-HEIGHT+HP_Y+HP_MP_HEIGHT/2);
		gc.fillText(String.format("%d / %d", player.getMp(), player.getMaxMp()), HP_MP_X+4, Constants.MAP_HEIGHT-HEIGHT+MP_Y+HP_MP_HEIGHT/2);
	}
	
}
