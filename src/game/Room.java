package game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
	private Door north, south, east, west, exit;
	private boolean switch1, switch2, switch3, switch4, exitOpen, key, food, water, seal;
	private int roomNum;
	private ArrayList<ToggleSwitch> switches = new ArrayList<ToggleSwitch>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private String story = "";
	public Room(int index) {
		setRoom(index);
		key = false;
		water = false;
		food = false;
		switches.add(new ToggleSwitch(50, 50));
		switches.add(new ToggleSwitch(350, 50));
		switches.add(new ToggleSwitch(50, 350));
		switches.add(new ToggleSwitch(350, 350));
	}

	public void setRoom(int index) {
		roomNum = index;
		seal = true;
		switch (index) {
		case 0:
			this.north = null;
			this.east = new Door(true, true, true, false);
			this.west = null;
			this.south = null;
			items.add(new Item("ExoBoots"));
			story = "Sparks fly and the lights go out. The control deck is dead all power to the engines has been severed. The only way out it into the lobby where you can already hear the screams of other crewmates perishing. The door is sealed shut to contain the damage you must enact the emergency override actions to open it. You must pull the first three levers to active the emergency override.";
			break;
		case 1:
			this.north = null;
			this.east = null;
			this.west = null;
			this.south = new Door(true, true, true, true);
			this.exit = null;
			items.add(new Item("Key"));
			story = "The room has been ransacked  and turned upside down. On the floor laying open is an old book it appears to be ancient and unused. Upon flipping the book over you read the title “Big Java Late Objects”. Through looking around for a few minutes you are able to find the captain's key for the escape shuttle. You have obtained the key now you must make it to the escape shuttle.";
			break;
		case 2:
			this.north = new Door(true, true, true, true);
			this.east = new Door(false, false, true, true);
			this.west = null;
			this.south = null;
			this.exit = null;
			items.add(new Item("Water"));
			items.add(new Item("Food"));
			story = "The door slides shut behind you, you can breathe again it appears that the canteen has remained sealed. Food and water are strewn across the room, they may be useful for the long journey to the nearest planet. The two main exits to the west and south have been blocked by rubble and  debris the only door left is to the north. As the red lights flash and sirens blare you know there isn't much time left before the ship is shredded to pieces.";
			break;
		case 3:
			this.north = null;
			this.east = new Door(true, false, false, true);
			this.west = new Door(true, true, true, false);
			this.south = new Door(false, true, true, true);
			this.exit = null;
			items.add(new Item("ExoBoots"));
			story = "As the walls fall in around you, you enter the lobby, where people would be cleared for access to the control room. The air grows cold as the furnaces are no longer running. On the feet of the receptionist who has been crushed by rubble you see a pair of ExoBoots. They could be useful to get off the ship quickly, but is it worth stealing from the dead? You see two doors on to your right and one in front of you each requires a special override action to open.";
			break;
		case 4:
			this.north = new Door(false, true, true, true);
			this.east = null;
			this.west = null;
			this.south = new Door(false, true, false, true);
			this.exit = null;
			story ="This room served as a hallway at one point, but now, it just serves as another barrier for you and the damage to go through. A door lies ahead only one way to find out what is left ahead.";
			break;
		case 5:
			this.north = new Door(false, true, false, true);
			this.east = null;
			this.west = new Door(true, false, true, true);
			this.south = null;
			this.exit = null;
			seal = false;
			story ="As the door slides open you can feel yourself pulled into the next room. The large observation window is cracked and the vacuum of space is pulling the oxygen out of the room. Looking out you can see the wreckage of the ship floating in the void. Equipment, chairs, rubble, people all frozen in the expanse of space. The air leaves the room and you will suffocate unless you have some emergency sealant. There is one door to the west you should open it before your oxygen runs out.";
			break;
		case 6:
			this.north = null;
			this.east = null;
			this.west = new Door(true, false, false, true);
			this.south = new Door(false, false, false, true);
			this.exit = null;
			story = "You enter the room and the elevator which once could take you to any section of the ship is now just a hole in the floor. Screams echo up from the lower decks only one exit to the south remains. If only you could remember the combination.";
			break;
		case 7:
			this.north = new Door(false, false, false, true);
			this.east = null;
			this.west = null;
			this.south = new Door(true, true, false, true);
			this.exit = null;
			items.add(new Item("O2"));
			items.add(new Item("Sealant"));
			story = "This room is littered with equipment mostly picked clean of anything useful. You find a portable oxygen tank along the rest of rubble and supplies. Tucked away in a corner is some emergency sealant for small air leaks or openings in the hull.";
			break;
		case 8:
			this.north = new Door(true, false, true, true);
			this.east = null;
			this.west = null;
			this.south = null;
			exitOpen = false;
			this.exit = new Door();
			story = "Most of the escape pods have been taken there is only one left meant for the captain. You need the captains code to enter the escape pod. The oxygen feels thin you may not have much time left.";
			break;
		case 9:
			this.north = null;
			this.east = null;
			this.west = null;
			this.south = null;
			story = "As you enter the escape pod and punch in the coordinates for anywhere but here you inhale getting a full lungfull of oxygen. The escape pod pushes away from the ship you see the engines explode in a bright blue and orange before quickly dissipating in the expanse of space. ";
			break;
		case 10:
			this.north = null;
			this.east = null;
			this.west = new Door(false, false, false, false);
			this.south = null;
			story = "You must have access codes, food and water to be able to leave.";
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

	public boolean getExitOpen(int players) {
		if (players == 1 && key) {
			exitOpen = true;
		} else if (players == 2 && key && water)
			exitOpen = true;
		else if (players >= 3 && key && water && food)
			exitOpen = true;
		else
			exitOpen = false;
		return exitOpen;
	}
	
	public boolean getSeal() {
		return seal;
	}
	public void setSeal() {
		seal = true;
	}
	public void render(Graphics g) {
		if(roomNum == 5) {
			g.setColor(Color.RED);
			if(!getSeal())
				g.drawString("You must Seal the Room!", 200, 425);
			else g.drawString("Room Sealed!", 200, 435);
		}
		if (roomNum == 10) {
			g.setColor(Color.BLACK);
			g.drawString("You have Died", 200, 225);
			g.drawString("Fortunately you were revived", 200, 235);
			g.drawString("You must seal off that room!", 200, 245);
		}
		for (int i = 0; i < switches.size(); i++) {
			if (north != null || east != null || west != null || south != null)
				switches.get(i).render(g);
		}

		for (int i = 0; i < items.size(); i++) {
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

		if (exit != null) {
			g.setColor(Color.GRAY);
			g.fillRect(225, 225, 50, 50);
			g.setColor(Color.RED);
			g.drawString("E X I T", 234, 225);
			g.setColor(exitOpen ? Color.GREEN : Color.RED);
			g.fillRect(230, 230, 40, 40);
		}
		
		String[] words = story.split(" ");
		List<String> combines = new ArrayList<>();
		combines.add(words[0]);
		final int MAX_LINE_SIZE = 40;
		for(int i = 1; i < words.length; i++) {
			if(combines.get(combines.size() - 1).length() + words[i].length() > MAX_LINE_SIZE) {
				combines.add(words[i]);
			} else {
				combines.set(combines.size()-1,combines.get(combines.size() - 1) + " " + words[i]); 
			}
		}
		g.setColor(Color.black);
		for(int i = 0; i < combines.size(); i++) {
			g.drawString(combines.get(i), 120, 120 + 20 * i);
		}

	}

	public ArrayList<ToggleSwitch> getSwitches() {
		return switches;
	}

	public boolean northOpen() {
		if (north == null)
			return false;
		return north.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}

	public boolean eastOpen() {
		if (east == null)
			return false;
		return east.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}

	public boolean westOpen() {
		if (west == null)
			return false;
		return west.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}

	public boolean southOpen() {
		if (south == null)
			return false;
		return south.isOpen(switches.get(0).getState(), switches.get(1).getState(), switches.get(2).getState(),
				switches.get(3).getState());
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void removeItem(int i) {
		if (items.get(i).getName().equals("Key")) {
			key = true;
		}
		if (items.get(i).getName().equals("Water")) {
			water = true;
		}
		if (items.get(i).getName().equals("Food")) {
			food = true;
		}
		items.remove(i);
	}

	public void addItem(Item item) {
		if (item.getName().equals("Water")) {
			water = false;
		}
		if (item.getName().equals("Food")) {
			food = false;
		}
		if (item.getName().equals("Key")) {
			key = false;
		}

		items.add(item);
	}
}
