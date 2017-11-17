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

public class HPBar extends Particle {
	
	public static final int WIDTH = 80;
	public static final int HEIGHT = 6;
	public static final Font NAME_FONT = Font.font("Helvetica", FontWeight.BOLD, 12);
	private DamageableEntity entity;
	private int visibleTick = 180;
	private int maxVisibleTick = 180;
	
	public HPBar(DamageableEntity entity) {
		this.entity = entity;
	}
	
	public void render(GraphicsContext gc) {
		if (!isVisible()) return;
		
		double mapX = GameManager.getInstance().getCurrentMap().getX();
		double mapY = GameManager.getInstance().getCurrentMap().getY();
		
		double centerX = entity.getX() + entity.getWidth() / 2;
		double startX = centerX - WIDTH / 2;
		gc.setFill(Color.GRAY);
		gc.fillRect(startX-mapX, entity.getY()-5-HEIGHT-mapY, WIDTH, HEIGHT);
		gc.setFill(Color.RED);
		gc.fillRect(startX-mapX, entity.getY()-5-HEIGHT-mapY, WIDTH * entity.getHp() / entity.getMaxHp(), HEIGHT);
		gc.setLineWidth(1);
		gc.strokeRect(startX-mapX, entity.getY()-5-HEIGHT-mapY, WIDTH, HEIGHT);
		
		String text = String.format("Level %d: %s", entity.getLevel(), entity.getName());
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double textWidth = fontLoader.computeStringWidth(text, NAME_FONT);
		double textHeight = fontLoader.getFontMetrics(NAME_FONT).getLineHeight();
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.TOP);
		gc.setFill(Color.BLACK);
		gc.setFont(NAME_FONT);
		gc.setGlobalAlpha(0.7);
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
