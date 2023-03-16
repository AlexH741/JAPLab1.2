package src.picross;

/**
 * Rough draft of Picross game UI
 * 
 * CET - CS Academic Level 4
 * Student Names: Bich Khe Hoang, Alex Holmes
 * Student Numbers: , 041026437
 * Section 302
 * Course: CST8221 - Java Application Programming
 * Lab Professor: 
 */

public class Game{ //TODO: Javadoc

	public static void main(String[] args) {
		Model gameModel = new Model();
		View gameView = new View(gameModel.DimensionX, gameModel.DimensionY);
		Controller gameController = new Controller(gameModel, gameView);
		///UI newUI = new UI();
	}
}
