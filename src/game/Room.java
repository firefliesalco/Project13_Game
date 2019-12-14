package game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
	private Door north, south, east, west;
	private boolean switch1, switch2, switch3, switch4;
	private ArrayList<ToggleSwitch> switches = new ArrayList<ToggleSwitch>();

	public Room(int index) {
		setRoom(index);
		switches.add(new ToggleSwitch(50, 50));
		switches.add(new ToggleSwitch(350, 50));
		switches.add(new ToggleSwitch(50, 350));
		switches.add(new ToggleSwitch(350, 350));
	}

	public void setRoom(int index) {
		switch (index) {
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

		for (int i = 0; i < switches.size(); i++) {
			switches.get(i).render(g);
		}

		if (north != null) {
			g.setColor(north.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
					switches.get(3).getState()) ? Color.GREEN : Color.RED);
			g.fillRect(100, 0, 150, 16);
		}
		if (east != null) {
			g.setColor(east.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
					switches.get(3).getState()) ? Color.GREEN : Color.RED);
			g.fillRect(468, 100, 16, 150);
		}
		if (west != null) {
			g.setColor(west.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
					switches.get(3).getState()) ? Color.GREEN : Color.RED);
			g.fillRect(0, 100, 16, 150);
		}
		if (south != null) {
			g.setColor(south.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
					switches.get(3).getState()) ? Color.GREEN : Color.RED);
			g.fillRect(100, 445, 150, 16);
		}

	}

	public ArrayList<ToggleSwitch> getSwitches() {
		return switches;
	}

	public boolean northOpen() {
		if(north == null) return false;
		return north.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}

	public boolean eastOpen() {
		if(east == null) return false;
		return east.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
					switches.get(3).getState());
	}

	public boolean westOpen() {
		if(west == null) return false;
		return west.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}

	public boolean southOpen() {
		if(south == null) return false;
		return south.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}
}
