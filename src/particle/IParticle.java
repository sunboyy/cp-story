package particle;

import javafx.scene.canvas.GraphicsContext;

public interface IParticle {
	public boolean isVisible();
	public boolean isExpired();
	public void render(GraphicsContext gc);
}
