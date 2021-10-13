package GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import MainGameLogic.GameLogic;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;


/**
 * Implements the sailing screen for the game. Which allows the player to selected a route.
 * Then sail that route and it also deals with any random events that occurred during the sailing.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class EndScreen {
	
	/**
	 * The windows frame.
	 */
	private JFrame window;
	
	/**
	 * The game environment.
	 */
	private GameEnvironment environment;
	
	/**
	 * Used to determine if the player has won or not.
	 */
	private boolean wonGame;
	
	
	/**
	 * Initializes the end screen class. Which then initializes the components within it.
	 * 
	 * @param gameEnvironment		The game environment.
	 * @param won					The boolean the represents if the player has won or not.
	 */
	public EndScreen(GameEnvironment gameEnvironment, boolean won) {
		environment = gameEnvironment;
		wonGame = won;
		// Initializes the components.
		initialize();
		// Show the current window and restricts resizing.
		window.setVisible(true);
		window.setResizable(false);
		window.getContentPane().setLayout(null);
	}

	
	/**
	 * Call to close and dispose of the window when done.
	 */
	public void closeWindow() {
		window.dispose();
	}

	
	/**
	 * Call when close the current window and end the game
	 */
	public void finishedWindow() {
		environment.closeEndScreen(this);
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame("Trader Game");
		window.setBounds(700, 500, 900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		// Add components to the screen.
		buildLayout();
	}

	
	/**
	 * Adds all components to the endPanel.
	 */
	private void buildLayout() {
		// Gets the end screen information.
		GameLogic game = environment.getGame();
		String name = game.getName();
		int selectedDays = game.getSelectedDays();
		int playTime;
		// Checks if somehow the played days is greater than selected days and limits it to the selected days.
		if (selectedDays > (selectedDays - game.getDays())){
			playTime = selectedDays - game.getDays();
		} else {
			playTime = selectedDays;
		}
		
		int wealth = game.getWealth();
		// Used to determine the final score of players.
		int score = (int) (wealth * (((double) playTime) / ((double) selectedDays)));
		
		JPanel endPanel = new JPanel();
		endPanel.setBounds(0, 0, 884, 561);
		window.getContentPane().add(endPanel);
		endPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Captain: " + name);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(26, 22, 800, 50);
		endPanel.add(nameLabel);
		
		JLabel playTimeLabel = new JLabel("You selected, "+ selectedDays +", and played, "+playTime+".");
		playTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playTimeLabel.setBounds(26, 90, 800, 50);
		endPanel.add(playTimeLabel);
		
		JLabel profitLabel = new JLabel("You made "+ wealth +" coins in that time.");
		profitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profitLabel.setBounds(26, 161, 800, 50);
		endPanel.add(profitLabel);
		
		JLabel scoreLabel = new JLabel("Your final score is: "+ score);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(26, 260, 800, 80);
		endPanel.add(scoreLabel);
		
		// Checks to see if the player won or lost the game.
		// If they won they get the good ending screen otherwise they get the bad ending.
		JLabel background = new JLabel();
		if (wonGame) {
			background.setIcon(new ImageIcon(SetUpScreen.class.getResource("/Images/goodend.png")));
		} else {
			background.setIcon(new ImageIcon(SetUpScreen.class.getResource("/Images/badend.png")));
		}
		background.setBounds(0, 0, 884, 561);
		endPanel.add(background);
		
		// Add a quit button that stops the game.
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(26, 503, 143, 35);
		endPanel.add(quitButton);
		// Ends the game.
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
	}
	
}
