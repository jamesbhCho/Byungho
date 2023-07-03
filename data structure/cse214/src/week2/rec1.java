package week2;

public class rec1 {
	
}

abstract class gameCharacter{//class that contains abstract method
	
	private String ability;
	private boolean isDead;
	private int hp;
	
	public abstract int getHealthPoint();
	public abstract boolean move(int x, int y);
	
	//constructor
	public gameCharacter(String a){
		this.ability = a;
		this.isDead = false;
		this.hp = 100;
	}
	
	public String getAbility() {
		return ability;
	}
	
	public void deleteChar() {
		//
	}
	
	public boolean isDead() {
		return isDead;
	}
}
