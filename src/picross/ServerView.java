package src.picross;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerView implements ViewInterface, ActionListener {
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
JPanel NorthPanel = new JPanel();
JPanel CenterPanel = new JPanel();
JPanel SouthPanel = new JPanel();
JButton Exbutton = new JButton("Excecute");
JButton Resultbutton = new JButton("Result");
JButton Endbutton = new JButton("End");
JCheckBox finalCheckBox = new JCheckBox();
JTextField textField = new JTextField();

public void createAndShowGUI() {
	frame.setTitle("Picross Server");
	frame.setSize(420,560);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//frame.setResizable(false);
	ImageIcon serverimage = new ImageIcon("imgfolder/omark.png");
	frame.setIconImage(serverimage.getImage());
	frame.getContentPane().setBackground(Color.white);
	frame.add(NorthPanel,BorderLayout.NORTH);
	NorthPanel.setBackground(Color.green);
	NorthPanel.setPreferredSize(new Dimension(100,100));
	frame.add(CenterPanel,BorderLayout.CENTER);
	CenterPanel.setBackground(Color.red);
	CenterPanel.setPreferredSize(new Dimension(100,100));
	
	frame.add(SouthPanel,BorderLayout.SOUTH);
	SouthPanel.setBackground(Color.white);
	SouthPanel.setPreferredSize(new Dimension(100,100));
	CenterPanel.add(Exbutton);
	Exbutton.setPreferredSize(new Dimension(100,50));
	CenterPanel.add(Resultbutton);
	Resultbutton.setPreferredSize(new Dimension(100,50));
	CenterPanel.add(finalCheckBox);
	finalCheckBox.setPreferredSize(new Dimension(25,25));
	finalCheckBox.setText("Finalize");
	CenterPanel.add(Endbutton);
	Endbutton.setPreferredSize(new Dimension(100,50));
	SouthPanel.add(textField);
	textField.setPreferredSize(new Dimension(200,100));
	viewScene.getSendButton().addActionListener((e) -> {
		writeln();
	}
	);
	frame.pack();
	frame.setVisible(true);
	startServer("10001");

}



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

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}