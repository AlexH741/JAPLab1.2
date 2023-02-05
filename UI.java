
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

public class UI extends JFrame{
    private JTextArea Area1 = new JTextArea(":)", 30, 10);

    public UI() {
        JFrame frame = new JFrame("Picross");
	    frame.setBackground(Color.white);
	    frame.setMinimumSize(new Dimension(900,800));
	    frame.setResizable(false);
	    //frame.setResizable(isDefaultLookAndFeelDecorated());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
		JPanel textRightPanel = new JPanel();
		JPanel textLeftPanel = new JPanel();
		JPanel textTopPanel = new JPanel();

        frame.add(BorderLayout.LINE_START, createLeftPanel(textLeftPanel));
        frame.add(BorderLayout.LINE_END, createRightPanel(textRightPanel));
        frame.add(BorderLayout.PAGE_START, createTopPanel(textTopPanel));
        frame.add(BorderLayout.CENTER, createButtons(buttonPanel));
        frame.pack();
		frame.setVisible(true);
    }

    private JPanel createLeftPanel(JPanel left) {
        JTextArea Area2 = new JTextArea(":)", 30, 10);  
		left.add(Area2);

        int[] leftHints = {1, 2, 3, 4};
		JLabel[] leftLabels = new JLabel[leftHints.length];
		for (int i = 0; i < leftHints.length; i++) {
    		leftLabels[i] = new JLabel(String.valueOf(leftHints[i]));
    		left.add(leftLabels[i]);
		}
        return left;
    }

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


		JScrollPane textArea1 = new JScrollPane(Area1);  
		textArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        right.add(timerLabel, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        right.add(TimerBox, c);

        c.weightx = 0;
		right.add(textArea1, c);

        c.fill = GridBagConstraints.RELATIVE;;
        c.gridy = 2;
        right.add(pointsLabel, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        //c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 0;
        //.gridx = GridBagConstraints.RELATIVE;
        //c.gridy = 2;
        //c.gridwidth = GridBagConstraints.REMAINDER;
        //c.gridwidth = GridBagConstraints.RELATIVE;
        right.add(PointsBox, c);

        //c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 0.5;
        //c.gridx = 0;
        //c.gridy = 3;
        //right.add(resetButton, c);

        return right;
    }

    private JPanel createTopPanel(JPanel top) {
        String[] Languages = {"French" , "English", "Vietnamese"};

        JCheckBox markButton = new JCheckBox("Mark");
		top.add(markButton);

        JComboBox<Object> LanguageBox = new JComboBox<Object>(Languages);
		LanguageBox.addActionListener(LanguageBox);
		LanguageBox.setSelectedIndex(1);
        top.add(LanguageBox);

        JTextArea textArea3 = new JTextArea(":)", 7, 30);
		top.add(textArea3);

        int[] topHints = {1, 2, 3, 4};
		JLabel[] topLabels = new JLabel[topHints.length];
		for (int i = 0; i < topHints.length; i++) {
    		topLabels[i] = new JLabel(String.valueOf(topHints[i]));
    		top.add(topLabels[i]);
		}

        return top;
    }

    private JPanel createButtons(JPanel buttons) {
        JButton[][] areaButtons = new JButton[4][4];
		Boolean[][] markButtons = new Boolean[4][4];

		for (int i = 0; i < 4; i++) {
			final int final_i = i;
			for (int j = 0; j < 4; j++) {
				final int final_j = j;
				areaButtons[i][j] = new JButton();
                areaButtons[i][j].setBackground(new Color(0x7CCD7C));

				areaButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Area1.setText(Area1.getText() + "\n Position " + (final_i + 1) + ", " + (final_j + 1) + " pressed");
					}
				});

				buttons.add(areaButtons[i][j]); //TODO: Recolor/reskin buttons
			}
		}
        return buttons;
    }
}
