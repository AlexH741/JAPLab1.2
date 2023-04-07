package src.picross;

import javax.swing.JFrame;
import javax.swing.JTextArea;
//import ViewScene;

public class ClientView {
	private Client client;
	private ViewScene viewScene = new ViewScene();
	private JTextArea textArea = viewScene.getTextArea();
	private JTextArea userText = viewScene.getUserText();
	private static int PORT = 3000;

	private void startClient(String arg) {
		int portNumber;
		if (arg == null) {
			System.out.println("java <port number>");
			portNumber = PORT;
		} else {
			portNumber = Integer.parseInt(arg);
		}
		System.out.println("Starting Server Thread on port " + portNumber);
		Thread servDaemon = new Thread(new Server((ViewInterface) this, portNumber));
		servDaemon.start();
	}

	public void createAndShowGUI() {
		JFrame frame = new JFrame("ClientView");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		viewScene.getSendButton().addActionListener((e) -> {
			writeln();
		});

		frame.getContentPane().add(viewScene.getScene());
		frame.pack();
		startClient("10001");
		frame.setVisible(true);
	}

	private void writeln() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		ClientView clientView = new ClientView();
		clientView.createAndShowGUI();
	}


}
