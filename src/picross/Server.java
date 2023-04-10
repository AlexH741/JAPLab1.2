package src.picross;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Class Server.
 * 
 * @author sousap
 *
 */
public class Server implements Runnable {

	/**
	 * Socket variable.
	 */
	Socket sock;

	/**
	 * Variables for number clients.
	 */
	int nclient = 0, nclients = 0;

	/**
	 * Server socket.
	 */
	ServerSocket servsock;

	/**
	 * Default port.
	 */

	/**
	 * Number of port.
	 */
	int portNumber = 0;

	private ViewInterface view;

	/**
	 * Default constructor.
	 */
	public Server(ViewInterface _view, int portNumber) {
		view = _view;
		println("test server at server constructor");
		
		view.setServer(this);
		try {
			servsock = new ServerSocket(portNumber);
			println("Server running on " + InetAddress.getLocalHost() + " at port " + portNumber + "!");
		} catch (IOException e) {
			println("Error: " + e.toString());
			e.printStackTrace();
		}
	}

	public void writeln(String string) {
		println("test server at writeln server constructor");
		int j = 0;
		for (ListIterator<Worked> i = connections.listIterator(); i.hasNext();) {
			System.out.println(j++);
			Worked w = i.next();
			if (!w.isRunning()) {
				println(j + ": Not running");
				i.remove();
			} else {
				println(j + ": Running");
				w.writeln(string);
			}
		}

	}

	private void println(String string) {
		System.out.println("test server at println server constructor");
		System.out.println(string);
		view.println(string);
	}

	private LinkedList<Worked> connections = new LinkedList<>();

	/**
	 * Run method.
	 */
	public void run() {
			println("test server at run method server constructor");
		for (;;) {
			try {
				sock = servsock.accept();
				nclient += 1;
				nclients += 1;
				println("Connecting " + sock.getInetAddress() + " at port " + sock.getPort() + ".");
			} catch (IOException ioe) {
				println(ioe.toString());
			}
			Worked w = new Worked(sock, nclient);
			connections.add(w);
			w.start();
		}
	}

	/**
	 * Inner class for Theads.
	 * 
	 * @author sousap
	 *
	 */
	class Worked extends Thread {

		/**
		 * Socket variable.
		 */
		Socket sock;

		/**
		 * Integers for client and positions.
		 */
		int clientid, poscerq;

		/**
		 * String for data.
		 */
		String strcliid, dadosCliente;

		/**
		 * Default constructor.
		 * 
		 * @param s       Socket
		 * @param nclient Number of client.
		 */
		public Worked(Socket s, int nclient) {
			sock = s;
			clientid = nclient;
		}

		private boolean running;

		public boolean isRunning() {
			return running;
		}

		private PrintStream out = null;
		private BufferedReader in;

		public void writeln(String string) {
			println(clientid + ": Writing text");
			out.println("String \"" + string + "\" received.");
			out.flush();
		}

		/**
		 * Run method.
		 */
		public void run() {
			running = true;
			String data;
			try {
				out = new PrintStream(sock.getOutputStream());
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out.println(clientid);
				data = in.readLine();
				poscerq = data.indexOf("#");
				strcliid = data.substring(0, poscerq);
				dadosCliente = data.substring(poscerq + 1, data.length());
				while (!dadosCliente.equals("end")) {
					println("Cli[" + strcliid + "]: " + data);
					out.println("String \"" + data + "\" received.");
					out.flush();
					data = in.readLine();
					poscerq = data.indexOf("#");
					strcliid = data.substring(0, poscerq);
					dadosCliente = data.substring(poscerq + 1, data.length());
				}
				println("Disconecting " + sock.getInetAddress() + "!");
				nclients -= 1;
				println("Current client number: " + nclients);
				running = false;
				if (nclients == 0) {
					println("Ending server...");
					sock.close();
					System.exit(0);
				}
			} catch (IOException ioe) {
				println(ioe.toString());
			}
		}
	}
}