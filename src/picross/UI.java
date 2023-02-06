package src.picross;
/**
 * Rough draft of Picross game UI
 * 
 * CET - CS Academic Level 4
 * Student Names: Bich Khe Hoang, Alex Holmes
 * Student Numbers: 040990843 , 041026437
 * Section 302
 * Course: CST8221 - Java Application Programming
 * Lab Professor: Daniel Cormier
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.swing.*;

 /**
 * Class that creates a user interface with different components like text areas, buttons, panels, etc.
 */
public class UI extends JFrame implements ActionListener{
    /**
     * Text area that displays information.
     */
    private JTextArea Area1 = new JTextArea(30, 10);
    private JButton[][] areaButtons = new JButton[4][4];
    private JCheckBox markButton = new JCheckBox();
    private Boolean[][] selectedButton = new Boolean[4][4];

    /**
     * Constructor that creates and initializes the frame.
     */
    public UI() {
        JFrame frame = new JFrame("Picross");
	    frame.setBackground(Color.white);
	    frame.setMinimumSize(new Dimension(900,800));
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
		JPanel textRightPanel = new JPanel();
		JPanel textLeftPanel = new JPanel();
		JPanel textTopPanel = new JPanel();

        frame.add(BorderLayout.LINE_START, createLeftPanel(textLeftPanel));
        frame.add(BorderLayout.LINE_END, createRightPanel(textRightPanel));
        frame.add(BorderLayout.PAGE_START, createTopPanel(textTopPanel));
        frame.add(BorderLayout.CENTER, createButtons(buttonPanel));
        //frame.add(new JLabel(new ImageIcon("PICROSS1.png")));
        frame.pack();
		frame.setVisible(true);
    }

    /**
     * Creates and returns the left panel of the frame.
     * @param left JPanel object to be initialized as the left panel.
     * @return JPanel object that represents the left panel.
     */
    private JPanel createLeftPanel(JPanel left) {
        String text = "\n\n\n\n\t (1,1) \n\n\n\n\n\n\n\n\n\n\t (2,2) \n\n\n\n\n\n\n\n\n\n\n\t (3, 3) \n\n\n\n\n\n\n\n\n\n\t (4,4)";
        JTextArea Area2 = new JTextArea(text , 40, 10);
        Area2.setEditable(false);  
		left.add(Area2);

        return left;
    }

    /**
    * Creates the right panel of the application.
    * @param right JPanel to be created.
    * @return The created JPanel with components: a timer label, a text area with a scrollbar, a points label, and a reset button.
    */
    private JPanel createRightPanel(JPanel right) {
        right.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel timerLabel = new JLabel("Timer: ");
        JTextField TimerBox = new JTextField("00:00");
        TimerBox.setEditable(false);

        JLabel pointsLabel = new JLabel("Points: ");
        JTextField PointsBox = new JTextField("0");
        PointsBox.setEditable(false);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Area1.setText(Area1.getText() + "\n Reset game");
            }
        });


		JScrollPane textArea1 = new JScrollPane(Area1);  
		textArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Area1.setEditable(false);

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;        
        right.add(new JLabel(new ImageIcon("PICROSS1.png")), c);

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 0.0;
        right.add(timerLabel, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        right.add(TimerBox, c);

        c.weightx = 0;
		right.add(textArea1, c);

        c.fill = GridBagConstraints.RELATIVE;
        c.gridy = 3;
        right.add(pointsLabel, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 2;
        right.add(PointsBox, c);

        c.gridy = 4;
        right.add(resetButton, c);

        return right;
    }

    /**
    * Creates the top panel of the application.
    * 
    * @param top JPanel to be created.
    * @return The created JPanel with components: a checkbox, a dropdown menu, a text area, and an array of labels.
    */
    private JPanel createTopPanel(JPanel top) {
        String[] Languages = {"French" , "English", "Vietnamese"};
        String text = "\n\n\n\n\n\n\n\t                 (1,1)\t\t             (2,2)\t\t             (3,3)                                          (4,4)";

        markButton = new JCheckBox("Mark");
        markButton.addActionListener(this);

        JComboBox<Object> LanguageBox = new JComboBox<Object>(Languages);
		LanguageBox.addActionListener(LanguageBox);
		LanguageBox.setSelectedIndex(1);

        JTextArea textArea3 = new JTextArea(text, 7, 70);
        textArea3.setEditable(false);
        
		top.add(markButton);
        top.add(textArea3);
        top.add(LanguageBox);

        return top;
    }

    /**
    * Creates the buttons in the application.
    * 
    * @param buttons JPanel to be created.
    * @return The created JPanel with an array of buttons and their corresponding mark buttons. Each button adds a text to the text area when pressed.
    */
    private JPanel createButtons(JPanel buttons) {
		for (int i = 0; i < 4; i++) {
			final int final_i = i;
			for (int j = 0; j < 4; j++) {
				final int final_j = j;
				areaButtons[i][j] = new JButton();
                areaButtons[i][j].setBackground(new Color(0x7CCD7C));
				areaButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Area1.setText(Area1.getText() + "\n Pos " + (final_i + 1) + ", " + (final_j + 1));
                        selectedButton[final_i][final_j] = true;
					}
				});

				buttons.add(areaButtons[i][j]);
			}
		}
        return buttons;
    }

    /**
    * determines the recaction of an action event
    * 
    * @param e An action event.
    */
    public void actionPerformed(ActionEvent e) {
        JCheckBox c = (JCheckBox) e.getSource();
        if (c.isSelected()) {
            Area1.setText(Area1.getText() + "\n Mark");
        } else {
            Area1.setText(Area1.getText() + "\n Unmark");
        }
    }
}
