package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas {

    private JFrame frame;
    private final static int WIDTH = 500, HEIGHT = 500;
    private boolean running = true;

    public static void main(String[] args) {
        new Game();
    }

    public Game(){
        frame = new JFrame("Assignment 13");
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setEnabled(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);

        run();
    }

    public void run(){

        //Change these for your target framerate and tickspeed.
        final long targetFPS = 30;
        final long targetTPS = 20;


        //Calculates the number of nanoseconds between each frame and tick.
        final long targetNSPF = 1000000000 / targetFPS;
        final long targetNSPT = 1000000000 / targetTPS;

        //Game Loop

        long lastTime = System.nanoTime();
        long renderTime = 0; // Nanoseconds since last render
        long tickTime = 0; // Nanoseconds since last tick
        while(running) {

            long currentTime = System.nanoTime();
            long deltaTime = currentTime - lastTime; // Change in time from last loop cycle

            renderTime += deltaTime; // Increase render counter
            tickTime += deltaTime; // Increase tick counter

            //Catch up on ticks
            while(tickTime >= targetNSPT){
                tick();
                tickTime -= targetNSPT;
            }

            //Only render once, even if there's a delay or something
            if(renderTime > targetNSPF){
                render();
                renderTime = 0;
            }

            //Update time tracker
            lastTime = currentTime;
        }
        System.out.println("Game Loop Stopped, Exiting Game"); // :(
        System.exit(0);
    }

    public void tick(){

    }

    /**
     * Gets the width of the content pane
     * @return
     */
    public int getWidth(){
        return frame.getContentPane().getWidth();
    }

    /**
     * Gets the height of the content pane
     * @return
     */
    public int getHeight(){
        return frame.getContentPane().getHeight();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //Draw stuff here

        g.setColor(Color.PINK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.black);
        g.drawString("Alex was here", 200, 200);

        //Don't draw stuff after here
        g.dispose();
        bs.show();

    }


}
