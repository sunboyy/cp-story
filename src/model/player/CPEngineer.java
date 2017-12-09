package model.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.Images;
import javafx.scene.image.Image;
import model.map.Map;
import skill.PowerOfJoe;
import skill.PowerUp;
import skill.SharpSpirit;
import skill.MultiKill;

public class CPEngineer extends Player {
	
	private List<Image> imgWalkDead,imgFightDead;
	
	public CPEngineer(String name, Map map) {
		this(name, map, 0, 0);
	}
	
	public CPEngineer(String name, Map map, double x, double y) {
		super(name, Images.cpEngPlayerL,Images.cpEngPlayerR
				, new ArrayList<>(Arrays.asList(Images.cpEngPlayerWalkL, Images.cpEngPlayerWalkR))
				, new ArrayList<>(Arrays.asList(Images.cpEngPlayerCryL, Images.cpEngPlayerCryR))
				, new ArrayList<>(Arrays.asList(Images.cpEngPlayerWalkCryL, Images.cpEngPlayerWalkCryR))
				, new ArrayList<>(Arrays.asList(Images.cpEngPlayerFightL1, Images.cpEngPlayerFightL2,
						Images.cpEngPlayerFightR1, Images.cpEngPlayerFightR2))
				, map, x, y);
		imgWalkDead = new ArrayList<>(Arrays.asList(Images.cpEngPlayerDeadL,Images.cpEngPlayerDeadR));
		imgFightDead = new ArrayList<>(Arrays.asList(Images.cpEngPlayerDeadFightL,Images.cpEngPlayerDeadFightR));
		skills.add(new MultiKill());
		skills.add(new PowerUp());
		skills.add(new PowerOfJoe());
		skills.add(new SharpSpirit());
	}
	
	@Override
	public void setMove(int direction) {
		if(isDead()) {
			setFacing(direction, imgWalkDead.get((direction+1)/2));
		} else if(isCrying()) {
			if(getWalkTick()%10<5 && isWalking()) {
				setFacing(direction, imgWalkAndCry.get((direction+1)/2));
			} else {
				setFacing(direction, imgCrying.get((direction+1)/2));
			}
		} else if (isWalking()) {
			if(getWalkTick()%10<5) {
				setFacing(direction,imgWalking.get((direction+1)/2));
			} else {
				setFacing(direction);
			}
		} else {
			setFacing(direction);
		}
		if(!canAttack()) {	// is attacking now !
			if(isDead()) {
				setFacing(this.facing, imgFightDead.get((this.facing+1)/2));
			} else {
				setFacing(this.facing,imgAttack.get(this.facing+1));
			}
		} 
	}
	
}
