package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	private Game game;
	private boolean typing = false;
	public String msg = "";
	public KeyInput(Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		//System.out.println("1");
		char c = e.getKeyChar();
		int k = e.getKeyCode();
		if(!typing) {
			if(k == KeyEvent.VK_ENTER) {
				typing = true;
				return;
			}
			game.sendMessage("key_" + k + "_true");
		} else {
			
			if(k == KeyEvent.VK_BACK_SPACE) {
				if(msg.length() > 0)
					msg = msg.substring(0, msg.length() - 1);
			}
			else if(k == KeyEvent.VK_ESCAPE) {
				typing = false;
				msg = "";
			}
			else if(k == KeyEvent.VK_ENTER) {
				typing = false;
				if(msg.length() > 0)
					game.sendMessage("chat_" + msg);
				msg = "";
			}
			else if(k != KeyEvent.VK_SHIFT && k != KeyEvent.VK_CAPS_LOCK && k != KeyEvent.VK_TAB && k != KeyEvent.VK_ALT && k != KeyEvent.CTRL_DOWN_MASK){
				msg += c;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(!typing)
			game.sendMessage("key_" + e.getKeyCode() + "_false");
	}
}
