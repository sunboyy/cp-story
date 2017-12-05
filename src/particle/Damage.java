package particle;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.GameManager;

public class Damage implements IParticle {
	
	public static final Font FONT = Font.font("Impact", FontWeight.BOLD, 36);
	private int visibleTick = 0;
	private int maxVisibleTick = 60;
	
	private double x;
	private double y;
	private int hp;
	
	public Damage(int hp, double x, double y) {
		this.hp = hp;
		this.x = x;
		this.y = y;
	}
	
	public void render(GraphicsContext gc) {
		gc.setGlobalAlpha(1-(visibleTick+0.0)/maxVisibleTick);
		gc.setFont(FONT);
		gc.setFill(Color.ORANGE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BASELINE);
		gc.setLineWidth(1);
		gc.fillText(hp+"", x-GameManager.getInstance().getCurrentMap().getX(), y-GameManager.getInstance().getCurrentMap().getY());
		gc.setStroke(Color.BLACK);
		gc.strokeText(hp+"", x-GameManager.getInstance().getCurrentMap().getX(), y-GameManager.getInstance().getCurrentMap().getY());
		y -= 0.5;
		visibleTick++;
		gc.setGlobalAlpha(1);
	}
	
	public boolean isVisible() {
		return visibleTick < maxVisibleTick;
	}
	
	public boolean isExpired() {
		return visibleTick >= maxVisibleTick;
	}
	
}
