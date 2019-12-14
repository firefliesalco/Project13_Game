package game;

import java.io.Serializable;

public class Door implements Serializable{
	boolean sw1, sw2, sw3, sw4, exit;
	//regular door
	public Door(boolean sw1, boolean sw2, boolean sw3, boolean sw4) {
		this.exit = false;
		this.sw1 = sw1;
		this.sw2 = sw2;
		this.sw3 = sw3;
		this.sw4 = sw4;
	}
	
	//exit
	public Door() {
		this.exit = true;
		this.sw1 = true;
		this.sw2 = true;
		this.sw3 = true;
		this.sw4 = true;
		
	}
	
	public boolean isOpen(boolean switch1, boolean switch2, boolean switch3, boolean switch4) {
		return(switch1 == this.sw1 && switch2 == this.sw2 && switch3 == this.sw3 && switch4 == sw4);
	}
	
	public boolean isExitOpen(boolean key) {
		return (this.exit == key);
	}
	
	public boolean hasExit() {
		return exit;
	}
}
