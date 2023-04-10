package src.picross;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;

//import java.awt.event.KeyEvent;
//import ViewScene;

public class ClientView {
	private Client client;
	// private ViewScene viewScene = new ViewScene();
	private JTextArea serverText;
	private JTextArea userText;
	private JTextArea portText;
	private JButton connectButton;
	private JButton endButton;
	private JButton newGameButton;
	private JButton sendGameButton;
	private JButton recieveGameButton;
	private JButton sendDataButton;
	private JButton playButton;
	private int PORT = 10001;
	private String HOSTNAME = "localhost";
	private String USER = "client";

	ClientView(Controller ctrl) {
		createAndShowGUI(ctrl);
		// PORT = Integer.valueOf(portText.getText());
		// HOSTNAME = serverText.getText();
		// USER = userText.getText();
		// client = new Client(PORT, HOSTNAME, USER);
	}

	/*
	 * private void startClient(String arg) {
	 * int portNumber;
	 * if (arg == null) {
	 * System.out.println("java <port number>");
	 * portNumber = PORT;
	 * } else {
	 * portNumber = Integer.parseInt(arg);
	 * }
	 * System.out.println("Starting Server Thread on port " + portNumber);
	 * Thread servDaemon = new Thread(new Server((ViewInterface) this, portNumber));
	 * servDaemon.start();
	 * }
	 */

	private void addController(Controller ctrl) {
		connectButton.setActionCommand("connectGame");
		connectButton.addActionListener(ctrl);
		endButton.setActionCommand("endConnection");
		endButton.addActionListener(ctrl);
		newGameButton.setActionCommand("newNetGame");
		newGameButton.addActionListener(ctrl);
		sendGameButton.setActionCommand("sendNetGame");
		sendGameButton.addActionListener(ctrl);
		recieveGameButton.setActionCommand("recieveNetGame");
		recieveGameButton.addActionListener(ctrl);
		sendDataButton.setActionCommand("sendData");
		sendDataButton.addActionListener(ctrl);
		playButton.setActionCommand("playButton");
		playButton.addActionListener(ctrl);
	}

	private void createAndShowGUI(Controller ctrl) {
		JLabel userLabel = new JLabel("User:");
		JLabel serverLabel = new JLabel("Server:");
		JLabel portLabel = new JLabel("Port:");
		serverText = new JTextArea();
		userText = new JTextArea();
		portText = new JTextArea();
		connectButton = new JButton("Connect");
		endButton = new JButton("End");
		newGameButton = new JButton("New Game");
		sendGameButton = new JButton("Send Game");
		recieveGameButton = new JButton("Recieve Game");
		sendDataButton = new JButton("Send Data");
		playButton = new JButton("Play");

		JFrame frame = new JFrame("ClientView");
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serverText = new JTextArea();
		addController(ctrl);

		serverText.setPreferredSize(new Dimension(100, 20));
		userText.setPreferredSize(new Dimension(100, 20));
		portText.setPreferredSize(new Dimension(100, 20));

		panel.add(userLabel);
		panel.add(userText);
		panel.add(serverLabel);
		panel.add(serverText);
		panel.add(portLabel);
		panel.add(portText);
		panel.add(connectButton);
		panel.add(endButton);
		panel.add(newGameButton);
		panel.add(sendGameButton);
		panel.add(recieveGameButton);
		panel.add(sendDataButton);
		panel.add(playButton);

		frame.add(panel);

		frame.addWindowListener(listener);

		frame.pack();
		frame.setMinimumSize(new Dimension(550, 560));
		frame.setVisible(true);
	}

	public void startClient(String game) {
		// try {
		PORT = Integer.valueOf(portText.getText());
		HOSTNAME = serverText.getText();
		USER = userText.getText();
		// } catch (TypeMismatchException e) {

		// }
		client = new Client(PORT, HOSTNAME, USER, game);
	}

	public void closewindow() {
		client.windowclose();
	}

	WindowListener listener = new WindowAdapter() {
		public void windowClosing(WindowEvent evt) {
			Frame frame = (Frame) evt.getSource();
			try {
				// client.windowclose();
				closewindow();
			} catch (NullPointerException e) {
				System.out.println("Socket has not been created");
			}
			System.out.println("Closing = " + frame.getTitle());
		}
	};
}
