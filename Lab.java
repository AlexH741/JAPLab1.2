import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class Lab {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello World Java Swing");
		frame.setBackground(Color.white);
		frame.setMinimumSize(new Dimension(800,800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
		JPanel textRightPanel = new JPanel();
		JPanel textLeftPanel = new JPanel();
		JPanel textTopPanel = new JPanel();

		JTextArea textArea1 = new JTextArea(":)", 30, 10);
		textRightPanel.add(textArea1);

		JTextArea textArea2 = new JTextArea(":)", 30, 10);
		textLeftPanel.add(textArea2);

		JTextArea textArea3 = new JTextArea(":)", 7, 30);
		textTopPanel.add(textArea3);

		JButton areaButtons[] = new JButton[16];

		for (int i = 0; i < 16; i++) {
			areaButtons[i] = new JButton();
			buttonPanel.add(areaButtons[i]);
		}

		frame.add(BorderLayout.PAGE_START, textTopPanel);
		frame.add(BorderLayout.LINE_END, textRightPanel);
		frame.add(BorderLayout.LINE_START, textLeftPanel);
		frame.add(BorderLayout.CENTER, buttonPanel);
		frame.pack();
		frame.setVisible(true);
	}
}

//ahihihihih