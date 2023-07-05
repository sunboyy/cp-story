package particle;

import controller.GameManager;
import javafx.geometry.Dimension2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.DamageableEntity;
import model.player.Player;

public class DisplayName implements IParticle {
	
	public static final Font NAME_FONT = Font.font("Arial", FontWeight.BOLD, 12);
	public static final double PADDING = 4;

	private DamageableEntity entity;
	private int visibleTick = 180;
	private int maxVisibleTick = 180;

	public DisplayName(DamageableEntity entity) {
		this.entity = entity;
	}

	public void render(GraphicsContext gc) {
		if (!isVisible() && !(entity instanceof Player)) return;
		
		String text = String.format("Lv. %d: %s", entity.getLevel(), entity.getName());
		Dimension2D textSize = calculateTextSize(text);
		
		renderBackground(gc, textSize);
		renderText(gc, text, textSize);
		
		visibleTick++;
	}
	
	private Dimension2D calculateTextSize(String textValue) {
		Text text = new Text(textValue);
		text.setFont(NAME_FONT);
		return new Dimension2D(text.getLayoutBounds().getWidth(), text.getLayoutBounds().getHeight());
	}
	
	private void renderBackground(GraphicsContext gc, Dimension2D textSize) {
		double mapOffsetX = GameManager.getInstance().getCurrentMap().getX();
		double entityCenterX = entity.getX() + entity.getWidth() / 2;
		double topLeftX = entityCenterX - textSize.getWidth() / 2 - PADDING - mapOffsetX;
		
		double mapOffsetY = GameManager.getInstance().getCurrentMap().getY();
		double topLeftY = entity.getY() + entity.getHeight() - mapOffsetY;
		
		double width = textSize.getWidth() + PADDING * 2;
		double height = textSize.getHeight() + PADDING * 2;
		
		gc.setFill(Color.BLACK);
		gc.setGlobalAlpha(0.6);
		gc.fillRoundRect(topLeftX, topLeftY, width, height, 8, 8);
	}
	
	private void renderText(GraphicsContext gc, String textValue, Dimension2D textSize) {
		double mapOffsetX = GameManager.getInstance().getCurrentMap().getX();
		double entityCenterX = entity.getX() + entity.getWidth() / 2;
		double x = entityCenterX - mapOffsetX;
		
		double mapOffsetY = GameManager.getInstance().getCurrentMap().getY();
		double y = entity.getY() + entity.getHeight() + PADDING - mapOffsetY;
		
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.TOP);
		gc.setFont(NAME_FONT);
		gc.setGlobalAlpha(1);
		gc.setFill(Color.WHITE);
		gc.fillText(textValue, x, y);
	}
	
	public boolean isVisible() {
		return visibleTick < maxVisibleTick;
	}
	
	public boolean isExpired() {
		return entity.isDead();
	}

	public void resetVisible() {
		visibleTick = 0;
	}
	
}
