package game;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private String name;
	private int posX, posY, roomX, roomY;
	private ArrayList<Object> inventory = new ArrayList<Object>();
	private boolean spawnCollision = false;;
	public Player(String name) {
		this.name = name;
		posX = 100;
		posY = 100;
	}

	public void update(String s, ArrayList<Player> playerData) {
		switch (s) {
		case "s":
			posY += 3;
			if (intersect(playerData) || posY > 500 - 64)
				posY -= 3;
			break;
		case "d":
			posX += 3;
			if (intersect(playerData) || posX > 500 - 48)
				posX -= 3;
			break;
		case "w":
			posY -= 3;
			if (intersect(playerData) || posY < 0)
				posY += 3;
			break;
		case "a":
			posX -= 3;
			if (intersect(playerData) || posX < 0)
				posX += 3;
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
	
	public String getName() {
		return name;
	}
	
	public int getRoomX() {
		return roomX;
	}

	public int getRoomY() {
		return roomY;
	}
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 32, 32);
	}

	public boolean intersect(ArrayList<Player> playerData) {
		for (int i = 0; i < playerData.size(); i++) {
			if (playerData.get(i) != this) {
				if (this.getBounds().intersects(playerData.get(i).getBounds()))
					return (spawnCollision && true);
			}
		}
		spawnCollision = true;
		return false;

	}
}
