package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class ToggleSwitch implements Serializable{
	private boolean state;
	private int posX, posY;
	public ToggleSwitch(int posX, int posY) {
		this.state = false;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void render(Graphics g) {
		g.setColor(state ? Color.GREEN:Color.RED);
		g.fillRect(posX, posY, 8, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 8, 16);
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setState() {
		state = !state;
	}
}
