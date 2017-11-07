package model.monster;

public class Boss extends Monster {
	
	public Boss() {
		super("Boss",1,100,100,5,5);
	}
	
	public Boss(String name,int level,int hp,int mp,int attack,int defense) {
		super(name,level,hp,mp,attack,defense);
	}

}
