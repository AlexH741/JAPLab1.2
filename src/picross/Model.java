package src.picross;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

import java.net.*;
import java.io.*;

/*
 * TODO This class handles the configuration string (00100, 00100, 11111, 01110, 01010 or 4, 4, 31, 14, 10),
 * the Dimensions of the game (number of rows/columns), the board that holds game buttons( a 2d array most likely),
 *
 */
public class Model {
    public int DimensionX = 5; // 5x5 as default
    public int DimensionY = 5;
    private JTextArea Area1 = new JTextArea(30, 10);
    // private JButton[][] areaButtons = new JButton[5][5];
    private JButton areaButton;
    private JCheckBox markButton = new JCheckBox();
    // private Boolean[][] selectedButton = new Boolean[5][5];
    private JButton resetButton;
    private JTextField TimerBox, PointsBox;
    private JLabel AreaL1, AreaL2, AreaL3, AreaL4, AreaL5, AreaT1, AreaT2, AreaT3, AreaT4, AreaT5;
    private JComboBox<Object> LanguageBox;
    public int test = 100;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static final String PROTOCOL_SEPARATOR = ""; // TODO give defaults
    private static final String PROTOCOL_END = "";
    private static final String PROTOCOL_SENDGAME = "";
    private static final String PROTOCOL_RECVGAME = "";
    private static final String PROTOCOL_DATA = "";
    private static final String FIELD_SEPARATOR = "";

    public boolean[][] Board = new boolean[DimensionX][DimensionY];

    Model() {

    }

    /**
     * loads previous game configuration from file
     * 
     * @param filename
     */
    public void loadGame(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);// slplit(,) to create string to make line, charat to find each 1 in the line, 1
                                        // = true
            String s = scanner.nextLine();
            String[] sarray = s.split(",");
            DimensionX = sarray[0].length();
            DimensionY = sarray.length;
            Board = new boolean[DimensionX][DimensionY];

            for (int i = 0; i < this.DimensionX; i++) {
                for (int j = 0; j < this.DimensionY; j++) {
                    char c = sarray[i].charAt(j);
                    if (c == '1') {
                        Board[i][j] = true;
                    } else {
                        Board[i][j] = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            scanner.close();
        }
    }

    public void saveGame(String filename) {
        File file = new File(filename);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            for (int i = 0; i < this.DimensionX; i++) {
                for (int j = 0; j < this.DimensionY; j++) {
                    if (Board[i][j]) {
                        writer.print(1);
                    } else {
                        writer.print(0);
                    }
                }
                writer.print(",");
            }
            writer.println("");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            writer.close();
        }
    }

    public void connectServer(String hostname, String portnum, String username) {
        listenSocket(hostname, portnum);
        out.println("some text");// TODO change to connection protocol
        try { // TODO recieve confirmation from server, confirmation will appear as an echo
            String response = in.readLine();
            System.out.println("Recieved: " + response);
        } catch (IOException e) {
            System.out.println("Read has failed");
        }
    }

    public void sendGame() {
        out.println(""); // TODO change to send game protocol
        try { // TODO recieve confirmation from server, confirmation will appear as an echo
            String response = in.readLine();
            System.out.println("Recieved: " + response);
        } catch (IOException e) {
            System.out.println("Read has failed");
        }
    }

    private void listenSocket(String hostname, String portnum) {
        try {
            socket = new Socket(hostname, Integer.valueOf(portnum));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + hostname);
        } catch (IOException e) {
            System.out.println("No I/O");
        }
    }
}
