package game;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import canvas.AdventureServer;
import canvas.ConnectionEvent;
import canvas.ConnectionListener;
import canvas.TechAdventureServerDemo;
import canvas.UnknownConnectionException;


public class Server implements ConnectionListener{
	AdventureServer adventureServer = null;
	private HashMap< Long, Player > playerData = new HashMap<> ( );
	private Encoder encoder = new Encoder();
	private Level level = new Level();
	private boolean running = true;
	
	public Server ( ) {
		adventureServer = new AdventureServer ( );
		adventureServer.setOnTransmission ( this );
		Thread loop = new Thread() {
			public void run() {
				Thread thread = new Thread();
				final long targetTPS = 20;
	
				// Calculates the number of nanoseconds between each frame and tick.
				final long targetNSPT = 1000000000 / targetTPS;
	
				// Game Loop
	
				long lastTime = System.nanoTime();
				long tickTime = 0; // Nanoseconds since last tick
		;
					while (running) {
						long currentTime = System.nanoTime();
						long deltaTime = currentTime - lastTime; // Change in time from last loop cycle
						tickTime += deltaTime; // Increase tick counter
	
						// Catch up on ticks
						while (tickTime >= targetNSPT) {
							tickTime -= targetNSPT;
							try {
								tick();
							} catch (UnknownConnectionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	
						}
	
						// Update time tracker
						lastTime = currentTime;
					}
			}
		};
		loop.start();
	}

	public void start( int port ) {
		adventureServer.startServer ( port );
	}

	
	
	public void tick() throws UnknownConnectionException {
		for(Long l : playerData.keySet()) {
			Player p = playerData.get(l);
			String keyInput = "";
			if (p.getKeyHeld(KeyEvent.VK_W)) {
				keyInput += "w";
			}
			if (p.getKeyHeld(KeyEvent.VK_A)) {
				keyInput += "a";
			}
			if (p.getKeyHeld(KeyEvent.VK_S)) {
				keyInput += "s";
			}
			if (p.getKeyHeld(KeyEvent.VK_D)) {
				keyInput += "d";
			}
			if (p.getKeyHeld(KeyEvent.VK_E)) {
				keyInput += "e";
			}
			
			for(char c : keyInput.toCharArray()) {
				p.update(c + "", playerData.values().stream().collect(Collectors.toList()), level);
			}
			
			for (int i = 0; i < getPlayerIDs().size(); i++) {
				adventureServer.sendMessage(l, "update_" + encoder.encodeObj(getPlayerData()));
				adventureServer.sendMessage(l, "level_" + encoder.encodeObj(level));
			}
		}
		
		
	}
	
	@Override
	public void handle ( ConnectionEvent e ) {
		switch ( e.getCode ( ) ) {
			case CONNECTION_ESTABLISHED:
				playerData.put(e.getConnectionID(), new Player("player"));
				// What do you do when the connection is established?
				break;
			case TRANSMISSION_RECEIVED:
				//adventureServer.sendMessage ( e.getConnectionID ( ), String.format (
						  //"MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID ( ), e.getData ( ) ) );
				
				String[] arr = e.getData().split("_");
				switch(arr[0]) {
					case ("key"):{
						int code = Integer.parseInt(arr[1]);
						boolean enabled = Boolean.parseBoolean(arr[2]);
						playerData.get(e.getConnectionID()).setKeyHeld(code, enabled);
					}
				}

				
				
				// BEWARE - if you keep this, any user can shutdown the server
				if ( e.getData ( ).equals ( "SHUTDOWN" ) ) {
					adventureServer.stopServer ( );
				}
				break;
			case CONNECTION_TERMINATED:
				playerData.remove(e.getConnectionID());
				// Cleanup when the connection is terminated.
				break;
			default:
				// What is a reasonable default?
		}
	}

	
	public ArrayList<Player> getPlayerData() {
		return new ArrayList<Player>(playerData.values());
	}
	
	public ArrayList<Long> getPlayerIDs(){
		return new ArrayList<Long> (playerData.keySet());
	}
	
	
	public static void main ( String[] args ) {
		Server server = new Server();
		server.start ( 2112 );
	}
}
