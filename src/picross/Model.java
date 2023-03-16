package src.picross;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * TODO This class handles the configuration string (00100, 00100, 11111, 01110, 01010 or 4, 4, 31, 14, 10),
 * the Dimensions of the game (number of rows/columns), the board that holds game buttons( a 2d array most likely),
 *
 */
public class Model {
    public int DimensionX = 5; //5x5 as default
    public int DimensionY = 5;
    private JTextArea Area1 = new JTextArea(30, 10);
    //private JButton[][] areaButtons = new JButton[5][5];
    private JButton areaButton;
    private JCheckBox markButton = new JCheckBox();
    //private Boolean[][] selectedButton = new Boolean[5][5];
    private JButton resetButton;
    private JTextField TimerBox, PointsBox;
    private JLabel AreaL1, AreaL2, AreaL3, AreaL4, AreaL5, AreaT1, AreaT2, AreaT3, AreaT4, AreaT5;
    private JComboBox<Object> LanguageBox;
    public int test = 100;

    public boolean[][] Board = new boolean[DimensionX][DimensionY];
    Model() {

    }
}
