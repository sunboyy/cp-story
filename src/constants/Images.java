package constants;


import javafx.scene.image.Image;

public class Images {
	
	public static final Image startscreen = new Image("startscreen.jpg");
	public static final Image playbutton = new Image("playbutton.png");
	public static final Image playbutton_highlight = new Image("playbutton_highlight.png");

	public static final Image beachBackground = new Image("background.png");
	public static final Image defaultBackground = new Image("images/background/bg_default.jpg");
	public static final Image gardenBackground = new Image("images/background/bg_garden.jpg",1300,900,false,false);
	public static final Image skycafeBackground = new Image("images/background/bg_sky-cafe.jpg",1300,900,false,false);
	
	public static final Image beachForeground = new Image("foreground.png");
	
	public static final Image orangeMushMonster = new Image("orangemush.gif");
	public static final Image monsterProgmethR = new Image(ClassLoader.getSystemResource("images/monster/progmethR.png").toString());
	public static final Image monsterProgmethL = new Image(ClassLoader.getSystemResource("images/monster/progmethL.png").toString());
	
	public static final Image cpEngPlayerL = new Image("player/CPPlayerL.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkL = new Image("player/CPPlayerLWalk.png",77.25,110,true,false);
	public static final Image cpEngPlayerCryL = new Image("player/CPPlayerLCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkCryL = new Image("player/CPPlayerLWalkCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerR = new Image("player/CPPlayerR.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkR = new Image("player/CPPlayerRWalk.png",77.25,110,true,false); 
	public static final Image cpEngPlayerCryR = new Image("player/CPPlayerRCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkCryR = new Image("player/CPPlayerRWalkCry.png",77.25,110,true,false);
	
	public static final Image redPotionItem = new Image("images/item/redpotion.png");
	public static final Image bluePotionItem = new Image("images/item/bluepotion.png");
	
	public static final Image[] normalAttackSkill = new Image[10];
	
	static {
		for (int i=0; i<normalAttackSkill.length; i++) {
			normalAttackSkill[i] = new Image("images/effect/attack/"+i+".png");
		}
	}
	
}
