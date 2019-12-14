package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class Item implements Serializable{
	private String name;
	private Rectangle image;
	private Color color;
	private int posX, posY, width, height;
	
	
	public Item(String name) {
		this.name = name;
		switch(name) {
		case "Key":{
			posX = 225;
			posY = 225;
			width = 16;
			height = 4;
			color = Color.YELLOW;
		}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawRect(posX, posY, width, height);
		g.drawString(name, posX, posY-10);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, width, height);
	}
}
