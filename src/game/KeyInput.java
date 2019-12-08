package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Game game;
	public KeyInput(Game game) {
		this.game = game;
	}
	
	public void KeyPressed(KeyEvent e) {
		game.setKeyEvent(e);
	}
	
	public void KeyReleased(KeyEvent e) {
		game.setKeyEvent(null);
	}
}
