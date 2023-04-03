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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.*;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;

import javax.swing.*;


public class View extends JFrame{
    private Controller gameController;
    private Model gameModel;
    private String myLang;
    private int langIndex = 0;
    private String TITLE, BUTOK, BUTCANCEL, MENUTAB1, MENUTAB2, MENUTAB3, MENUNW, MENULG, MENUSG, MENUSL, MENUEX, MENUCLRS, MENUABT, MENUCFGC, BTMARK, BTRST, TXTTM, TXTPTS;
    private String[] text = {"Mark", "Timer", "Points", "Reset"};
    private String LANG1, LANG2, LANG3;
    private String[] Languages = {LANG1, LANG2, LANG3};
    private Locale currentLocale;
    private String SYSTEMMESSAGES = "resources/texts";
    private ResourceBundle texts;
    private JTextArea Area1 = new JTextArea(30, 10);
    private JButton[][] areaButtons = new JButton[5][5];
    private JCheckBox markButton = new JCheckBox();
    private boolean[][] userSelected;
    private boolean[][] userMarked;
    private JButton resetButton;
    private JTextField TimerBox, PointsBox;
    private JLabel AreaL[]; 
    private JLabel AreaT[];
    private JComboBox<Object> LanguageBox;
    private Color correctSelected = new Color(0x7CCD7C);
    private Color correctMarked = new Color(255, 255, 0);
    private Color incorrectColor = new Color(255, 0, 0);
    

    private JMenu Game;

    private JMenu Help;

    private JMenu Client;

    private JMenuItem loadGame;
    private JMenuItem saveGame;

    private JMenuItem ClientConfig;

    /**
	 * The JMenuItem to indicate weather the user wants to see the solution to the current match
	 */
	private JMenuItem Solution;
	/**
	 * The JMenuItem to indicate weather the user wants to start a new game
	 */
	private JMenuItem New;
    /**
	 * The JMenuItem to indicate weather the user wants to run debug senario 3 of the program
	 */
	private JMenuItem newConnect;
	/**
	 * The JMenuItem to indicate weather the user wants to run debug senario 3 of the program
	 */
	private JMenuItem disconnect;

    /**
	 * The shortcut key for the solution JMenuItem (ALT+S)
	 */
	private final KeyStroke keySolution = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK);
    /**
	 * The shortcut key for the new JMenuItem (CRTL+N)
	 */
	private final KeyStroke keyNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
    /**
	 * Icon image for nw JMenuItem
	 */
	//private ImageIcon newFileImg;
    private ImageIcon newFileImg;
    private ImageIcon svFileImg;
    private ImageIcon ldFileImg;

    private ImageIcon victoryImage;
	/**
	 * Icon image for exit JMenuItem
	 */
	private ImageIcon extFileImg;
	
	/**
	 * Icon image for solution JMenuItem
	 */
	private ImageIcon solFileImg;

    private ImageIcon abtFileImg;

    private ImageIcon clrFileImg;
	
	/**
	 * The main menu bar for the user
	 */
	private JMenuBar menuBar;

    private JMenuItem Colors;
	
	/**
	 * menu item to display programmer information
	 */
	private JMenuItem About;
	
	/**
	 * menu item to exit application
	 */
	private JMenuItem Exit;

    /**
     * Creates the view for the Picross game. 
     * The game model must be passed.
     * 
     * @param gameModel
     */
    View(Model gameModel) {
        splashScreen();
        this.gameModel = gameModel;
        //gameController = new Controller(this.gameModel, this);
        AreaL = new JLabel[gameModel.DimensionY];
        AreaT = new JLabel[gameModel.DimensionX];
        userSelected = new boolean[gameModel.DimensionX][gameModel.DimensionY];
        userMarked = new boolean[gameModel.DimensionX][gameModel.DimensionY];
        updateInterface(langIndex);
        JFrame frame = new JFrame(TITLE);
        try {
            newFileImg = new ImageIcon("imgfolder\\newicon.gif");
            extFileImg = new ImageIcon("imgfolder\\nexticon.gif");
            solFileImg = new ImageIcon("imgfolder\\solicon.gif");
            abtFileImg = new ImageIcon("imgfolder\\abouticon.gif");
            clrFileImg = new ImageIcon("imgfolder\\coloricon.gif");
        } catch (Exception e) {
            System.out.println(e);
        }

	    frame.setBackground(Color.white);
	    frame.setMinimumSize(new Dimension(900,800));
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(5,5,5,5));
		JPanel textRightPanel = new JPanel();
		JPanel textLeftPanel = new JPanel();
		JPanel textTopPanel = new JPanel();
        
        frame.add(BorderLayout.LINE_START, createLeftPanel(textLeftPanel, this.gameModel.DimensionY));
        frame.add(BorderLayout.LINE_END, createRightPanel(textRightPanel));
        frame.add(BorderLayout.PAGE_START, createTopPanel(textTopPanel, this.gameModel.DimensionX));
        frame.add(BorderLayout.CENTER, createButtons(buttonPanel, this.gameModel.DimensionX, this.gameModel.DimensionY));
        frame.setJMenuBar(createMenuBar());
        gameController = new Controller(this.gameModel, this);
        addController();
        gameModel.saveGame("text.txt");

        frame.pack();
		frame.setVisible(true);
    }
    
    /**
     * Creates the components for the left side of the Picross game.
     * The JPanel that will hold the components must be passed in.(param left)
     * The required amount of hint labels must be passed in.(param y)
     * All components will adhere to BorderLayout.LINE_START
     * 
     * @param left
     * @param y
     * @return Left side components of picross game
     */
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

    /**
     * Creates the components for the right side of the Picross game.
     * The JPanel that will hold the components must be passed in.(param right)
     * All components will adhere to BorderLayout.LINE_END
     * 
     * @param right
     * @return Left side components of picross game
     */
    private JPanel createRightPanel(JPanel right) {
        right.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel timerLabel = new JLabel(TXTTM+": ");
        TimerBox = new JTextField("00:00");
        TimerBox.setEditable(false);

        JLabel pointsLabel = new JLabel(TXTPTS+": ");
        PointsBox = new JTextField("0");
        PointsBox.setEditable(false);
        
        resetButton = new JButton(text[3]);
        
		JScrollPane textArea1 = new JScrollPane(Area1);  
		textArea1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Area1.setEditable(false);

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;        
        //right.add(new JLabel(new ImageIcon("bin/PICROSS1.png")), c);
        right.add(new JLabel(new ImageIcon("imgfolder/PICROSS1.png")), c);

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

    /**
     * Creates the components for the top side of the Picross game.
     * The JPanel that will hold the components must be passed in.(param top)
     * The required amount of hint labels must be⌈ passed in.(param x)
     * All components will adhere to BorderLayout.PAGE_START
     * 
     * @param top
     * @param x
     * @return Top side components of picross game
     */
    private JPanel createTopPanel(JPanel top, int x) {
        top.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        markButton = new JCheckBox(BTMARK);

        LanguageBox = new JComboBox<Object>(Languages);
		LanguageBox.setSelectedIndex(0);

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
        top.add(LanguageBox);
        return top;
    }

    /**
     * Creates the button components for the Picross game.
     * The JPanel that will hold the button components must be passed in.(param buttons)
     * The horizontal quantity of buttons required must be passed in.(param x)
     * The vertical quantity of buttons required must be passed in.(param y)
     * All buttons components will adhere to GridLayout and BorderLayout.PAGE_CENTER
     * 
     * @param buttons
     * @param x
     * @param y
     * @return Button components of picross game
     */
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

    /**
     * Creates the menu bar for the picross game
     * 
     * @return A Menu Bar
     */
    private JMenuBar createMenuBar() {
        menuBar = new JMenuBar();

        Game = new JMenu(MENUTAB1);
        Help = new JMenu(MENUTAB2);
        Client = new JMenu(MENUTAB3);

        New = new JMenuItem(MENUNW, newFileImg); 
		New.setAccelerator(keyNew);
		New.setMnemonic(KeyEvent.VK_N);
		New.setActionCommand(MENUNW);

        loadGame = new JMenuItem(MENULG);

        saveGame = new JMenuItem(MENUSG);
		
        Solution  = new JMenuItem(MENUSL);
        Solution.setAccelerator(keySolution);
        Solution.setIcon(solFileImg);

        Exit = new JMenuItem(MENUEX);
        Exit.setIcon(extFileImg); 

        Colors = new JMenuItem(MENUCLRS);
        Colors.setIcon(clrFileImg);

        About = new JMenuItem(MENUABT);
        About.setIcon(abtFileImg);

        ClientConfig = new JMenuItem(MENUCFGC);

        newConnect = new JMenuItem("New Connection");
		disconnect = new JMenuItem("Disconnect");
        disconnect.setEnabled(false);

        Game.add(New);
        Game.add(loadGame);
        Game.add(saveGame);
        Game.add(Solution);
        Game.add(Exit);

        Help.add(Colors);
        Help.add(About);

        Client.add(ClientConfig);

        menuBar.add(Game);
        menuBar.add(Help);
        menuBar.add(Client);

        return menuBar;
    }

    /**
     * Creates GridBagConstraints
     * 
     * @param x
     * @param y
     * @return GridBagConstraints
     */
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

    /**
     * Creates a new line of text inside the event log of the picross game
     * The desired text to be printed must be passed in.(param text) 
     * 
     * @param text
     */
    public void createLogTextNL(String text) {
        Area1.setText(Area1.getText() + "\n" + text);
        System.out.println("New Line");
    }

    public void createLogText(String text) {
        Area1.setText(Area1.getText() + "\n" + text);
    }
    
    /**
     * Adds a controller to each component that requires one. 
     */
    private void addController() {
        LanguageBox.setActionCommand("languageBox");
        LanguageBox.addActionListener(gameController);
        resetButton.setActionCommand("resetButton");
        resetButton.addActionListener(gameController);
        for (int i = 0; i < gameModel.DimensionX; i++) {
            for (int j = 0; j < gameModel.DimensionY; j++) {
                areaButtons[i][j].setActionCommand("gridButton " + i + " " + j);
                areaButtons[i][j].addActionListener(gameController);
            }
        }
        New.setActionCommand("New");
        New.addActionListener(gameController);
        loadGame.setActionCommand("loadGame");
        loadGame.addActionListener(gameController);
        saveGame.setActionCommand("saveGame");
        saveGame.addActionListener(gameController);

    }
    
    /**
     * Sets values of two arrays based on what button component has been clicked.
     * The vertical position of the button components must be passed in. (param i)
     * The horizontal position of the button components must be passed in. (param j)
     * 
     * @param i
     * @param j
     */
    public void squareClicked(int i, int j) {
        if (gameModel.Board[i][j] && !markButton.isSelected()) {// correct guess of occupied pattern space
            changeColor(i, j, correctSelected);// green
            userSelected[i][j] = true;
            userMarked[i][j] = false;
        } else if (!gameModel.Board[i][j] && !markButton.isSelected()) {// correct guess of non-occupied pattern space
            changeColor(i, j, incorrectColor);// red
            userSelected[i][j] = true;
            userMarked[i][j] = false;
        } else if (gameModel.Board[i][j] && markButton.isSelected()) {// incorrect guess of non-occupied pattern space
            changeColor(i, j, incorrectColor);// red
            userSelected[i][j] = false;
            userMarked[i][j] = true;
        } else if (!gameModel.Board[i][j] && markButton.isSelected()) {// incorrect guess of occupied pattern space
            changeColor(i, j, correctMarked);// yellow
            userSelected[i][j] = false;
            userMarked[i][j] = true;
        }
        isComplete();
    }

    private boolean isComplete() {
        for (int i = 0; i < gameModel.DimensionX; i++) {
            for (int j = 0; j < gameModel.DimensionY; j++) {
                if (gameModel.Board[i][j] && !userSelected[i][j]) {
                    return false;
                } else if (!gameModel.Board[i][j] && userSelected[i][j]) {
                    return false;
                } else if (!gameModel.Board[i][j] && !userMarked[i][j]) {
                    return false;
                } else if (gameModel.Board[i][j] && userMarked[i][j]) {
                    return false;
                }
            }
        }
        victoryScreen();
        return true;
    }

    private void victoryScreen() {
        JPanel victoryPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        victoryPanel.setLayout(layout);
        victoryImage = new ImageIcon("imgfolder\\wingame.png");
        JButton button = new JButton();
        button.setIcon(victoryImage);
        victoryPanel.add(button, BorderLayout.CENTER);
        JFrame victoryFrame = new JFrame();
        victoryFrame.setMinimumSize(new Dimension(748,343));
        victoryFrame.add(victoryPanel);
        victoryFrame.setVisible(true);
    }

    private void splashScreen() {
        JPanel victoryPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        victoryPanel.setLayout(layout);
        victoryImage = new ImageIcon("imgfolder\\piccross.png");
        JButton button = new JButton();
        button.setIcon(victoryImage);
        victoryPanel.add(button, BorderLayout.CENTER);
        JFrame victoryFrame = new JFrame();
        victoryFrame.setMinimumSize(new Dimension(748,343));
        victoryFrame.add(victoryPanel);
        victoryFrame.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (Exception e){
        }
        victoryFrame.setVisible(false);
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
                if (board[j][i]) {// if position has 1 
                    System.out.println(num);
                    num++; //increment number
                } else if (!board[j][i] && num != 0) { // if position isn't occupied
                        System.out.println(text + Integer.toString(num) + " ");
                        text = text + Integer.toString(num) + " ";// add 
                    
                    num = 0; 
                } else if (j+1 == y && text == "") {
                    text = "0";
                }
            }
            if (num > 0) {
                System.out.println(text + Integer.toString(num));
                        text = text + Integer.toString(num);
            } else if ((text.length() % 2 == 0) && text.length() % 3 == 0 ) {
                //text = text + "mark";
                text = text + text.substring(0, text.length() - 2);
            }
            num = 0;
            //text = text + text.substring(0, text.length() -2);
            AreaT[i].setText(text);
        }
        for (int i = 0; i < y; i++) {
            String text = "";
            int num = 0;
            for (int j = 0; j < y; j++) {
                if (board[i][j]) {
                    System.out.println(num);
                    num++;
                } else if (!board[i][j] && num > 0) {
                        System.out.println(text + Integer.toString(num) + " ");
                        text = text + Integer.toString(num) + " ";
                    
                    num = 0; 
                } else if (j+1 == y && text == "") {
                    text = "0";
                }
            }
            if (num > 0) {
                System.out.println(text + Integer.toString(num));
                        text = text + Integer.toString(num);
            } else if ((text.length() % 2 == 0) && text.length() % 3 == 0 ) {
                text = text + text.substring(0, text.length() - 2);
            }
            num = 0;
            //text = text + text.substring(0, text.length() -2);
            AreaL[i].setText(text);
        }
    }

    public void updateInterface(int langIndex) {
        System.out.println("");
        String lang = "";
        String country = "";
        switch(langIndex) {
            case 0:
                lang = "en";
                country = "US";
                System.out.println(country);
                break;
            case 1:
                lang = "fr";
                country = "FR";
                System.out.println(country);
                break;
        }
         
        try { //TODO change resource language files to conform with what we need.
            currentLocale = new Locale.Builder().setLanguage(lang).setRegion(country).build();
            texts = ResourceBundle.getBundle(SYSTEMMESSAGES, currentLocale);
            TITLE = texts.getString("TITLE");
            LANG1 = texts.getString("LANG1");
            LANG2 = texts.getString("LANG2");
            LANG3 = texts.getString("LANG3");
            Languages[0] = LANG1;
            Languages[1] = LANG2; 
            Languages[2] = LANG3;  
            BUTOK = texts.getString("BUTOK");
            BUTCANCEL = texts.getString("BUTCANCEL");
            MENUTAB1 = texts.getString("MENUTAB1");
            MENUTAB2 = texts.getString("MENUTAB2");
            MENUTAB3 = texts.getString("MENUTAB3");
            MENUNW = texts.getString("MENUNW");
            MENULG = texts.getString("MENULG");
            MENUSG = texts.getString("MENUSG");
            MENUSL = texts.getString("MENUSL");
            MENUEX = texts.getString("MENUEX");
            MENUCLRS = texts.getString("MENUCLRS");
            MENUABT = texts.getString("MENUABT");
            MENUCFGC = texts.getString("MENUCFGC");
            BTMARK = texts.getString("BTMARK");
            BTRST = texts.getString("BTRST");
            TXTTM = texts.getString("TXTTM");
            TXTPTS = texts.getString("TXTPTS");
        } catch(Exception e) {
            System.out.println("Language file is not found");
        }
    }


    public int getLangBoxIndex() {
        return LanguageBox.getSelectedIndex();
    }
}
