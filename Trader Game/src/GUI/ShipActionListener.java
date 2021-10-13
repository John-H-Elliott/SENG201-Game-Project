package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements a Listener that checks to see if a JButton has been pushed.
 * This button listener allows the player to set their ship choice.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class ShipActionListener implements ActionListener{
	
	/**
	 * The ship number the player wants.
	 */
	int shipNum;
	
	/**
	 * The current frame the player is in.
	 */
	SetUpScreen window;
	
	
    /**
     * Initializes an ShipActionListener.
     *
     * @param num			The ship number.
     * @param screen		The game frame.
     */
	public ShipActionListener(int num, SetUpScreen screen) {
		shipNum = num;
		window = screen;
	}
	
	
	/**
	 * Overrides the action performed method that is called when the button is pressed.
	 * 
	 * @param e		The event that has occurred.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Sets the ship type.
		window.setShipNum(shipNum);
		// As the ship buttons are also the last thing a player needs to pick,
		// it close the setup and starts the main game window.
		window.finishedWindow();
	}
	
}
