/*
 * TODO This class handles the configuration string (00100, 00100, 11111, 01110, 01010 or 4, 4, 31, 14, 10),
 * the Dimensions of the game (number of rows/columns), the board that holds game buttons( a 2d array most likely),
 *
 */
public class Model {
    public int DimensionX = 5; //5x5 as default
    public int DimensionY = 5;
    public boolean[][] Board = new boolean[DimensionX][DimensionY];
    Model() {

    }
}
