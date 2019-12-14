package game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
	private Door north, south, east, west, exit;
	private boolean switch1, switch2, switch3, switch4, exitOpen;
	private ArrayList<ToggleSwitch> switches = new ArrayList<ToggleSwitch>();
	private ArrayList<Item> items = new ArrayList<Item>();

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
			this.exit = null;
			items.add(new Item("Speed"));
			break;
		case 1:
			this.north = null;
			this.east = null;
			this.west = null;
			this.south = new Door(true, true, true, true);
			this.exit = null;
			items.add(new Item("Key"));
			break;
		case 2:
			this.north = new Door(true, true, true, true);
			this.east = new Door(false, false, true, true);
			this.west = null;
			this.south = null;
			this.exit = null;
			break;
		case 3:
			this.north = null;
			this.east = new Door(true, false, false, true);
			this.west = new Door(true, true, true, false);
			this.south = new Door(false, true, true, true);
			this.exit = null;
			break;
		case 4:
			this.north = new Door(false, true, true, true);
			this.east = null;
			this.west = null;
			this.south = new Door(false, true, false, true);
			this.exit = null;
			break;
		case 5:
			this.north = new Door(false, true, false, true);
			this.east = null;
			this.west = new Door(false, false, true, true);
			this.south = null;
			this.exit = null;
			break;
		case 6:
			this.north = null;
			this.east = null;
			this.west = new Door(true, false, false, true);
			this.south = new Door(false, false, false, true);
			this.exit = null;
			break;
		case 7:
			this.north = new Door(false, false, false, true);
			this.east = null;
			this.west = null;
			this.south = new Door(true, false, true, true);
			this.exit = null;
			break;
		case 8:
			this.north = new Door(true, false, true, true);
			this.east = null;
			this.west = null;
			this.south = null;
			exitOpen = false;
			this.exit = new Door();
			break;
		case 9:
			this.north = null;
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
	
	public boolean getExitOpen() {
		return exitOpen;
	}

	public void render(Graphics g) {
		if(north == null && east == null && west == null && south == null) {
			g.setColor(Color.BLACK);
			g.drawString("CONGRATULATIONS", 225, 225);
			g.drawString("You Have Escaped", 225, 235);
		}
		for (int i = 0; i < switches.size(); i++) {
			if(north != null || east != null|| west != null || south != null)
				switches.get(i).render(g);
		}
		
		for(int i = 0; i < items.size(); i++) {
			items.get(i).render(g);
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
		
		if(exit != null) {
			g.setColor(Color.GRAY);
			g.fillRect(225, 225, 50, 50);
			g.setColor(Color.RED);
			g.drawString("E X I T", 234, 225);
			g.setColor(exitOpen ? Color.GREEN : Color.RED);
			g.fillRect(230, 230, 40, 40);
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

	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void removeItem(int i) {
		if (items.get(i).getName().equals("Key")) {
			exitOpen = true;
		}
		items.remove(i);
	}
	
	public void addItem(Item item) {
		if(item.getName().equals("Key")) {
			exitOpen = false;
		}
		items.add(item);
	}
}
