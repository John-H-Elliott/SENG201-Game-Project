package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import MainGameLogic.GameLogic;
import MainGameLogic.RandomEvents;
import MainGameLogic.SetUp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * Implements the sailing screen for the game. Which allows the player to selected a route.
 * Then sail that route and it also deals with any random events that occurred during the sailing.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class SailScreen {
	// All components used when creating the screen.
	// Panels and layered panels.
	private JLayeredPane EventsLayeredPanel;
	private JPanel pickRoutePanel;
	private JPanel pirateEventsPanel;
	private JPanel normalEventsPanel;
	// Text panels.
	private JTextPane eventText;
	private JTextPane pirateText;
	// Buttons.
	private JButton continueButton;
	
	/**
	 * The windows frame.
	 */
	private JFrame window;
	
	/**
	 * The game environment.
	 */
	private GameEnvironment environment;
	
	/**
	 * The current games logic.
	 */
	private GameLogic game;
	
	/**
	 * The island the player is traveling to.
	 */
	private int newIsland;
	
	/**
	 * The random event that occurred.
	 */
	private RandomEvents outcome;
	
	
	/**
	 * Initializes the sail screen class. Which then initializes the components within it.
	 * 
	 * @param playingEnvironment		The game environment.
	 * @param islandIndex				The island the player is sailing to.
	 */
	public SailScreen(GameEnvironment playingEnvironment, int islandIndex) {
		environment = playingEnvironment;
		game = environment.getGame();
		newIsland = islandIndex;
		// Initializes the components.
		initialize();
		// Show the current window and restricts resizing.
		window.setVisible(true);
		window.setResizable(false);
	}

	
	/**
	 * Call to close and dispose of the window when done.
	 */
	public void closeWindow() {
		window.dispose();
	}
	
	
	/**
	 * Call when close the current window to get the environment to return to the main screen.
	 * 
	 * @param sailed		The boolean the represents if the player has sailed or not.
	 */
	public void finishedWindow(boolean sailed) {
		environment.closeSailScreen(this, sailed);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame("Trader Game");
		window.setBounds(700, 500, 700, 350);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		window.setVisible(true);
		// Add the panels and layered panels to this screens frame.
		buildPanels();
	
	}
	
	
	/**
	 * Builds all panels and then initializes all their components for the window.
	 */
	private void buildPanels() {
		// Where the player picks the route.
		pickRoutePanel = new JPanel();
		pickRoutePanel.setBounds(0, 0, 684, 311);
		window.getContentPane().add(pickRoutePanel);
		pickRoutePanel.setLayout(null);
		// Holds all event panels.
		EventsLayeredPanel = new JLayeredPane();
		EventsLayeredPanel.setBounds(0, 0, 684, 311);
		EventsLayeredPanel.setVisible(false);
		window.getContentPane().add(EventsLayeredPanel);
		// Shown during a pirate event.
		pirateEventsPanel = new JPanel();
		pirateEventsPanel.setBounds(0, 0, 684, 257);
		EventsLayeredPanel.add(pirateEventsPanel);
		pirateEventsPanel.setLayout(null);
		pirateEventsPanel.setVisible(false);
		// The default event panel.
		normalEventsPanel = new JPanel();
		normalEventsPanel.setBounds(0, 0, 684, 257);
		EventsLayeredPanel.add(normalEventsPanel);
		normalEventsPanel.setLayout(null);
		normalEventsPanel.setVisible(false);
		// Used to continue the game after the event.
		continueButton = new JButton("Ok");
		continueButton.setBounds(525, 270, 150, 30);
		continueButton.setEnabled(false);
		EventsLayeredPanel.add(continueButton);
		
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(true);
			}
		});
		
		// Adds all components of the panels.
		addPickRouteComponents();
		addPirateEventsComponents();
		addNormalEventsComponents();
	}

	
	/**
	 * Adds all components that are used when a player is picking their route to sail.
	 */
	private void addPickRouteComponents() {
		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 270, 150, 30);
		pickRoutePanel.add(backButton);
		// As the player didn't sail returns false. And goes back to the same screen without an update.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow(false);
			}
		});
		
		// Checks if the player can sail a given route and locks it if they can't.
		JButton lowRiskButton = new JButton("Easy Route");
		lowRiskButton.setBounds(56, 58, 200, 80);
		pickRoutePanel.add(lowRiskButton);
		if (!game.canSail(newIsland, SetUp.EASY)) {
			lowRiskButton.setEnabled(false);
		}
		// Creates an event using the easy route.
		lowRiskButton.addActionListener(riskButtonListener(SetUp.EASY));
		// Checks if the player can sail a given route and locks it if they can't.
		JButton highRiskButton = new JButton("Hard Route");
		highRiskButton.setBounds(400, 58, 200, 80);
		pickRoutePanel.add(highRiskButton);
		if (!game.canSail(newIsland, SetUp.HARD)) {
			highRiskButton.setEnabled(false);
		}
		// Creates an event using the hard route.
		highRiskButton.addActionListener(riskButtonListener(SetUp.HARD));
		
		// Labels about the difference in the days between the routes.
		String lowRisk = game.getRouteInfo(newIsland, SetUp.EASY);
		JLabel lowRiskLabel = new JLabel("<html><b><font size=3 color=rgb(1,1,1)>  "+ lowRisk +"   </font></b></html>");
		lowRiskLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lowRiskLabel.setBounds(56, 170, 200, 70);
		pickRoutePanel.add(lowRiskLabel);
		
		String highRisk = game.getRouteInfo(newIsland, SetUp.HARD);
		JLabel highRiskLabel = new JLabel("<html><b><font size=3 color=rgb(1,1,1)>  "+ highRisk +"   </font></b></html>");
		highRiskLabel.setHorizontalAlignment(SwingConstants.CENTER);
		highRiskLabel.setBounds(400, 170, 200, 70);
		pickRoutePanel.add(highRiskLabel);
	}
		
	
	/**
	 * This listener is used by the risk buttons to make a event and then to determine the outcome type.
	 * It will then based on the outcome type launch the relevant event panel.
	 * 
	 * @param risk			The route taken.
	 * 
	 * @return listener		The action listener used by the buttons.
	 */
	private ActionListener riskButtonListener(int risk) {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome = game.sail(newIsland, risk);
				pickRoutePanel.setVisible(false);
				EventsLayeredPanel.setVisible(true);
				// Event was pirates. So pirates panel is launched.
				if (outcome.getType() == SetUp.PIRATES) {
					pirateEventsPanel.setVisible(true);
					pirateText.setText(outcome.getOutCome());
				} else {
					normalEventsPanel.setVisible(true);
					eventText.setText(outcome.getOutCome());
					// As there is nothing to resolve the continue button is enabled.
					continueButton.setEnabled(true);
				}
			}
		};
		return listener;
	}
	
	
	/**
	 * Adds all components that are used when a player has encounter a pirate event.
	 * It also has the logic for dealing with the winner of the event.
	 */
	private void addPirateEventsComponents() {
		pirateText = new JTextPane();
		pirateText.setBounds(10, 11, 500, 235);
		pirateText.setEditable(false);
		pirateEventsPanel.add(pirateText);
		
		JButton rollButton = new JButton("Roll The Dice");
		rollButton.setBounds(524, 11, 150, 30);
		pirateEventsPanel.add(rollButton);
		
		// This listener rolls a dice in the random event object.
		// After either the player (+3) or pirates (-3) have won 3 times the game is decide. 
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outcome.rollDice();
				// Adds outcome to the screen.
				pirateText.setText(pirateText.getText() + "\n" + outcome.getOutCome());
				
				// The player has won.
				if (outcome.getWon() == 3) {
					rollButton.setEnabled(false);
					continueButton.setEnabled(true);
					pirateText.setText(outcome.getOutCome());
				// The player has lost.
				} else if (outcome.getWon() == -3) {
					rollButton.setEnabled(false);
					continueButton.setEnabled(true);
					// This then checks if the player has enough loot to stay "alive".
					game.determinePirateGameActive(outcome);
					pirateText.setText(outcome.getOutCome());
				}
			}
		});
	
		// Displays the cannons currently owned.
		JLabel cannonsOwnedLabel = new JLabel("Cannons Owned: " + game.getShipUpgrades("Cannons").getQuantity());
		cannonsOwnedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cannonsOwnedLabel.setBounds(527, 47, 147, 22);
		pirateEventsPanel.add(cannonsOwnedLabel);
		
	}
		
	
	/**
	 * Adds all components that are used when a player has encounter a normal event.
	 * It just displays the text of the outcome to the player and allows them to continue.
	 */
	private void addNormalEventsComponents() {
		eventText = new JTextPane();
		eventText.setBounds(10, 11, 664, 235);
		eventText.setEditable(false);
		normalEventsPanel.add(eventText);
	}
	
}