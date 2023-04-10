package src.picross;

/*
 * TODO This class creates the binary string for the game pattern and starts the game, 
 * Manages the timer, Manages the actionevents
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;

import java.awt.Color;

public class Controller implements ActionListener {
    private Model model;
    private View view;

    boolean markStatus = false;

    Controller(Model gameModel, View gameView) {
        this.model = gameModel;
        this.view = gameView;
        // addListeners();
        configureBinaryString(model.Board);
        view.changeLabelText(model.Board, model.DimensionX, model.DimensionY);
    }

    private boolean[][] configureBinaryString(boolean[][] board) {// BinaryString[i] =
                                                                  // Integer.toBinaryString(randomNumber);
        boolean[][] BinaryString = board;
        int maxNum = (int) Math.pow(2, model.DimensionX) - 1; // determines the largest decimal number that can be
                                                              // represented in binary for the number of buttons in the
                                                              // board rows.
        for (int i = 0; i < model.DimensionY; i++) {
            int randomNumber = (int) Math.floor(Math.random() * (maxNum - 0 + 1) + 0);
            String binary = Integer.toBinaryString(randomNumber);
            System.out.println(binary.length() + (binary.length() - model.DimensionX));
            for (int j = 0; j < binary.length() + (binary.length() - model.DimensionX); j++) {
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

    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event instanceof AbstractButton) {
            AbstractButton event2 = (AbstractButton) event;
            String command = event2.getActionCommand();
            String commands[] = command.split(" ");
            switch (commands[0]) {
                case "resetButton":
                    view.createLogTextNL("reset");
                    break;
                case "markButton":
                    if (markStatus == false) {
                        markStatus = true;
                    } else if (markStatus == true) {
                        markStatus = false;
                    }
                    break;
                case "gridButton":
                    int i = Integer.parseInt(commands[1]);
                    int j = Integer.parseInt(commands[2]);
                    view.squareClicked(i, j);
                    break;
                case "languageBox":
                    System.out.println("mark");
                    view.updateInterface(view.getLangBoxIndex());
                    break;
                case "New":
                    configureBinaryString(model.Board);
                    view.changeLabelText(model.Board, model.DimensionX, model.DimensionY);
                    break;
                case "Solution":
                    break;
                case "Exit":
                    break;
                case "Colors":
                    break;
                case "About":
                    break;
                case "loadGame":
                    model.loadGame("test.txt");
                    view.changeLabelText(model.Board, model.DimensionX, model.DimensionY);
                    break;
                case "saveGame":
                    break;
                case "configureClient":
                    view.createClientScreen();
                    break;
                case "connectGame":
                    view.startClient();
                    break;
                case "endConnection":
                    break;
                case "newNetGame": // Creates a new game
                    break;
                case "sendNetGame": //
                    break;
                case "recieveNetGame":
                    break;
                case "sendData":
                    break;
                case "playButton":
                    break;

            }
        }
    }
}
