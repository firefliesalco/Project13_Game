package game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Room implements Serializable{
	private Door north, south, east, west;
	private boolean switch1, switch2, switch3, switch4;
	public Room(int index) {
		setRoom(index);
		this.switch1 = false;
		this.switch2 = false;
		this.switch3 = false;
		this.switch4 = false;
	}
	
	public void setRoom(int index) {
		switch(index) {
		case 0:
			this.north = null;
			this.east = new Door(true, true, true, false);
			this.west = null;
			this.south = null;
			break;
		case 1:
			this.north = null;
			this.east = null;
			this.west = null;
			this.south = new Door(true, true, true, true);
			break;
		case 2:
			this.north = null;
			this.east = new Door(false, false, true, true);
			this.west = null;
			this.south = null;
			break;
		case 3:
			this.north = null;
			this.east = new Door(true, false, false, true);
			this.west = new Door(true, true, true, false);
			this.south = new Door(false, true, true, true);
			break;
		case 4:
			this.north = new Door(false, true, true, true);
			this.east = null;
			this.west = null;
			this.south = new Door(false, true, false, true);
			break;
		case 5:
			this.north = new Door(false, true, false, true);
			this.east = null;
			this.west = new Door(false, false, true, true);
			this.south = null;
			break;
		case 6:
			this.north = null;
			this.east = null;
			this.west = new Door(true, false, false, true);
			this.south = new Door(false, false, false, true);
			break;
		case 7:
			this.north = new Door(false, false, false, true);
			this.east = null;
			this.west = null;
			this.south = new Door(true, false, true, true);
			break;
		case 8:
			this.north = new Door(true, false, true, true);
			this.east = null;
			this.west = null;
			this.south = null;
			break;
		}
	}
	
	public void setSwitch1() {
		this.switch1 = !this.switch1;
	}
	
	public boolean getSwitch1() {
		return this.switch1;
	}
	
	public void setSwitch2() {
		this.switch2 = !this.switch2;
	}
	
	public boolean getSwitch2() {
		return this.switch2;
	}
	
	public void setSwitch3() {
		this.switch3 = !this.switch3;
	}
	
	public boolean getSwitch3() {
		return this.switch3;
	}
	
	public void setSwitch4() {
		this.switch4 = !this.switch4;
	}
	
	public boolean getSwitch4() {
		return this.switch4;
	}
	
	public void render(Graphics g) {
		g.setColor(switch1 ? Color.GREEN:Color.RED);
		g.fillRect(50, 50, 8, 16);
		g.setColor(switch2 ? Color.GREEN:Color.RED);
		g.fillRect(350, 50, 8, 16);
		g.setColor(switch3 ? Color.GREEN:Color.RED);
		g.fillRect(50, 350, 8, 16);
		g.setColor(switch4 ? Color.GREEN:Color.RED);
		g.fillRect(350, 350, 8, 16);
	}
}
