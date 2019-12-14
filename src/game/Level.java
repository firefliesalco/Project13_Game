package game;

import java.awt.Graphics;
import java.io.Serializable;

public class Level implements Serializable{
	private Room rooms[][] = new Room[3][3];
	
	public Level() {
		rooms[0][0] = new Room(0);
		rooms[1][0] = new Room(1);
		rooms[2][0] = new Room(2);
		rooms[0][1] = new Room(3);
		rooms[1][1] = new Room(4);
		rooms[2][1] = new Room(5);
		rooms[0][2] = new Room(6);
		rooms[1][2] = new Room(7);
		rooms[2][2] = new Room(8);
	}
	
	public void render(Graphics g, int roomX, int roomY) {
		rooms[roomY][roomX].render(g);
	}
	
	public Room[][] getRooms(){
		return rooms;
	}
}
