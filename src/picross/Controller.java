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

    Controller(Model gameModel, View gameView) {
        this.model = gameModel;
        this.view = gameView;
        addListeners();
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
        view.addActionListeners(new ActionListener() {
            @Override
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
                //view.isButton(e, 0, 0);\
                /* 
                boolean test1 = e.getSource().equals(view.isButton(e, 4, 4));
                boolean test2 = view.booleantest();
                System.out.println("test = " + test1);
                System.out.println("test = " + test2);
                if (test1 == true) { //variable needs to stay private, create isButton() method instead of this
                    view.createLogTextNL(Integer.toString(4) + ", " + Integer.toString(4));
                }
                */
                
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        //System.out.println(e.getSource().equals(view.isButton(e, i, j)));
                        //System.out.println(e.getSource().equals(view.isButton(e, i, j)));
                        if (view.isButton(e, i, j)) { //variable needs to stay private, create isButton() method instead of this
                            view.createLogTextNL(Integer.toString(i) + ", " + Integer.toString(j));
                        }
                    }
                }
            }
        });
    }
    
}
