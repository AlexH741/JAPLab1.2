/**
 * Rough draft of Picross game UI
 * 
 * CET - CS Academic Level 4
 * Student Names: Bich Khe Hoang, Alex Holmes
 * Student Numbers: , 041026437
 * Section 302
 * Course: CST8221 - Java Application Programming
 * Lab Professor: 
 */

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

public class Game extends JFrame{ //TODO: Javadoc

	public static void main(String[] args) {
		String[] Languages = {"French" , "English", "Vietnamese"};
		JFrame frame = new JFrame("Picross");
		frame.setBackground(Color.white);
		frame.setMinimumSize(new Dimension(800,800));
		frame.setResizable(false);
		//frame.setResizable(isDefaultLookAndFeelDecorated());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
		JPanel textRightPanel = new JPanel();
		JPanel textLeftPanel = new JPanel();
		JPanel textTopPanel = new JPanel();

		JButton markButton = new JButton("Mark");
		textTopPanel.add(markButton);

		JComboBox<Object> LanguageBox = new JComboBox<Object>(Languages);
		LanguageBox.addActionListener(LanguageBox);
		LanguageBox.setSelectedIndex(1);
		textTopPanel.add(LanguageBox);
		//TODO: add bottom pane?
		
		//TODO: add hint texts to panes
		//TODO: add language change functionality

		JTextArea Area1 = new JTextArea(":)", 30, 10);
		JScrollPane textArea1 = new JScrollPane(Area1);  
		textArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		textRightPanel.add(textArea1);

		JTextArea Area2 = new JTextArea(":)", 30, 10);
		//JScrollPane textArea2 = new JScrollPane(Area1);  
		textLeftPanel.add(Area2);

		JTextArea textArea3 = new JTextArea(":)", 7, 30);
		textTopPanel.add(textArea3);

		// Left side hint numbers
		int[] leftHints = {1, 2, 3, 4};
		JLabel[] leftLabels = new JLabel[leftHints.length];
		for (int i = 0; i < leftHints.length; i++) {
    		leftLabels[i] = new JLabel(String.valueOf(leftHints[i]));
    		textLeftPanel.add(leftLabels[i]);
		}

		// Top side hint numbers
		int[] topHints = {1, 2, 3, 4};
		JLabel[] topLabels = new JLabel[topHints.length];
		for (int i = 0; i < topHints.length; i++) {
    		topLabels[i] = new JLabel(String.valueOf(topHints[i]));
    		textTopPanel.add(topLabels[i]);
		}

		JButton[][] areaButtons = new JButton[4][4];
		Boolean[][] markButtons = new Boolean[4][4];

		for (int i = 0; i < 4; i++) {
			final int final_i = i;
			for (int j = 0; j < 4; j++) {
				final int final_j = j;
				areaButtons[i][j] = new JButton();

				areaButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Area1.setText(Area1.getText() + "\n Position " + (final_i + 1) + ", " + (final_j + 1) + " pressed");
					}
				});

				buttonPanel.add(areaButtons[i][j]); //TODO: Recolor/reskin buttons
			}
		}

		frame.add(BorderLayout.PAGE_START, textTopPanel);
		frame.add(BorderLayout.LINE_END, textRightPanel);
		frame.add(BorderLayout.LINE_START, textLeftPanel);
		frame.add(BorderLayout.CENTER, buttonPanel);
		frame.pack();
		frame.setVisible(true);
	}
}
