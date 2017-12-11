package particle;

import constants.FontStream;
import constants.Images;
import controller.GameManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.player.Player;

public class LevelUp implements IParticle {

	public static final Font LEVEL_UP_FONT = Font.loadFont(FontStream.ARIAL_ROUNDED_BOLD, 24);
	public static final Color TEXT_COLOR = Color.color(1, 198./255, 2./255);
	
	private int visibleTick = 0;
	private int maxVisibleTick = 120;
	
	@Override
	public boolean isVisible() {
		return visibleTick < maxVisibleTick;
	}

	@Override
	public boolean isExpired() {
		return visibleTick >= maxVisibleTick;
	}

	@Override
	public void render(GraphicsContext gc) {
		Player player = GameManager.getInstance().getPlayer();
		
		Image img;
		if (visibleTick < 40) {
			img = Images.levelUpEffect[visibleTick/2];
		}
		else if (visibleTick + 40 > maxVisibleTick) {
			img = Images.levelUpEffect[(maxVisibleTick-visibleTick)/2];
		}
		else {
			img = Images.levelUpEffect[Images.levelUpEffect.length-1];
		}
		double imageX = player.getX()+player.getWidth()/2-img.getWidth()/2-GameManager.getInstance().getCurrentMap().getX();
		double imageY = player.getY()+player.getHeight()-img.getHeight()-GameManager.getInstance().getCurrentMap().getY();
		gc.drawImage(img, imageX, imageY);
		
		double x = player.getX()+player.getWidth()/2-GameManager.getInstance().getCurrentMap().getX();
		double y = player.getY()-GameManager.getInstance().getCurrentMap().getY()-visibleTick/2;
		
		gc.setFont(LEVEL_UP_FONT);
		gc.setFill(TEXT_COLOR);
		gc.setStroke(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BASELINE);
		gc.setLineWidth(1);
		if (visibleTick >= 60) {
			gc.setGlobalAlpha((maxVisibleTick-visibleTick)/60.);
			gc.fillText("LEVEL UP", x, y);
			gc.strokeText("LEVEL UP", x, y);
			gc.setGlobalAlpha(1);
		}
		else {
			gc.fillText("LEVEL UP", x, y);
			gc.strokeText("LEVEL UP", x, y);
		}
		visibleTick++;
	}

}
