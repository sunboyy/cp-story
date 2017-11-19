package constants;

import javafx.scene.image.Image;

public class Images {

	public static final Image beachBackground = new Image("background.png");
	public static final Image gardenBackground = new Image("garden-background.jpg",1300,900,false,false);
	public static final Image defaultBackground = new Image("default-background.jpg");
	
	public static final Image beachForeground = new Image("foreground.png");
	
	public static final Image orangeMushMonster = new Image("orangemush.gif");
	public static final Image monsterProgmethR = new Image(ClassLoader.getSystemResource("monster/progmethR.png").toString());
	public static final Image monsterProgmethL = new Image(ClassLoader.getSystemResource("monster/progmethL.png").toString());
	
	public static final Image cpEngPlayerL = new Image("player/CPPlayerL.png",77.25,110,true,false);
	public static final Image cpEngPlayerR = new Image("player/CPPlayerR.png",77.25,110,true,false);
	
	public static final Image redPotionItem = new Image("item/redpotion.png");
	
}
