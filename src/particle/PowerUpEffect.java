package particle;

import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.map.Map;
import model.player.Player;

public class PowerUpEffect implements IParticle {
	
	private int age = 0;
	private int maxAge = 90;
	
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
		if (age > 60) {
			gc.setGlobalAlpha(0.8*(maxAge-age)/30);
		}
		else {
			gc.setGlobalAlpha(0.8);
		}
		Image img = Images.powerUpEffect[(age/4)%16];
		gc.drawImage(img, player.getX()-map.getX()+player.getWidth()/2-img.getWidth()/2, player.getY()+5-map.getY()+player.getHeight()-img.getHeight());
		gc.setGlobalAlpha(1);
		age++;
	}

}
