package GUI;
import javax.swing.JOptionPane;

import MainGameLogic.*;


/**
 * This class initializes GameLogic which is used to run the main logic and
 * launches the application windows for the GUI.
 * Windows can only be accessed in the set order to ensure the game runs smoothly.
 * 
 * @author John Elliott
 * @version 0.3, 19 May 2021.
 */
public class GameEnvironment {
	// The user interface to be used by this manager environment.
	
	/**
	 * The game logic that is being used to run the game.
	 */
	private GameLogic game = new GameLogic();
	
	/**
	 * The main window screen for displaying information.
	 */
	private MainGameScreen mainWindow;
		
		
	/**
	* Returns instance of game from GameLogic.
	* @return game		The game logic.
	*/
	public GameLogic getGame(){
		return game;
	}
		
	
	/**
	 * Launches a new instance of the StartUpScreen. an introductory screen for the game.
	 */
	public void launchStartupScreen() {
		new StartUpScreen(this);
	}
		
	
	/**
	 * Closes the StartUpScreen and launches the SetUpScreen.
	 * 
	 * @param startupWindow		Instance of StartUpScreen.
	 */
	public void closeStartupScreen(StartUpScreen startupWindow) {
		startupWindow.closeWindow();
		launchSetupScreen();
	}
		
	
	/**
	 * Launches the games SetUpScreen.
	 * 
	 * This window contains many options that provide players with ways to customize their game-play. further detail on specific options
	 * and functionality is provided in the documentation for the class.
	 */
	public void launchSetupScreen() {
		new SetUpScreen(this);
	}
		
	
	/**
	 * Closes the SetUpScreen and launches the main game window.
	 * 
	 * @param setupWindow		Instance of SetUpScreen.
	 */
	public void closeSetupScreen(SetUpScreen setupWindow) {
		setupWindow.closeWindow();
		launchMainScreen();
	}
		
	
	/**
	 * Launches the games MainGameScreen.
	 * 
	 * This window contains the main game GUI and is where the user plays the game. further information and details are provided in the
	 * documentation for the class.
	 */
	public void launchMainScreen() {
		mainWindow = new MainGameScreen(this);
	}
		
	
	/**
	 * Closes the instance of the main game screen and pass the "won game" state to the end screen.
	 * 
	 * @param mainGameScreen		The main screen object.
	 * @param won					The win state.
	 */
	public void closeMainGameScreen(MainGameScreen mainGameScreen, boolean won) {
		mainGameScreen.closeWindow();
		launchEndScreen(won);
	}

	
	/**
	 * Launches the games end screen which shows the final score of the player.
	 * 
	 * @param won		The win state.
	 */
	public void launchEndScreen(boolean won) {
		new EndScreen(this, won);	
	}
		
	
	/**
	 * Ends the game.
	 * 
	 * @param endWindow		The end screen object.
	 */
	public void closeEndScreen(EndScreen endWindow) {
		endWindow.closeWindow();	
	}
	
	
	/**
	 * Launches the games sailing screen. Which allows the player to sail to a island.
	 * 
	 * @param islandIndex		The island they are wanting to sail to.
	 */
	public void launchSailScreen(int islandIndex) {
		new SailScreen(this, islandIndex);
	}
		
	
	/**
	 * Closes the sailing screen. This then makes checks with the game logic if the game is still playable.
	 * If it is it will launch the game back to the main screen. Otherwise it will close the main screen and launch the end screen.
	 * 
	 * @param sailWindow		The sail screen object.
	 * @param sailed			A boolean value representing if the player had sailed.
	 */
	public void closeSailScreen(SailScreen sailWindow, boolean sailed) {
		sailWindow.closeWindow();
		mainWindow.getFrame().setVisible(true);
		// Checked if we have sailed.
		if (sailed) {
			// If we have sailed is the game still active. This checks pirates have killed the player.
			if (!game.getGameActive()) {
				// This is a game loss.
				mainWindow.finishedWindow(false);
			} else {	
				// Checks now if the player has somewhere to go.
				if (game.canContiune()) {
					mainWindow.updateMainScreen();
				} else {
					// If they don't alerts the player. Sells all current goods to the shop and ends the game.
					JOptionPane.showMessageDialog(mainWindow.getFrame(), "There is no where left to go.");
					game.sellRemainingCargo();
					// This is a game win.
					mainWindow.finishedWindow(true);
				}
			}
		}
	}
		
	
	/**
	 * The main method that initializes this GameEnvironment object and starts the game.
	 * 
	 * @param args		Any strings received through the terminal.
	 */
	public static void main(String[] args) {
		GameEnvironment runningGame = new GameEnvironment();
		runningGame.launchStartupScreen();
	}

}