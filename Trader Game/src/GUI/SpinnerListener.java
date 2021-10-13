package GUI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import MainGameLogic.GameLogic;
import MainGameLogic.Upgrade;

/**
 * Implements a Listener that checks to see if a JSpinners have had its values changed.
 * So to make sure only valid amounts are allowed to be entered.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class SpinnerListener implements ChangeListener{
	
	/**
	 * The upgrade button that is locked when the amount entered is invalid.
	 */
	JButton button;
	
	/**
	 * The GameLogic that is used to check if the amount can be payed for.
	 */
	GameLogic game;
	
	/**
	 * The type of upgrade the spinner is related to.
	 */
	String type;
	
	/**
	 * A display of the price of the upgrades.
	 */
	JLabel label;
	
	
    /**
     * Initializes an SpinnerListener.
     *
     * @param newButton			The upgrade button related to the spinner.
     * @param newGame         	The game logic.
     * @param upgradeType		The type of upgrade the spinner is related to.
     * @param priceLabel		The price to buy the upgrades.
     */
	public SpinnerListener(JButton newButton, GameLogic newGame, String upgradeType, JLabel priceLabel) {
		button = newButton;
		game = newGame;
		type = upgradeType;
		label = priceLabel;
	}

	/**
	 * Overrides the state changed method that activates when the state of the spinner has changed.
	 * 
	 * @param e		The event that has occurred.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		// That spinner that has changed.
		JSpinner spinner = (JSpinner) e.getSource();
        int value = (int) spinner.getValue();
        // Checks the value is non-negative.
        if (value < 0) {
        	spinner.setValue(0);
        	return;
        } 
        // Gets the price of the total amount of a given upgrade.
        Upgrade currentUpgrade = game.getShipUpgrades(type);
        int price = (currentUpgrade.getPrice() * value);
        // Update label price.
        label.setText("Cost: " + price);
        
        // Checks if the player can afford that amount and that its at least one upgrade.
        // Then allows the purchase button to be enabled.
        if ((price > 0) && (game.getWealth() >= price)){
        	button.setEnabled(true);
        } else {
        	button.setEnabled(false);
        }
	}
	
}
