package src.picross;
/*
 * TODO This class should used to create the splash screen, Initialize the game(creating the components),
 * Refresh/clean components during execution, Show menu dialogs(ex: "About" or "Color Chooser"), interact
 * with the player when they are playing the game (updating colors, points, etc.) 
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;

public class View extends JFrame{
        /* 
        private JTextArea Area1 = new JTextArea(30, 10);
        private JButton[][] areaButtons = new JButton[5][5];
        private JCheckBox markButton = new JCheckBox();
        private Boolean[][] selectedButton = new Boolean[5][5];
        */
        
        private JTextArea Area1 = new JTextArea(30, 10);
        private JButton[][] areaButtons = new JButton[5][5];
        private JCheckBox markButton = new JCheckBox();
        //private Boolean[][] selectedButton = new Boolean[5][5];
        private JButton resetButton;
        private JTextField TimerBox, PointsBox;
        private JLabel AreaL1, AreaL2, AreaL3, AreaL4, AreaL5, AreaT1, AreaT2, AreaT3, AreaT4, AreaT5;
        private JComboBox<Object> LanguageBox;
        public int test = 100;
        
        public ActionListener actionResetListener;
        
        //public ActionListener actionResetListener = new Controller(resetButton);

        //private ActionListener actionListener = new Controller(resetButton, areaButtons, markButton, TimerBox, PointsBox, LanguageBox, AreaL1, AreaL2, AreaL3, AreaL4, AreaL5, AreaT1, AreaT2, AreaT3, AreaT4, AreaT5);
        

    View() {
        JFrame frame = new JFrame("Picross");
	    frame.setBackground(Color.white);
	    frame.setMinimumSize(new Dimension(900,800));
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(5,5,5,5));
		JPanel textRightPanel = new JPanel();
		JPanel textLeftPanel = new JPanel();
		JPanel textTopPanel = new JPanel();
        /* 
        JOptionPane.showMessageDialog(frame, "image here", "", JOptionPane.PLAIN_MESSAGE);
        try { //TODO modify try/catch
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        frame.add(BorderLayout.LINE_START, createLeftPanel(textLeftPanel));
        frame.add(BorderLayout.LINE_END, createRightPanel(textRightPanel));
        frame.add(BorderLayout.PAGE_START, createTopPanel(textTopPanel));
        frame.add(BorderLayout.CENTER, createButtons(buttonPanel));
        frame.setJMenuBar(createMenuBar());
        frame.pack();
		frame.setVisible(true);
    }
    
    private JPanel createLeftPanel(JPanel left) {
        left.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //String text = "\n\n\n\n\t (1,1) \n\n\n\n\n\n\n\n\n\n\t (2,2) \n\n\n\n\n\n\n\n\n\n\n\t (3, 3) \n\n\n\n\n\n\n\n\n\n\t (4,4)";
        //JTextArea Area2 = new JTextArea(text , 40, 10);
        AreaL1 = new JLabel("(1,1)");
        AreaL2 = new JLabel("(1,2)");
        AreaL3 = new JLabel("(1,3)");
        AreaL4 = new JLabel("(1,4)");
        AreaL5 = new JLabel("(1,5)");
        //Area2.setEditable(false); 
        c.fill = GridBagConstraints.RELATIVE;
        c.ipadx = 50;
        c.ipady = 125; 
		left.add(AreaL1, c);
        c.gridy = 1;
        left.add(AreaL2, c);
        c.gridy = 2;
        left.add(AreaL3, c);
        c.gridy = 3;
        left.add(AreaL4, c);
        c.gridy = 4;
        left.add(AreaL5, c);

        return left;
    }

    private JPanel createRightPanel(JPanel right) {
        right.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel timerLabel = new JLabel("Timer: ");
        TimerBox = new JTextField("00:00");
        TimerBox.setEditable(false);

        JLabel pointsLabel = new JLabel("Points: ");
        PointsBox = new JTextField("0");
        PointsBox.setEditable(false);

        //JButton resetButton = new JButton("Reset");
        //ActionListener listener = new Controller();
        resetButton = new JButton("Reset");
        //ActionListener actionResetListener = new Controller(resetButton);
        actionResetListener = new Controller(resetButton);
        resetButton.addActionListener(actionResetListener);
        //resetButton.addActionListener(this);
        /* 
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Area1.setText(Area1.getText() + "\n Reset game");
            }
        });
        */
        
        

		JScrollPane textArea1 = new JScrollPane(Area1);  
		textArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Area1.setEditable(false);

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;        
        //right.add(new JLabel(new ImageIcon("bin/PICROSS1.png")), c);
        right.add(new JLabel(new ImageIcon("bin/PICROSS1.png")), c);

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 1.0;
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

    private JPanel createTopPanel(JPanel top) {
        top.setLayout(new GridBagLayout());
        //top.set
        GridBagConstraints c = new GridBagConstraints();
        String[] Languages = {"French" , "English", "Vietnamese"};
        //String text = "\n\n\n\n\n\n\n\t                 (1,1)\t\t             (2,2)\t\t             (3,3)                                          (4,4)";

        markButton = new JCheckBox("Mark");
        //markButton.addActionListener((ActionListener) this);

        LanguageBox = new JComboBox<Object>(Languages);
		//LanguageBox.addActionListener(LanguageBox);
		LanguageBox.setSelectedIndex(1);

        //JTextArea textArea3 = new JTextArea(text, 7, 70);
        //textArea3.setEditable(false);
        AreaT1 = new JLabel("(1,1)");
        AreaT2 = new JLabel("(1,2)");
        AreaT3 = new JLabel("(1,3)");
        AreaT4 = new JLabel("(1,4)");
        AreaT5 = new JLabel("(1,5)");
        
        ///* 
		//c.fill = GridBagConstraints.RELATIVE;
        //c.fill = GridBagConstraints.REMAINDER;
        //c.ipadx = 70;
        c.ipady = 50;
        //c.anchor = GridBagConstraints.EAST;
        //top.add(markButton, c);
        double var = 0.1;
        GridBagConstraints t = createGbc(0, 0);
        //t.ipadx = 65; 
		top.add(markButton, t);
        t = createGbc(1, 0);
        t.weightx = var;
        top.add(AreaT1, t);
        //t.ipadx = 0;
        t = createGbc(2, 0);
        t.weightx = var;
        top.add(AreaT2, t);
        t = createGbc(3, 0);
        t.weightx = var;
        top.add(AreaT3, t);
        t = createGbc(4, 0);
        t.weightx = var;
        top.add(AreaT4, t);
        t = createGbc(5, 0);
        t.weightx = var;
        top.add(AreaT5, t);
        /* 
        top.add(markButton, createGbc(0, 0));
        top.add(Area1, createGbc(1, 0));
        top.add(Area2, createGbc(2, 0));
        top.add(Area3, createGbc(3, 0));
        top.add(Area4, createGbc(4, 0));
        top.add(Area5, createGbc(5, 0));
        /* 
        // c.ipadx = 80;
        c.gridx = 1;
        top.add(Area1, c);
        c.gridx = 2;
        top.add(Area2, c);
        c.gridx = 3;
        top.add(Area3, c);
        c.gridx = 5;
        top.add(Area4, c);
        c.gridx = 6;
        //c.ipadx = 90;
        top.add(Area5, c);
        c.gridx = 7;
        //c.ipadx = 0;
        //top.add(textArea3);
        top.add(LanguageBox, c);
        */
        return top;
    }

    private JPanel createButtons(JPanel buttons) {
		for (int i = 0; i < 5; i++) {
			final int final_i = i;
			for (int j = 0; j < 5; j++) {
				final int final_j = j;
				areaButtons[i][j] = new JButton();
                areaButtons[i][j].setBackground(new Color(0x7CCD7C));
                //areaButtons[i][j].addActionListener(this);
                /* 
				areaButtons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//Area1.setText(Area1.getText() + "\n Pos " + (final_i + 1) + ", " + (final_j + 1));
                        selectedButton[final_i][final_j] = true;
					}
				});
                */

				buttons.add(areaButtons[i][j]);
			}
		}
        return buttons;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu Game = new JMenu("Game");
        JMenu Help = new JMenu("Help");
        JMenuItem New = new JMenuItem("New"); 
        JMenuItem Solution  = new JMenuItem("Solution");
        JMenuItem Exit = new JMenuItem("Exit"); 
        JMenuItem Colors = new JMenuItem("Colors");
        JMenuItem About = new JMenuItem("About");
        Game.add(New);
        Game.add(Solution);
        Game.add(Exit);

        Help.add(Colors);
        Help.add(About);

        menuBar.add(Game);
        menuBar.add(Help);

        return menuBar;
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipady = 50;
  
        //gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        //gbc.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;


        if (x == 0) {
            gbc.fill = GridBagConstraints.BOTH;
            System.out.println("flag 2");
        } else {
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }

        if (x == 0) {
            gbc.weightx = 0.1;
            System.out.println("flag 3");
        } else {
            gbc.weightx = 1.0;
        }
  
        //gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
        //gbc.weightx = (x == 0) ? 0.1 : 1.0;
        //gbc.weighty = 1.0;
        return gbc;
    }

    public void createLogTextNNL(String text) {
        Area1.setText(Area1.getText() + text );
    }
    public void createLogTextNL(String text) {
        Area1.setText(Area1.getText() + "\n" + text);
        System.out.println("New Line");
    }
    public void createLogText(String text) {
        Area1.setText(Area1.getText() + "\n" + text);
    }
}
