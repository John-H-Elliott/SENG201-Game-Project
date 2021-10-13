package GUI;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * Code for window that displays when launching the application.
 * 
 * The window displays a large thematic image alongside a "start" and "quit button much like traditional game applications.
 * The image is covers the majority of the screen and is the central element of the window.
 * If the user presses start the game begins and the SetUpScreen is launched from where they can choose parameters for their play-through.
 * 
 * @author Ryan Boyd
 * @author John Elliot
 * @version 1.2, 19 May 2021.
 */
public class StartUpScreen {

	/**
	 * The frame for the start screen.
	 */
	private JFrame window;
	
	/**
	 * The game environment.
	 */
	private GameEnvironment environment;
	

	/**
	 * Initializes the start screen class. Which then initializes the components within it.
	 * 
	 * @param playingEnvironment		The game environment.
	 */
	public StartUpScreen(GameEnvironment playingEnvironment) {
		environment = playingEnvironment;
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
	 * Call when close the current window to get the environment to launch the new window.
	 */
	public void finishedWindow() {
		environment.closeStartupScreen(this);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame("StartUpScreen");
		window.setTitle("Trader Game");
		window.setBounds(700, 500, 1000, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		// Add the types of components onto the window frame.
		addButtons();
		addLabels();
		
	}

	
	/**
	 * Creates the background image for the start screen.
	 */
	private void addLabels() {
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(StartUpScreen.class.getResource("/Images/StartScreenBackground.jpg")));
		background.setBounds(0, 0, 984, 561);
		window.getContentPane().add(background);
		
	}

	
	/**
	 * Creates a confirmation box to ensure the player wants to quit.
	 */
    private boolean confirmQuit() {
        int selection = JOptionPane.showConfirmDialog(window, "Are you sure you want to quit?",
                "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        return selection == JOptionPane.YES_OPTION;
    }
    
    
	/**
	 * Gets the button images that are used for the start screen .
	 * 
	 * @param buttonColour		Used to get the pathway to the button.
	 * 
	 * @return image			The image icon that the button will use.
	 */
    private ImageIcon getButtonImage(String buttonColour) {
    	String pathway;
    	switch (buttonColour) {
    	case "BLUE":
    		pathway = "/Images/BlueButton.png";
    		break;
    	case "RED":
    		pathway = "/Images/RedButton.png";
    		break;
    	default:
    		pathway = "/Images/BlueButton.png";
    	}
    	
    	Image img = (new ImageIcon(StartUpScreen.class.getResource(pathway))).getImage();
		Image newImg = img.getScaledInstance(300, 58,  java.awt.Image.SCALE_SMOOTH );
		ImageIcon icon = new ImageIcon(newImg);
		return icon;
    }
    
    
	/**
	 * Creates the buttons that are displayed on the start screen.
	 * That either allow the player to quit or start the game.
	 */
	private void addButtons() {		
		JButton startButton = new JButton();
		startButton.setIcon(getButtonImage("BLUE"));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Starts the game and move to the setup screen.
				finishedWindow();
			}
		});
		startButton.setBounds(10, 440, 300, 60);
		window.getContentPane().add(startButton);
		
		JButton quitButton = new JButton();
		quitButton.setIcon(getButtonImage("RED"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (confirmQuit()){
					// Quits the game.
					System.exit(0);
				}
			}
		});
		quitButton.setBounds(674, 440, 300, 60);
		window.getContentPane().add(quitButton);
	}

}