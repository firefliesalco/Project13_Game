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


public class Game extends Canvas {

	private JFrame frame;
	private final static int WIDTH = 500, HEIGHT = 500;
	private boolean running = true;
	private KeyEvent keyEvent = null;
	private Player player;
	private ArrayList<Player> playerData = new ArrayList<Player>();
	private Encoder encoder = new Encoder();
	private Level level= new Level();
	private long connectionID;
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
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter toServer = new PrintWriter(server.getOutputStream(), true);
			String s = null;
			while (running) {
				long currentTime = System.nanoTime();
				long deltaTime = currentTime - lastTime; // Change in time from last loop cycle
				renderTime += deltaTime; // Increase render counter
				tickTime += deltaTime; // Increase tick counter

				// Catch up on ticks
				while (tickTime >= targetNSPT) {
					tick();
					tickTime -= targetNSPT;
					if (keyEvent != null) {
                        toServer.println((String.valueOf(getKeyEvent())));
                    } else toServer.println("");
					while (fromServer.ready()) {
						s = fromServer.readLine();

						String[] arr = s.split("_");
						String data = arr[1];
						switch(arr[0]) {
							case "update": {
								playerData = (ArrayList<Player>) encoder.decodeObj(data);
								break;
							}
							case "player":{
								player = (Player) encoder.decodeObj(data);
								break;
							}
							case "level":{
								level = (Level) encoder.decodeObj(data);
								break;
							}
							case "print": {
								System.out.println(data);
								break;
							}
						}
					}
					s = null;
					
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

	public void tick() {
	}

	/**
	 * Gets the width of the content pane
	 * 
	 * @return
	 */
	public int getWidth() {
		return frame.getContentPane().getWidth();
	}

	/**
	 * Gets the height of the content pane
	 * 
	 * @return
	 */
	public int getHeight() {
		return frame.getContentPane().getHeight();
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
		if(player!= null)
			level.render(g, player.getRoomX(), player.getRoomY());
		//g.setColor(Color.black);
		//g.drawString("Jacob was here", 200, 200);
		g.setColor(Color.BLUE);
		for(int i = 0; i < playerData.size(); i++) {
			Player p = playerData.get(i);
			g.drawString(p.getName() + i +1, p.getPosX(), p.getPosY()-10);
			g.fillRect(p.getPosX(), p.getPosY(), 32, 32);
		}
		// Don't draw stuff after here
		g.dispose();
		bs.show();

	}

	public void setKeyEvent(KeyEvent e) {
		// if (keyEvent != null)
		// System.out.println(e.getKeyChar());
		this.keyEvent = e;
	}

	public char getKeyEvent() {
		return this.keyEvent.getKeyChar();
	}
}
