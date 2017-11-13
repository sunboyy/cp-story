package particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.DamageableEntity;
import model.GameManager;

public class HPBar extends Particle {
	
	public static final int WIDTH = 80;
	public static final int HEIGHT = 6;
	private DamageableEntity entity;
	private int visibleTick = 180;
	private int maxVisibleTick = 180;
	
	public HPBar(DamageableEntity entity) {
		this.entity = entity;
	}
	
	public void render(GraphicsContext gc) {
		if (!isVisible()) return;
		double startX = entity.getX() + entity.getWidth() / 2 - WIDTH / 2;
		gc.setFill(Color.GRAY);
		gc.fillRect(startX-GameManager.getInstance().getCurrentMap().getX(), entity.getY()-5-HEIGHT-GameManager.getInstance().getCurrentMap().getY(), WIDTH, HEIGHT);
		gc.setFill(Color.RED);
		gc.fillRect(startX-GameManager.getInstance().getCurrentMap().getX(), entity.getY()-5-HEIGHT-GameManager.getInstance().getCurrentMap().getY(), WIDTH * entity.getHp() / entity.getMaxHp(), HEIGHT);
		gc.setLineWidth(1);
		gc.strokeRect(startX-GameManager.getInstance().getCurrentMap().getX(), entity.getY()-5-HEIGHT-GameManager.getInstance().getCurrentMap().getY(), WIDTH, HEIGHT);
		visibleTick++;
	}
	
	public boolean isVisible() {
		return visibleTick < maxVisibleTick;
	}
	
	public void resetVisible() {
		visibleTick = 0;
	}
	
}
