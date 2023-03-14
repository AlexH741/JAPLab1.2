package src.picross;

/*
 * TODO This class creates the binary string for the game pattern and starts the game, 
 * Manages the timer, Manages the actionevents
 */

import java.awt.event.ActionEvent;

public class Controller {
    private Model model;
    private View view;
    Controller(Model gameModel, View gameView) {
        model = gameModel;
        view = gameView;
        configureBinaryString(model.Board);
    }

    private boolean[][] configureBinaryString(boolean[][] board) {//BinaryString[i] = Integer.toBinaryString(randomNumber);
        boolean[][] BinaryString = board;
        int maxNum = (int)Math.pow(2, model.DimensionX); //determines the largest decimal number that can be represented in binary for the number of buttons in the board rows.
        for (int i = 0; i < model.DimensionY; i++) {
            int randomNumber = (int)Math.floor(Math.random() * (maxNum - 0 + 1) + 0);
            String binary = Integer.toBinaryString(randomNumber);
            for (int j = 0; j < binary.length()+(binary.length() - model.DimensionX); j++) {
                if (binary.charAt(j) == '1') {
                    BinaryString[i][j] = true;
                } else {
                    BinaryString[i][j] = false;
                }
                
            } 
        }
        return BinaryString;
    }

    public void actionPerformed(ActionEvent e) {
    }

    //int BinaryString[][];
}
