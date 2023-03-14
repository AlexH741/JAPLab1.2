package src.picross;

/*
 * TODO This class creates the binary string for the game pattern and starts the game, 
 * Manages the timer, Manages the actionevents
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controller implements ActionListener{
    private Model model;
    private View view;

    private JButton[][] areaButtons;
    private JCheckBox markButton;
    //private Boolean[][] selectedButton = new Boolean[5][5];
    private JButton resetButton;
    private JTextField TimerBox, PointsBox;
    private JLabel AreaL1, AreaL2, AreaL3, AreaL4, AreaL5, AreaT1, AreaT2, AreaT3, AreaT4, AreaT5;
    private JComboBox<Object> LanguageBox;
    /*
    Controller(JButton resetButton, JButton[][] areaButtons, JCheckBox markButton, JTextField TimerBox, JTextField PointsBox, JComboBox<Object> LanguageBox, JLabel AreaL1, JLabel AreaL2, JLabel AreaL3, JLabel AreaL4, JLabel AreaL5, JLabel AreaT1, JLabel AreaT2, JLabel AreaT3, JLabel AreaT4, JLabel AreaT5) {
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
    }
    */
    
    Controller(JButton resetButton) { // right panel
        this.resetButton = resetButton;
        System.out.println(this.resetButton);
    }
    

    Controller(Model gameModel, View gameView) {
        model = gameModel;
        view = gameView;
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

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
        System.out.println(resetButton);
        System.out.println(e.getSource().equals(resetButton));
        if (e.getSource().equals(resetButton)) {
            try {
                System.out.println(view.test);
            } catch(NullPointerException g) {
                System.out.println(g + ", class invalid");
            }
            try {
                view.createLogTextNL("test");
            } catch(NullPointerException g) {
                System.out.println(g + ", function invalid");
            }
        }
        /*
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getSource().equals(areaButtons[i][j])) {
                    view.createLogTextNNL("test");
                }
            }
        }
        */    
    }
}
