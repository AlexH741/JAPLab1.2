package src.picross;
/**
 * Rough draft of Picross game UI
 * 
 * CET - CS Academic Level 4
 * Student Names: Bich Khe Hoang, Alex Holmes
 * Student Numbers: 040990843 , 041026437
 * Section 302
 * Course: CST8221 - Java Application Programming
 * Lab Professor: Daniel Cormier
 */

public class Game{

	/**
	* The main method of the Game class display the UI for the game
    * @param args The command line arguments.
	*/
	public static void main(String[] args) {
		Model gameModel = new Model();
		View gameView = new View();
		Controller gameController = new Controller(gameModel, gameView);
		//UI newUI = new UI();
	}
}
