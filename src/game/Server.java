package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import canvas.AdventureServer;
import canvas.ConnectionEvent;
import canvas.ConnectionListener;
import canvas.TechAdventureServerDemo;
import canvas.UnknownConnectionException;


public class Server implements ConnectionListener{
	AdventureServer adventureServer = null;
	private HashMap< Long, Player > playerData = new HashMap<> ( );
	private Encoder encoder = new Encoder();
	public Server ( ) {
		adventureServer = new AdventureServer ( );
		adventureServer.setOnTransmission ( this );
	}

	public void start( int port ) {
		adventureServer.startServer ( port );
	}

	@Override
	public void handle ( ConnectionEvent e ) {
		System.out.println( "EVENT RECEIVED - YOU MUST PARSE THE DATA AND RESPOND APPROPRIATELY");
		System.out.println( String.format ( "connectionId=%d, data=%s", e.getConnectionID (), e.getData() ));
		System.out.println("b4 try");
		try {
			System.out.println("RX");
			switch ( e.getCode ( ) ) {
				case CONNECTION_ESTABLISHED:
					playerData.put(e.getConnectionID(), new Player("player"));
					// What do you do when the connection is established?
					break;
				case TRANSMISSION_RECEIVED:
					//adventureServer.sendMessage ( e.getConnectionID ( ), String.format (
							  //"MESSAGE RECEIVED: connectionId=%d, data=%s", e.getConnectionID ( ), e.getData ( ) ) );
					playerData.get(e.getConnectionID()).update(e.getData());
					
					
					for(int i = 0; i < getPlayerIDs().size(); i++) {
					adventureServer.sendMessage(getPlayerIDs().get(i), encoder.encodeObj(getPlayerData()));
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
		} catch ( UnknownConnectionException unknownConnectionException ) {
			unknownConnectionException.printStackTrace ( );
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
