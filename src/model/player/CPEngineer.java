package model.player;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import model.map.Map;
import skill.PowerUp;
import test.TestSkill;

public class CPEngineer extends Player {
	
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
		skills.add(new TestSkill());
		skills.add(new PowerUp());
	}
	
}
