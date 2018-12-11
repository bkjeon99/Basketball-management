package basketball_management;

public class Player {
	public int foul;
	public int score;
	public int number;
	public int Team;
	
	public Player(int number) {
		this.number = number;
	}
	
	public int returnNumber () {
		return number;
	}
	
	public void changefoul (int a) {
		foul = foul + a;
	}
	public void changescore (int a) {
		score = score + a;
	}
}
