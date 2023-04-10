package src.picross;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;

public class ViewScene {
	private JFrame mainFrame;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JTextArea textArea;
	private JTextArea userText;
	private JButton sendButton;

	private void configure() {
		bottomPanel.add(userText);
		bottomPanel.add(sendButton);
		centerPanel.add(textArea);
		centerPanel.add(bottomPanel);
		mainFrame.add(centerPanel, BorderLayout.CENTER);
	}

	public ViewScene() {
		mainFrame = new JFrame("ViewScene");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		textArea = new JTextArea();
		userText = new JTextArea();
		sendButton = new JButton("Send");
		configure();
		mainFrame.pack();
	}

	public void createAndShowGUI() {
		mainFrame.setVisible(true);
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getUserText() {
		return userText;
	}

	public Component getScene() {
		// TODO Auto-generated method stub
		return null;
	}

}
