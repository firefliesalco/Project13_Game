package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends Canvas {

	private JFrame frame;
	private final static int WIDTH = 500, HEIGHT = 500, SEND_DELAY = 5;
	private boolean running = true;
	private Player player;
	private ArrayList<Player> playerDataList = new ArrayList<Player>();
	private HashMap< Long, Player > playerData;
	private Encoder encoder = new Encoder();
	private Level level = new Level();
	private long connectionID;
	private BufferedReader fromServer;
	private PrintWriter toServer;
	private int tickCount = 0;
	private int tickCounter = 0;
	private boolean[] keysHeld = new boolean[255];

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		frame = new JFrame("Assignment 13");
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setEnabled(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		this.addKeyListener(new KeyInput(this));
		this.player = new Player("game Player");
		run();
	}

	@SuppressWarnings("unchecked")
	public void run() {

		// Change these for your target framerate and tickspeed.
		final long targetFPS = 30;
		final long targetTPS = 20;

		// Calculates the number of nanoseconds between each frame and tick.
		final long targetNSPF = 1000000000 / targetFPS;
		final long targetNSPT = 1000000000 / targetTPS;

		// Game Loop

		long lastTime = System.nanoTime();
		long renderTime = 0; // Nanoseconds since last render
		long tickTime = 0; // Nanoseconds since last tick

		try (Socket server = new Socket("141.219.226.239", Integer.valueOf(2112))) {
			System.out.println("Connected to AdventureServer host " + server.getInetAddress());
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			toServer = new PrintWriter(server.getOutputStream(), true);
			while (running) {
				long currentTime = System.nanoTime();
				long deltaTime = currentTime - lastTime; // Change in time from last loop cycle
				renderTime += deltaTime; // Increase render counter
				tickTime += deltaTime; // Increase tick counter

				// Catch up on ticks
				while (tickTime >= targetNSPT) {
					tickTime -= targetNSPT;
					tick();

				}

				// Only render once, even if there's a delay or something
				if (renderTime > targetNSPF) {
					render();
					renderTime = 0;
				}

				// Update time tracker
				lastTime = currentTime;
			}
			fromServer.close();
			toServer.close();
			System.out.println("Game Loop Stopped, Exiting Game"); // :(
			System.exit(0);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() throws IOException {
		Packet serverPacket = null;
		if(tickCounter >= 5) {
			tickCounter = 0;
			Packet packet = new Packet(this.player);
			toServer.println(encoder.encodeObj(packet));
			
			while (fromServer.ready()) {    
	            String s = fromServer.readLine();    
	            String[] arr = s.split("_");    
	            String data = arr[1];    
	            switch(arr[0]) {    
	                case "ID":{
	                	Packet cIDpacket = (Packet) encoder.decodeObj(data);
	                    connectionID = cIDpacket.getCID(); 
	                    break;
	                }
	                case "Packet":{
	                	serverPacket = (Packet) encoder.decodeObj(data);
	                	break;
	                }                
	            }    
	        }		
			if(serverPacket != null) {
				level = serverPacket.getLevel();
				playerData = serverPacket.getPlayerData();
				playerDataList = new ArrayList<Player>(playerData.values());
			}
		}else tickCounter++;
		
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// Draw stuff here

		g.setColor(Color.PINK);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (player != null && level != null)
			level.render(g, player.getRoomX(), player.getRoomY());
		// g.setColor(Color.black);
		// g.drawString("Jacob was here", 200, 200);
		g.setColor(Color.BLUE);
		for (int i = 0; i < playerDataList.size(); i++) {
			Player p = playerDataList.get(i);
			g.drawString(p.getName() + i + 1, p.getPosX(), p.getPosY() - 10);
			g.fillRect(p.getPosX(), p.getPosY(), 32, 32);
		}
		// Don't draw stuff after here
		g.dispose();
		bs.show();

	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Player> getPlayerDataList(){
		return playerDataList;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setKeyEvent(KeyEvent e, boolean isPressed) {
		// if (keyEvent != null)
		// System.out.println(e.getKeyChar());
		this.keysHeld[e.getKeyCode()] = isPressed;
	}

}
