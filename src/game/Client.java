package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

	
	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("Select a name");
		String ip = JOptionPane.showInputDialog("Input the server IP");
		Game game = new Game(ip, name);
	}
	
}
