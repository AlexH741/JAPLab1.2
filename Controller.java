/*
 * TODO This class creates the binary string for the game pattern and starts the game, 
 * Manages the timer, Manages the actionevents
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;
    Controller(Model gameModel, View gameView) {
        model = gameModel;
        view = gameView;
        configureBinaryString(model.Board);
    }

    private boolean[][] configureBinaryString(boolean[][] board) {
        boolean[][] BinaryString = board;
        int maxNum = 2^model.DimensionX; //determines the largest decimal number that can be represented in binary for the number of buttons in the board rows.
        for (int i = 0; i < model.DimensionY; i++) {
            int randomNumber = (int)Math.floor(Math.random() * (maxNum - 0 + 1) + 0);
            //BinaryString[][] = randomNumbe.parseBoolean()
        }
        return BinaryString;
    }

    public void actionPerformed(ActionEvent e) {
    }

    //int BinaryString[][];
}
