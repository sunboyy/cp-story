package ui;

import constants.Constants;
import controller.GameManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.item.Item;

public class Tooltip {

	private static double x;
	private static double y;
	private static String title;
	private static String description;
	private static boolean shouldShow = false;
	
	private static final Color BACKGROUND_COLOR = Color.color(0, 0, 0, .8);
	private static final Font TITLE_FONT = Font.font("Tahoma", 16);
	private static final Font DESCRIPTION_FONT = Font.font("Tahoma", 13);
	
	public static void update(double newX, double newY) {
		x = newX;
		y = newY;
		for (int i=0;i<10;i++) {
			if (isMouseInInventory(i)) {
				Item item = GameManager.getInstance().getPlayer().getInventory()[i];
				if (item != null) {
					title = item.getName();
					description = item.getDescription();
					shouldShow = true;
					return;
				}
			}
		}
		shouldShow = false;
	}
	
	private static boolean isMouseInInventory(int i) {
		double leftBound = StatusBar.ITEM_X+i*35;
		double rightBound = leftBound + 30;
		double topBound = Constants.WINDOW_HEIGHT-StatusBar.HEIGHT+StatusBar.SKILL_ITEM_Y;
		double bottomBound = topBound + 30;
		return (x >= leftBound) && (x < rightBound) && (y >= topBound) && (y < bottomBound);
	}
	
	public static void render(GraphicsContext gc) {
		if (shouldShow) {
			gc.setFill(BACKGROUND_COLOR);
			gc.fillRoundRect(x-170, y-51, 170, 46, 10, 10);
			gc.setTextAlign(TextAlignment.LEFT);
			gc.setTextBaseline(VPos.BOTTOM);
			gc.setFill(Color.WHITE);
			gc.setFont(TITLE_FONT);
			gc.fillText(title, x-165, y-29);
			gc.setFont(DESCRIPTION_FONT);
			gc.setTextBaseline(VPos.TOP);
			gc.fillText(description, x-165, y-25);
		}
	}
	
}
