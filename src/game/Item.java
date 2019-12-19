package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Item implements Serializable{
	private String name;
	private Color color;
	private int posX, posY, width, height;
	private BufferedImage image;
	
	public Item(String name) {
		this.name = name;
		//this.image = getImage(name);
		//width = image.getWidth();
		//height = image.getHeight();
		switch(name) {
		case "Key":{
			posX = 125;
			posY = 325;
			width = 16;
			height = 4;
			color = Color.YELLOW;
			break;
		}
		case "ExoBoots" : {
			posX = 75;
			posY = 225;
			width = 12;
			height = 8;
			color = Color.BLACK;
			break;
		}
		case "Food":{
			posX = 300;
			posY = 225;
			width = 8;
			height = 8;
			color = Color.ORANGE;
			break;
		}
		case "Water": {
			posX = 75;
			posY = 225;
			width = 8;
			height = 16;
			color = Color.CYAN;
			break;
		}
		
		case "Sealant":{
			posX = 75;
			posY = 225;
			width = 8;
			height = 16;
			color = Color.GREEN;
			break;
		}
		
		case "O2":{
			posX = 300;
			posY = 225;
			width = 8;
			height = 16;
			color = Color.CYAN;
			break;
		}
			
		}
	}
	
	public BufferedImage getImage(String name) {
		try {
			return ImageIO.read(new FileInputStream("resources/" + name + ".png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getName() {
		return name;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(posX, posY, width, height);
		g.drawString(name, posX, posY-10);
		g.setColor(color);
		g.fillRect(posX, posY, width, height);
		
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, width, height);
	}
}
