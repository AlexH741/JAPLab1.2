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
        private JTextArea Area1 = new JTextArea(30, 10);
        private JButton[][] areaButtons = new JButton[5][5];
        private JCheckBox markButton = new JCheckBox();
        private JButton resetButton;
        private JTextField TimerBox, PointsBox;
        private JLabel[] AreaL;
        private JLabel[] AreaT;
        private JComboBox<Object> LanguageBox;

    View(int x, int y) {
        AreaL = new JLabel[y];
        AreaT = new JLabel[x];
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

        frame.add(BorderLayout.LINE_START, createLeftPanel(textLeftPanel, y));
        frame.add(BorderLayout.LINE_END, createRightPanel(textRightPanel));
        frame.add(BorderLayout.PAGE_START, createTopPanel(textTopPanel, x));
        frame.add(BorderLayout.CENTER, createButtons(buttonPanel, x, y));
        frame.setJMenuBar(createMenuBar());
        frame.pack();
		frame.setVisible(true);
    }
    
    private JPanel createLeftPanel(JPanel left, int y) {
        left.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.RELATIVE;
        c.ipadx = 50;
        c.ipady = 125;
        for (int i =0; i < y; i++) {
            AreaL[i] = new JLabel("test");
            c.gridy = i;
            left.add(AreaL[i], c);
        }
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

        resetButton = new JButton("Reset");
        
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

    private JPanel createTopPanel(JPanel top, int x) {
        top.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        String[] Languages = {"French" , "English", "Vietnamese"};

        markButton = new JCheckBox("Mark");

        LanguageBox = new JComboBox<Object>(Languages);
		LanguageBox.setSelectedIndex(1);

        c.ipady = 50;
        double var = 0.1;
        GridBagConstraints t = createGbc(0, 0);
		top.add(markButton, t);

        for (int i =0; i < x; i++) {
            AreaT[i] = new JLabel("test");
            t = createGbc(i+1, 0);
            t.weightx = var;
            top.add(AreaT[i], t);
        }
        return top;
    }

    private JPanel createButtons(JPanel buttons, int x, int y) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				areaButtons[i][j] = new JButton();
                areaButtons[i][j].setBackground(new Color(150, 150, 150));
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

    public void addActionListeners(ActionListener r, ActionListener m, ActionListener b) {
        resetButton.addActionListener(r);
        markButton.addActionListener(m);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                areaButtons[i][j].addActionListener(b);
            }
        }
    }
    public boolean isButton(ActionEvent e, int x , int y) {
        return e.getSource().equals(areaButtons[x][y]);
    }
    public void changeColor(int x, int y, Color c) {
        areaButtons[x][y].setBackground(c);
    }
    public void changeLabelText(boolean[][] board, int x, int y) {
        for(int i = 0; i < x; i++) {
            String text = "";
            int num = 0;
            for (int j = 0; j < y; j++) {
                if (board[j][i]) {
                    System.out.println(num);
                    num++;
                } else if (!board[j][i] && num > 0) {
                        System.out.println(text + Integer.toString(num) + ", ");
                        text = text + Integer.toString(num) + ", ";
                    
                    num = 0; 
                } else if (j+1 == y && text == "") {
                    text = "0";
                } else if (j+1 == y && !board[j][i]) {
                    System.out.println("flag");
                    text = text.substring(0, text.length() - 2);
                } else if (j+1 == y && !board[j][i+1]) {
                    System.out.println("flag2");
                    text = text.substring(0, text.length() - 2);
                }
            }
            if (num > 0) {
                System.out.println(text + Integer.toString(num));
                        text = text + Integer.toString(num);
            } else if ((text.length() % 2 == 0) && text.length() % 3 == 0 ) {
                text = text + "mark";
                //text = text + text.substring(0, text.length() - 2);
            }
            num = 0;
            //text = text + text.substring(0, text.length() -2);
            AreaT[i].setText(text);
        }
        for (int i = 0; i < y; i++) {
            String text = "";
            for(int j = 0; j < x; j++) {
            }
            AreaL[i].setText("");
        }
    }
}
