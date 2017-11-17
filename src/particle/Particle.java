package particle;

import javafx.scene.canvas.GraphicsContext;

public abstract class Particle {
	public abstract boolean isVisible();
	public abstract boolean isExpired();
	public abstract void render(GraphicsContext gc);
}
