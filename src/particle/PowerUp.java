package particle;

import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.map.Map;
import model.player.Player;

public class PowerUp implements IParticle {
	
	int age = 0;
	int maxAge = 60;
	
	@Override
	public boolean isVisible() {
		return age < maxAge;
	}

	@Override
	public boolean isExpired() {
		return age >= maxAge;
	}

	@Override
	public void render(GraphicsContext gc) {
		Player player = GameManager.getInstance().getPlayer();
		Map map = GameManager.getInstance().getCurrentMap();
		Image img = Images.skillPowerUp;
		gc.setGlobalAlpha((-1./maxAge)*age + 1);
		gc.drawImage(img, player.getX() - map.getX() + player.getWidth()/2 - img.getWidth()/2 - 20, player.getY()-map.getY()-82,72,72);
		gc.setGlobalAlpha(1);
		age++;
	}

}
