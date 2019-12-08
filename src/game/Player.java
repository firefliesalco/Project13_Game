package game;

public class Player {
	private String name;
	private int posX,posY;
	
	public Player (String name) {
		this.name = name;
	}
	
	public void update(String s) {
		if(s.equals("s")) {
			this.posY--;
		}
	}
	
}
