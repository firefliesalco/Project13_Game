package game;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
	private String name;
	private int posX, posY, roomX, roomY;
	private Item inventory = null;
	private boolean collisionFlag = false;
	private boolean[] keysHeld = new boolean[255];

	public Player(String name) {
		this.name = name;
		posX = 100;
		posY = 100;
		roomX = 0;
		roomY = 0;
	}
	
	public void setName(String s) {
		this.name = s;
	}

	public void update(String s, List<Player> playerData, Level level) {
		if(!level.getRooms()[roomY][roomX].getSeal()) {
			if(!inventory.getName().equals("Sealant")) {
				roomY = 0;
				roomX = 3;
			}		
		}
		switch (s) {
		case "s": {
			posY += 3 + hasSpeed();
			if (intersect(playerData))
				posY -= 3 + hasSpeed();
			if (posY > 500 - 64 - 16) {
				if (level.getRooms()[roomY][roomX].southOpen()) {
					posY = 80;
					roomY++;
					collisionFlag = false;
				} else
					posY -= 3 + hasSpeed();
			}
			if(inventory != null) {
				inventory.setPosX(posX - 8);
				inventory.setPosY(posY + 20);
				}
			break;
		}

		case "d": {
			posX += 3 + hasSpeed();
			if (intersect(playerData))
				posX -= 3 + hasSpeed();
			if (posX > 500 - 48) {
				if (level.getRooms()[roomY][roomX].eastOpen()) {
					posX = 80;
					roomX++;
					collisionFlag = false;
				} else
					posX -= 3 + hasSpeed();
			}
			if(inventory != null) {
				inventory.setPosX(posX - 8);
				inventory.setPosY(posY + 20);
				}
			break;
		}

		case "w": {
			posY -= 3 + hasSpeed();

			if (intersect(playerData))
				posY += 3 + hasSpeed();
			if (posY < 16) {
				if (level.getRooms()[roomY][roomX].northOpen()) {
					posY = 464;
					roomY--;
					collisionFlag = false;
				} else
					posY += 3 + hasSpeed();
			}
			if(inventory != null) {
				inventory.setPosX(posX - 8);
				inventory.setPosY(posY + 20);
				}
			break;
		}

		case "a": {
			posX -= 3 + hasSpeed();
			if (intersect(playerData))
				posX += 3 + hasSpeed();
			if (posX < 16) {
				if (level.getRooms()[roomY][roomX].westOpen()) {
					posX = 464;
					roomX--;
					collisionFlag = false;
				} else
					posX += 3 + hasSpeed();
			}
			if(inventory != null) {
				inventory.setPosX(posX - 8);
				inventory.setPosY(posY + 20);
				}
			break;
		}

		case "e":{
			ArrayList<ToggleSwitch> switches = level.getRooms()[roomY][roomX].getSwitches();
			ArrayList<Item> items = level.getRooms()[roomY][roomX].getItems();
			
			for (int i = 0; i < switches.size(); i++) {
				if (this.getBounds().intersects(switches.get(i).getBounds()))
					switches.get(i).setState();
			}
			for(int i = 0; i < items.size(); i++) {
				if(this.getBounds().intersects(items.get(i).getBounds()) && inventory == null) {
					inventory = items.get(i);
					level.getRooms()[roomY][roomX].removeItem(i);
				}
			}
			
			if(level.getRooms()[roomY][roomX].getExitOpen(playerData.size())) {
				if(posX > 230 && posY > 230 && posX < 270 && posY < 270){
					roomY = 3;
					roomX = 3;
					collisionFlag = false;
				}
			}
			if(!level.getRooms()[roomY][roomX].getSeal()) {
				if(inventory.getName().equals("Sealant")) 
					level.getRooms()[roomY][roomX].setSeal();
					inventory = null;
			}
				
			break;
		}
		
		case "q":{
			if(inventory!=null) {
				inventory.setPosX(posX);
				inventory.setPosY(posY+34);
				level.getRooms()[roomY][roomX].addItem(inventory);
				inventory = null;
			}
			break;
		}
			
		default:
		}

	}

	private int hasSpeed() {
		if(inventory != null) {
			if(inventory.getName().equals("ExoBoots"))
				return 3;
		}
		return 0;
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
	
	public Item getInventory() {
		return inventory;
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
		for (Player p : playerData) { 
			if (p != this && p.getRoomX() == roomX && p.getRoomY() == roomY) {
				if (this.getBounds().intersects(p.getBounds()))
					return (collisionFlag && true);
			}
		}
		collisionFlag = true;
		return false;

	}
}
