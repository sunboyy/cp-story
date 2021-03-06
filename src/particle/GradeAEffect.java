package particle;

import constants.Constants;
import constants.Images;
import constants.Sounds;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class GradeAEffect implements IParticle {

	private static final Font CENTER_FONT = Font.font("Gill Sans", FontWeight.BOLD, 192);
	
	private int visibleTick = 0;
	private int maxVisibleTick = 240;
	
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
		if (visibleTick + 30 >= maxVisibleTick) {
			gc.setGlobalAlpha((maxVisibleTick-visibleTick)/30.);
		}
		gc.drawImage(Images.gradeAEffect[(visibleTick/12)%2], 0, 0);
		gc.setFont(CENTER_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.CYAN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(5);
		gc.strokeText("A", Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2);
		gc.fillText("A", Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT/2);
		if (visibleTick + 30 >= maxVisibleTick) {
			gc.setGlobalAlpha(1);
		}
		if (visibleTick == 30) {
			Sounds.tadaSound.play();
		}
		visibleTick++;
	}

}
