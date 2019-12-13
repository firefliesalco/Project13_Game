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
		game.sendMessage("key_" + e.getKeyCode() + "_true");
	}
	
	public void keyReleased(KeyEvent e) {
		game.sendMessage("key_" + e.getKeyCode() + "_false");
	}
}
