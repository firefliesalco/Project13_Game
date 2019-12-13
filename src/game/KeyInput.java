package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Game game;
	public KeyInput(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		//System.out.println("1");
		game.setKeyEvent(e, true);
	}
	
	public void keyReleased(KeyEvent e) {
		game.setKeyEvent(e, false);
	}
}
