package src.picross;

/*
 * TODO This class creates the binary string for the game pattern and starts the game, 
 * Manages the timer, Manages the actionevents
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Controller /*implements ActionListener*/{
    private Model model;
    private View view;

    boolean markStatus = false;

    Controller(Model gameModel, View gameView) {
        this.model = gameModel;
        this.view = gameView;
        addListeners();
        configureBinaryString(model.Board);
        view.changeLabelText(model.Board, model.DimensionX, model.DimensionY);
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
        view.changeLabelText(BinaryString, model.DimensionX, model.DimensionY);
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
                //view.createLogTextNL("mark");
                if (markStatus == false) {
                    markStatus = true;
                } else if (markStatus == true) {
                    markStatus = false;
                }
                
            }
        },
        new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < model.DimensionX; i++) {
                    for (int j = 0; j < model.DimensionX; j++) {
                        if (view.isButton(e, i, j)) {
                            if (model.Board[i][j] && markStatus) {//correct guess of occupied pattern space
                                view.changeColor(i, j, new Color(0x7CCD7C));//green
                            } else if (!model.Board[i][j] && !markStatus) {//correct guess of non-occupied pattern space
                                view.changeColor(i, j, new Color(255, 255, 0));//yellow
                            } else if (model.Board[i][j] && !markStatus) {//incorrect guess of non-occupied pattern space
                                view.changeColor(i, j, new Color(255, 0, 0));//red
                            } else if (!model.Board[i][j] && markStatus) {//incorrect guess of occupied pattern space
                                view.changeColor(i, j, new Color(255, 0, 0));//red
                            }
                        }
                    }
                }
            }
        });
    }
}
