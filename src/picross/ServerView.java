package src.picross;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class ServerView implements ViewInterface {
private Server server;
private ViewScene viewScene = new ViewScene();
private JTextArea textArea = viewScene.getTextArea();
private JTextArea userText = viewScene.getUserText();
private static int PORT = 3000;
private void startServer(String arg) {
	int portNumber;
	if (arg == null) {
		System.out.println("java <port number>");
		portNumber = PORT;
	} else {
		portNumber = Integer.parseInt(arg);
	}
	System.out.println("Starting Server Thread on port " + portNumber);
	Thread servDaemon = new Thread(new Server(this, portNumber));
	servDaemon.start();
}

void writeln() {
	server.writeln(userText.getText());
	userText.setText("");
}
JFrame frame = new JFrame("ServerView");
public void createAndShowGUI() {
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	viewScene.getSendButton().addActionListener((e) -> {
		writeln();
	}
	);
	frame.pack();
	frame.setVisible(true);
	startServer("10001");

}
JPanel textRightPanel = new JPanel();
JPanel textLeftPanel = new JPanel();
JPanel textTopPanel = new JPanel();



public static void main(String[] args) {
	ServerView serverView = new ServerView();
	serverView.createAndShowGUI();
}

@Override
public void println(String text) {
	textArea.append(text + "\n");
}

@Override
public void setServer(Server _server) {
	server = _server;
}
}