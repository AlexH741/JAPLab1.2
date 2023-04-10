package src.picross;

import java.net.*;
import java.io.*;

/**
 * Class ThreadClient.
 * 
 * @author sousap
 *
 */
public class Client {

	/**
	 * Default port.
	 */
	static int PORT = 10001;

	/**
	 * Number of port.
	 */
	static int portNumber = 0;

	/**
	 * Default hostname.
	 */
	static String HOSTNAME = "localhost";

	/**
	 * Variable for hostname.
	 */
	static String hostName = "";

	/**
	 * Default constructor.
	 */
	/*
	 * public Client() {
	 * ; // No commands
	 * }
	 */

	private Socket sock;

	private BufferedReader dis;

	private PrintStream dat;

	private BufferedReader inConsole;

	/**
	 * Main method.
	 * 
	 * @param args Param arguments.
	 */
	// public static void main(String args[]) {
	public Client(int port, String hostname, String username, String game) {
		portNumber = port;
		hostName = hostname;
		System.out.println("Connecting with server on " + hostName + " at port " + portNumber);
		System.out.println("Starting Server Thread on port " + portNumber);
		try {
			sock = new Socket(hostName, portNumber);
			dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			dat = new PrintStream(sock.getOutputStream());
			String strcliid = dis.readLine();
			String messageData = username + "$" + game;
			String serverData;
			inConsole = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(messageData);
			// consoleData = inConsole.readLine();
			/*
			 * while (!consoleData.equals("end")) {
			 * consoleData = strcliid + "#" + consoleData;
			 * dat.println(consoleData);
			 * dat.flush();
			 * serverData = dis.readLine();
			 * System.out.println("Server: " + serverData);
			 * System.out.print("Client[" + strcliid + "]: ");
			 * consoleData = inConsole.readLine();
			 * }
			 */
			// consoleData = strcliid + "#" + consoleData;
			dat.println(messageData);
			serverData = dis.readLine();
			System.out.println(serverData);
			dat.println("");
			dat.flush();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void windowclose() {
		System.out.println("closing socket");
		try {
			sock.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
