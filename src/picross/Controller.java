package src.picross;

/*
 * TODO This class creates the binary string for the game pattern and starts the game, 
 * Manages the timer, Manages the actionevents
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controller /*implements ActionListener*/{
    private Model model;
    private View view;

    private JButton[][] areaButtons = new JButton[5][5];
    private JButton areaButton;
    private JCheckBox markButton;
    //private Boolean[][] selectedButton = new Boolean[5][5];
    private JButton resetButton;
    private JTextField TimerBox, PointsBox;
    private JLabel AreaL1, AreaL2, AreaL3, AreaL4, AreaL5, AreaT1, AreaT2, AreaT3, AreaT4, AreaT5;
    private JComboBox<Object> LanguageBox;
    /* 
    Controller(JButton resetButton, JButton[][] areaButtons, JCheckBox markButton, JTextField TimerBox, JTextField PointsBox, JComboBox<Object> LanguageBox, JLabel AreaL1, JLabel AreaL2, JLabel AreaL3, JLabel AreaL4, JLabel AreaL5, JLabel AreaT1, JLabel AreaT2, JLabel AreaT3, JLabel AreaT4, JLabel AreaT5, View gameView) {
        this.resetButton = resetButton;
        this.markButton = markButton;
        this.areaButtons = areaButtons;
        this.TimerBox = TimerBox;
        this.PointsBox = PointsBox;
        this.AreaL1 = AreaL1;
        this.AreaL2 = AreaL2;
        this.AreaL3 = AreaL3;
        this.AreaL4 = AreaL4;
        this.AreaL5 = AreaL5;
        this.AreaT1 = AreaT1;
        this.AreaT2 = AreaT2;
        this.AreaT3 = AreaT3;
        this.AreaT4 = AreaT4;
        this.AreaT5 = AreaT5;
        this.view = gameView;
    }
    */

    
    Controller(JButton resetButton, View gameView) { // right panel
        for (int i = 0; i < model.DimensionY; i++) {
            for (int j = 0; j < model.DimensionX; j++) {
                this.areaButtons[i][j] = new JButton(Integer.toString(i)+ "," + Integer.toString(j));
            }
        }
        this.view = gameView;
        this.resetButton = resetButton;
        //resetButton.addActionListener(this);
    }

    Controller(JCheckBox markButton, View gameView) { // right panel
        this.view = gameView;
        addListeners();
        this.markButton = markButton;
        System.out.println("test2");
        //this.view.addActionListeners(LanguageBox, LanguageBox);
        //markButton.addActionListener(this);
    }

    Controller(JButton[][] areaButtons, JButton areaButton, int dimx, int dimy, View gameView) {
        this.view = gameView;
        //model.areaButton = areaButton;
        //this.areaButton.addActionListener(this);
        try {
            this.areaButtons[dimx][dimy] = areaButton;
        } catch (NullPointerException e) {
            System.out.println("fail");
        }
        //areaButton.addActionListener(this);
        
    }

    Controller(Model gameModel, View gameView) {
        this.model = gameModel;
        this.view = gameView;
        //areaButtons = new JButton[model.DimensionX][model.DimensionY];
        addListeners();
        //areaButtons = new JButton[model.DimensionX][model.DimensionY];
        configureBinaryString(model.Board);
    }

    private boolean[][] configureBinaryString(boolean[][] board) {//BinaryString[i] = Integer.toBinaryString(randomNumber);
        boolean[][] BinaryString = board;
        int maxNum = (int)Math.pow(2, model.DimensionX) - 1; //determines the largest decimal number that can be represented in binary for the number of buttons in the board rows.
        for (int i = 0; i < model.DimensionY; i++) {
            int randomNumber = (int)Math.floor(Math.random() * (maxNum - 0 + 1) + 0);
            String binary = Integer.toBinaryString(randomNumber);
            System.out.println(binary.length()+(binary.length() - model.DimensionX));
            for (int j = 0; j < binary.length()+(binary.length() - model.DimensionX); j++) {
                if (binary.charAt(j) == '1') {
                    BinaryString[i][j] = true;
                } else {
                    BinaryString[i][j] = false;
                }
                
            } 
        }
        for (int i = 0; i < model.DimensionY; i++) {
            for (int j = 0; j < model.DimensionX; j++) {
                view.createLogTextNNL(String.valueOf(BinaryString[i][j]) + " ");
            }
            view.createLogTextNL("");
        }
        view.createLogTextNNL("Game Set");
        return BinaryString;
    }
    
    private void addListeners() {
        //System.out.println("test");
        view.addActionListeners(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                view.createLogTextNL("reset");
            }
        },
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.createLogTextNL("mark");
            }
        },
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (e.getSource().equals(view.areaButtons[i][j])) {
                            view.createLogTextNL(Integer.toString(i) + ", " + Integer.toString(j));
                        }
                    }
                } 
            }
        });
    } 
    /*
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(resetButton)) {
            view.createLogTextNL("reset");
        } else if (e.getSource().equals(markButton)) {
            view.createLogTextNL("mark");
        }
         
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource().equals(areaButtons[i][j])) {
                    view.createLogTextNNL(Integer.toString(i) + ", " + Integer.toString(j));
                }
            }
        }  
         
    }
    */
    
}
