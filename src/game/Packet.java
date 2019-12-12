package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Packet implements Serializable {
	private Level level;
	private HashMap<Long, Player> playerData;
	private Player player;
	private Long connectionID;

	// server to client packet
	public Packet(Level level, HashMap<Long, Player> playerData) {
		this.level = level;
		this.playerData = playerData;
	}

	// client to server packet
	public Packet(Player player) {
		this.player = player;
	}

	public Packet(Long connectionID) {

	}

	public Level getLevel() {
		return level;
	}

	public HashMap<Long, Player> getPlayerData() {
		return playerData;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Long getCID() {
		return connectionID;
	}
}
