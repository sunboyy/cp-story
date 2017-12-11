package particle;

import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.DamageableEntity;

public class HpBar implements IParticle {
	
	public static final int WIDTH = 80;
	public static final int HEIGHT = 6;
	private DamageableEntity entity;
	private int visibleTick = 180;
	private int maxVisibleTick = 180;
	
	public HpBar(DamageableEntity entity) {
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
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeRect(startX-mapX, entity.getY()-5-HEIGHT-mapY, WIDTH, HEIGHT);
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
