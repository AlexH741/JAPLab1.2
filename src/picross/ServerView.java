package src.picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ServerView implements ViewInterface, ActionListener {
	private Server server;
	private ViewScene viewScene = new ViewScene();
	private JTextArea textArea = viewScene.getTextArea();
	private JTextArea userText = viewScene.getUserText();
	private JTextArea portTextArea;
	private JLabel portText;
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
	BufferedImage myPicture;
	ImageIcon serverimage1 = new ImageIcon("imgfolder/omark.png");
	ImageIcon serverimage2;
	JFrame frame = new JFrame("ServerView");
	JPanel buttonPanel = new JPanel();
	JPanel CenterPanel = new JPanel();
	JButton Exbutton = new JButton("Excecute");
	JButton Resultbutton = new JButton("Result");
	JButton Endbutton = new JButton("End");
	JCheckBox finalCheckBox = new JCheckBox("Finalize");
	JTextArea textAreaServer = new JTextArea();
	JLabel image;

	public void createAndShowGUI() throws IOException {
		myPicture = ImageIO.read(new File("imgfolder/piccross.png"));
		serverimage2 = new ImageIcon(myPicture);
		image = new JLabel(serverimage2);
		portTextArea = new JTextArea();
		portText = new JLabel("Port:");
		buttonPanel.setLayout(new FlowLayout());
		CenterPanel.setLayout(new BorderLayout());
		CenterPanel.add(image, BorderLayout.NORTH);
		frame.add(CenterPanel);
		buttonPanel.add(portText);
		buttonPanel.add(portTextArea);
		buttonPanel.add(Exbutton);
		Exbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startServer(portTextArea.getText());
			}
		});
		
		buttonPanel.add(Resultbutton);
		buttonPanel.add(finalCheckBox);
		buttonPanel.add(Endbutton);
		CenterPanel.add(textAreaServer, BorderLayout.SOUTH);
		CenterPanel.add(buttonPanel, BorderLayout.CENTER);

		CenterPanel.setPreferredSize(new Dimension(100, 100));


		Exbutton.setPreferredSize(new Dimension(100, 50));

		Resultbutton.setPreferredSize(new Dimension(100, 50));

		Endbutton.setPreferredSize(new Dimension(100, 50));

		portTextArea.setPreferredSize(new Dimension(100, 20));
		textAreaServer.setPreferredSize(new Dimension(500, 400));

		viewScene.getSendButton().addActionListener((e) -> {
			writeln();
		}

		);
		frame.setTitle("Picross Server");
		frame.setMinimumSize(new Dimension(550, 560));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setIconImage(serverimage1.getImage());

		frame.getContentPane().setBackground(Color.white);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		ServerView serverView = new ServerView();
		serverView.createAndShowGUI();
	}

	@Override
	public void println(String text) {
		System.out.println("test server at println serverview");
		textAreaServer.append(text + "\n");
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
