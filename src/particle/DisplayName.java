package particle;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.DamageableEntity;
import model.GameManager;
import model.player.Player;

public class DisplayName implements IParticle {
	
	public static final Font NAME_FONT = Font.font("Helvetica", FontWeight.BOLD, 12);

	private DamageableEntity entity;
	private int visibleTick = 180;
	private int maxVisibleTick = 180;

	public DisplayName(DamageableEntity entity) {
		this.entity = entity;
	}

	public void render(GraphicsContext gc) {
		if (!isVisible() && !(entity instanceof Player)) return;
		
		double mapX = GameManager.getInstance().getCurrentMap().getX();
		double mapY = GameManager.getInstance().getCurrentMap().getY();
		double centerX = entity.getX() + entity.getWidth() / 2;
		String text = String.format("Lv. %d: %s", entity.getLevel(), entity.getName());
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double textWidth = fontLoader.computeStringWidth(text, NAME_FONT);
		double textHeight = fontLoader.getFontMetrics(NAME_FONT).getLineHeight();
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.TOP);
		gc.setFill(Color.BLACK);
		gc.setFont(NAME_FONT);
		gc.setGlobalAlpha(0.6);
		gc.fillRoundRect(centerX-textWidth/2-4-mapX, entity.getY()+entity.getHeight()-mapY, textWidth+8, textHeight+8, 8, 8);
		gc.setGlobalAlpha(1);
		gc.setFill(Color.WHITE);
		gc.fillText(text, centerX-mapX, entity.getY()+entity.getHeight()+4-mapY);
		visibleTick++;
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
