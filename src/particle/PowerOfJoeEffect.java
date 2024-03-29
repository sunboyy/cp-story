package particle;

import constants.Images;
import controller.GameManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Entity;
import model.map.Map;
import model.player.Player;

public class PowerOfJoeEffect implements IParticle {

	private int age = 0;
	private int maxAge = 30;
	
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
		Image img = Images.skillPowerOfJoe;
		gc.setGlobalAlpha((-1./maxAge)*age + 1);
		if (player.getFacing() == Entity.LEFT) {
			gc.drawImage(img, player.getX()-img.getWidth()-map.getX()+10, player.getY()-map.getY()+50,img.getWidth()*1.5,img.getHeight()*1.5);
		}
		else {
			gc.drawImage(img, player.getX()+player.getWidth()+img.getWidth()-map.getX()-10, player.getY()-map.getY()+50, -img.getWidth()*1.5, img.getHeight()*1.5);
		}
		gc.setGlobalAlpha(1);
		age++;
	}

}
