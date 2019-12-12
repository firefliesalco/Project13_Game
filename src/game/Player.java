package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	private String name;
	private int posX,posY,roomX,roomY;
	private ArrayList<Object> inventory = new ArrayList<Object>();
	
	public Player (String name) {
		this.name = name;
		posX = 100;
		posY = 100;
	}
	
	public void update(String s) {
		switch(s) {
			case "s":
				posY++;
				break;	
			case "d":
				posX++;
				break;	
			case "w":
				posY--;
				break;	
			case "a":
				posX--;
				break;
			default:		
		}
		
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
}
