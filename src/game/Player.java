package game;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
	private String name;
	private int posX, posY, roomX, roomY;
	private ArrayList<Object> inventory = new ArrayList<Object>();
	private boolean spawnCollision = false;
	private boolean[] keysHeld = new boolean[255];

	public Player(String name) {
		this.name = name;
		posX = 100;
		posY = 100;
		roomX = 0;
		roomY = 0;
	}

	public void update(String s, List<Player> playerData, Level level) {
		switch (s) {
		case "s": {
			posY += 3;
			if (intersect(playerData))
				posY -= 3;
			if (posY > 500 - 64) {
				if (level.getRooms()[roomY][roomX].southOpen()) {
					posY = 80;
					roomY++;
					
				} else
					posY -= 3;
			}
			break;
		}

		case "d": {
			posX += 3;
			if (intersect(playerData))
				posX -= 3;
			if (posX > 500 - 48) {
				if (level.getRooms()[roomY][roomX].eastOpen()) {
					posX = 80;
					roomX++;
					
				} else
					posX -= 3;
			}
			break;
		}

		case "w": {
			posY -= 3;
			if (intersect(playerData))
				posY += 3;
			if (posY < 16) {
				if (level.getRooms()[roomY][roomX].northOpen()) {
					posY = 464;
					roomY--;
					
				} else
					posY -= 3;
			}
			break;
		}

		case "a": {
			posX -= 3;
			if (intersect(playerData))
				posX += 3;
			if (posX < 16) {
				if (level.getRooms()[roomY][roomX].westOpen()) {
					posX = 464;
					roomX--;
					
				} else
					posX += 3;
			}
			break;
		}

		case "e":
			ArrayList<ToggleSwitch> switches = level.getRooms()[roomY][roomX].getSwitches();
			for (int i = 0; i < switches.size(); i++) {
				if (this.getBounds().intersects(switches.get(i).getBounds()))
					switches.get(i).setState();
			}
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

	public boolean getKeyHeld(int key) {
		return keysHeld[key];
	}

	public void setKeyHeld(int key, boolean val) {
		keysHeld[key] = val;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 32, 32);
	}

	public boolean intersect(List<Player> playerData) {
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
