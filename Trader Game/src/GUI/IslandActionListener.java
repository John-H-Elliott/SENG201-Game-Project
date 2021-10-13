package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


/**
 * Implements a Listener that checks to see if a JButton have has been pushed.
 * It will then swap the visibility of two panels.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class IslandActionListener implements ActionListener{
	
	/**
	 * The current frame the player is in.
	 */
	private MainGameScreen screen;
	
	/**
	 * The panel thats currently visible.
	 */
	private JPanel currentPanel;
	
	/**
	 * The panel thats not visible.
	 */
	private JPanel nextPanel;
	
	/**
	 * If this is an island button this number represents what island it is.
	 */
	private int islandNum;
	
	
    /**
     * Initializes an IslandActionListener. This constructor is used when the button is an island button.
     *
     * @param current		The panel that is currently visible.			
     * @param next			The panel that needs to be made visible.				
     * @param num			The island number.
     * @param window		The game frame.
     */
	public IslandActionListener(JPanel current, JPanel next, int num, MainGameScreen window) {
		screen = window;
		currentPanel = current;
		nextPanel = next;
		islandNum = num;
	}
	
	
    /**
     * Initializes an IslandActionListener.
     *
     * @param current		The panel that is currently visible.			
     * @param next			The panel that needs to be made visible.
     */
	public IslandActionListener(JPanel current, JPanel next) {
		currentPanel = current;
		nextPanel = next;
	}
	
	
	/**
	 * Overrides the action performed method that is called when the button is pressed.
	 * 
	 * @param e		The event that has occurred.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Swaps the visibility of the panels.
		currentPanel.setVisible(false);
		nextPanel.setVisible(true);
		// Checks to see if this was called by an island button.
		if (screen != null) {
			// Updates the map to show the changes.
			screen.updateMap(islandNum);
		}
	}
}
