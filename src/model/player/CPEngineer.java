package model.player;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Images;
import model.map.Map;
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
<<<<<<< HEAD
				, new ArrayList<>(Arrays.asList(Images.cpEngPlayerFightL1, Images.cpEngPlayerFightL2,
						Images.cpEngPlayerFightR1, Images.cpEngPlayerFightR2))
				, map, x, y, 40, 70);
||||||| merged common ancestors
				, map, x, y, 40, 70);
=======
				, map, x, y);
>>>>>>> 6e5049d2143e7cdeacf709b510dc4a5191bac63b
		skills.add(new TestSkill());
	}
	
}
