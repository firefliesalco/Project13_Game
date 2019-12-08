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

public class Game extends Canvas {

	private JFrame frame;
	private final static int WIDTH = 500, HEIGHT = 500;
	private boolean running = true;
	private KeyEvent keyEvent = null;

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

		try (Socket server = new Socket("141.219.196.51", Integer.valueOf(2112))) {
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
					System.out.println("2");
					if (keyEvent != null) {
						toServer.println((String.valueOf(getKeyEvent())));
					}
					System.out.println("3");
					s = fromServer.readLine();
					System.out.println("4");
					if (s != null)
						System.out.println(s);
					s = null;
					System.out.println("5");
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
		System.out.println("1");
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

		g.setColor(Color.black);
		g.drawString("Jacob was here", 200, 200);

		// Don't draw stuff after here
		g.dispose();
		bs.show();

	}

	public void setKeyEvent(KeyEvent e) {
		if (keyEvent != null)
			System.out.println(e.getKeyChar());
		this.keyEvent = e;
	}

	public char getKeyEvent() {
		return this.keyEvent.getKeyChar();
	}
}
