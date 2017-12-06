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
	public static final Image monsterProgmethR = new Image("images/monster/progmethR.png");
	public static final Image monsterProgmethL = new Image("images/monster/progmethL.png");
	
	
	public static final Image cpEngPlayerL = new Image("images/player/CPPlayerL.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkL = new Image("images/player/CPPlayerLWalk.png",77.25,110,true,false);
	public static final Image cpEngPlayerCryL = new Image("images/player/CPPlayerLCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkCryL = new Image("images/player/CPPlayerLWalkCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerFightL1 = new Image("images/player/CPPlayerFightL1.png",77.25,110,true,false);
	public static final Image cpEngPlayerFightL2 = new Image("images/player/CPPlayerFightL2.png",77.25,110,true,false);
	public static final Image cpEngPlayerR = new Image("images/player/CPPlayerR.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkR = new Image("images/player/CPPlayerRWalk.png",77.25,110,true,false); 
	public static final Image cpEngPlayerCryR = new Image("images/player/CPPlayerRCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerWalkCryR = new Image("images/player/CPPlayerRWalkCry.png",77.25,110,true,false);
	public static final Image cpEngPlayerFightR1 = new Image("images/player/CPPlayerFightR1.png",77.25,110,true,false);
	public static final Image cpEngPlayerFightR2 = new Image("images/player/CPPlayerFightR2.png",77.25,110,true,false);
	
	
	public static final Image redPotionItem = new Image("images/item/redpotion.png");
	public static final Image bluePotionItem = new Image("images/item/bluepotion.png");
	
	public static final Image[] normalAttackSkill = new Image[10];
	
	public static final Image[] levelUpEffect = new Image[21];
	
	static {
		for (int i=0; i<normalAttackSkill.length; i++) {
			normalAttackSkill[i] = new Image("images/effect/attack/"+i+".png");
		}
		for (int i=0; i<levelUpEffect.length; i++) {
			levelUpEffect[i] = new Image("images/effect/level_up/"+i+".png");
		}
	}
	
}
