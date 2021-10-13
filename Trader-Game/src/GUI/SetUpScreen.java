package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import MainGameLogic.GameLogic;


/**
 * This class builds the SetUpScreen window which gives the player a GUI where they can decide on
 * certain options for their game such as: choosing their displayed name in game, choosing
 * how many days they want the game to last for (game length) and choosing the ship they would
 * like to play the game with(this affects certain statistics for the player).
 *
 * @author John Elliot
 * @version 0.3, 19 May 2021.
 */
public class SetUpScreen {

	/**
	 * The text field used for the players name.
	 */
	private JTextField txtName;
	
	/**
	 * A layered panel.
	 */
	private JLayeredPane layeredPane;
	
	/**
	 * A slider used to select the days to play.
	 */
	private JSlider slider;
	
	/**
	 * The readout of the slider.
	 */
	private JLabel sliderReadOut;
	
	/**
	 * A label explanation of selecting days.
	 */
	private JLabel dayPromptLabel;
	
	/**
	 * A label explanation of selecting a ship.
	 */
	private JLabel shipPromptLabel;
	
	/**
	 * The first panel.
	 */
	private JPanel panelOne;
	
	/**
	 * The second panel.
	 */
	
	private JPanel panelTwo;
	
	/**
	 * The third panel.
	 */
	private JPanel panelThree;
	
	/**
	 * The windows frame.
	 */
	private JFrame window;
	
	/**
	 * The game environment.
	 */
	private GameEnvironment environment;
	
	/**
	 * The players selected name.
	 */
	private String name;
	
	/**
	 * The players selected ship number.
	 */
	private int shipNum;
	
	/**
	 * The players selected number of days to play.
	 */
	private int days;

	/**
	 * A constant that represents ship one.
	 */
	private static final int SHIP_ONE = 1;

	/**
	 * A constant that represents ship two.
	 */
	private static final int SHIP_TWO = 2;

	/**
	 * A constant that represents ship three.
	 */
	private static final int SHIP_THREE = 3;

	/**
	 * A constant that represents ship four.
	 */
	private static final int SHIP_FOUR = 4;
	

	/**
	 * Initializes the setup screen class. Which then initializes the components within it.
	 * 
	 * @param playingEnvironment		The game environment.
	 */
	public SetUpScreen(GameEnvironment playingEnvironment) {
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
	 * This also store the values selected during the setup screen to the GameLogic game in the environment.
	 */
	public void finishedWindow() {
		// Saves selected values.
		GameLogic game = environment.getGame();
		game.setName(name);
		game.setDays(days);
		game.setShip(shipNum);
		environment.closeSetupScreen(this);
	}

	
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param num		The ship number.
	 */
	public void setShipNum(int num) {
		// This method is public as its accessed through the ShipActionListener class.
		shipNum = num;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame("SetUpScreen");
		window.setBounds(700, 500, 900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		// Add the panels and layers to the frame.
		buildLayers();
		
	}
	
	
	/**
	 * Initialize the layers and panels within the frame.
	 */
	private void buildLayers() {
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 884, 561);
		window.getContentPane().add(layeredPane);
		
		panelOne = new JPanel();
		panelOne.setBounds(0, 0, 884, 561);
		panelOne.setOpaque(false);
		layeredPane.add(panelOne);
		panelOne.setLayout(null);

		panelTwo = new JPanel();
		panelTwo.setBounds(0, 0, 884, 561);
		panelTwo.setVisible(false);
		panelTwo.setOpaque(false);
		layeredPane.add(panelTwo);
		panelTwo.setLayout(null);
		
		panelThree = new JPanel();
		panelThree.setBounds(0, 0, 884, 561);
		panelThree.setVisible(false);
		panelThree.setOpaque(false);
		layeredPane.add(panelThree);
		panelThree.setLayout(null);
		
		JLabel background = new JLabel(new ImageIcon(SetUpScreen.class.getResource("/Images/mapback.png")));
		background.setBounds(0, 0, 884, 561);
		layeredPane.add(background);
		
		// Initializes all the components of the layers and panels.
		addLayerOneComponents();
		addLayerTwoComponents();
		addLayerThreeComponents();
	}
	
	
	/**
	 * Initialize the components of panel one.
	 */
	private void addLayerOneComponents() {
		txtName = new JTextField();
		txtName.setBounds(50, 150, 350, 40);
		txtName.setColumns(10);
		panelOne.add(txtName);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(426, 150, 100, 40);
		// Only usable when a name is entered.
   	 	confirmButton.setEnabled(false);
		panelOne.add(confirmButton);
		
		JLabel namePromptLabel = new JLabel("Enter your trader name!");
		namePromptLabel.setBounds(50, 125, 189, 14);
		panelOne.add(namePromptLabel);
		
		JLabel errorLabel = new JLabel("Error: Your name must be between 3-15 characters. Use only plain text have no double spaces.");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(50, 252, 600, 22);
		panelOne.add(errorLabel);
		// Not visible until an error in the name has occurred.
		errorLabel.setVisible(false);
		
		txtName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
			    checkName();
			    }
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkName();
				}
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkName();
			    }
			public void checkName() {
				// Checks the game logic if the name is valid.
				if (environment.getGame().checkName(txtName.getText())) {
			    	 errorLabel.setVisible(false);
			    	 confirmButton.setEnabled(true);
			     } else {
			    	 errorLabel.setVisible(true);
			    	 confirmButton.setEnabled(false);
			     }
			  }
		});
		
		// Saves name in text field.
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = txtName.getText();
				dayPromptLabel.setText("How many days long shall your voyage be, " + name + "!");
				shipPromptLabel.setText("Pick a ship to set sail with " + name + "!");
				}
		});
		// Swaps to panel two.
		confirmButton.addActionListener(new IslandActionListener(panelOne, panelTwo));

	}
	
	
	/**
	 * A method that is called when a changeEvent of the slider has occurred.
	 * Updates the slider readout and save the selected number of days.
	 */
	private void sliderChanged() {
		int currentNum = slider.getValue();
		sliderReadOut.setText(String.format("%d", currentNum));
	}
	
	
	/**
	 * Initialize the components of panel two.
	 */
	private void addLayerTwoComponents() {
		slider = new JSlider();
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinimum(20);
		slider.setMaximum(50);
		slider.setBounds(256, 242, 239, 40);
		slider.setOpaque(false);
		panelTwo.add(slider);
		
		sliderReadOut = new JLabel();
		sliderReadOut.setBounds(360, 300, 307, 14);
		panelTwo.add(sliderReadOut);
		sliderReadOut.setText(String.format("%d", slider.getValue()));
		
		// Whenever the slider is updated the sliderChanged method is called.
		slider.addChangeListener(changeEvent -> sliderChanged());

		dayPromptLabel = new JLabel();
		dayPromptLabel.setBounds(256, 216, 307, 14);
		panelTwo.add(dayPromptLabel);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBounds(505, 240, 88, 40);
		panelTwo.add(confirmButton);
		
		JButton backButton = new JButton("Go Back");
		backButton.setBounds(771, 510, 103, 40);
		panelTwo.add(backButton);
		
		// Ensures that the value in the slider is saved.
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				days = slider.getValue();
				}
		});
		// Swaps to panel three.
		confirmButton.addActionListener(new IslandActionListener(panelTwo, panelThree));
		// Swaps to panel one.
		backButton.addActionListener(new IslandActionListener(panelTwo, panelOne));
	}
	
	
	/**
	 * Initialize the components of panel three.
	 */
	private void addLayerThreeComponents() {
		JButton shipButtonOne = new JButton(new ImageIcon(SetUpScreen.class.getResource("/Images/Barq.png")));
		shipButtonOne.setBounds(120, 160, 100, 100);
		panelThree.add(shipButtonOne);
	
		JButton shipButtonTwo = new JButton(new ImageIcon(SetUpScreen.class.getResource("/Images/Brig.png")));
		shipButtonTwo.setBounds(299, 160, 100, 100);
		panelThree.add(shipButtonTwo);
		
		JButton shipButtonThree = new JButton(new ImageIcon(SetUpScreen.class.getResource("/Images/Galleon.png")));
		shipButtonThree.setBounds(479, 160, 100, 100);
		panelThree.add(shipButtonThree);
	
		JButton shipButtonFour = new JButton(new ImageIcon(SetUpScreen.class.getResource("/Images/Sloop.png")));
		shipButtonFour.setBounds(660, 160, 100, 100);
		panelThree.add(shipButtonFour);
		
		JButton backButton = new JButton("Go Back");
		backButton.setBounds(771, 510, 103, 40);
		panelThree.add(backButton);
	
		shipPromptLabel = new JLabel();
		shipPromptLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		shipPromptLabel.setBounds(265, 6, 698, 72);
		panelThree.add(shipPromptLabel);

		JLabel descTextLabel = new JLabel();
		descTextLabel.setBounds(178, 300, 564, 152);
		panelThree.add(descTextLabel);
		// Hide the text until the buttons are mouse over.
		descTextLabel.setVisible(false);
		
		
		// Defines what text will be displayed in the label if the mouse is on top of a given button.
		String textShipOne = "Barquentine (All Round Ship)";
		shipButtonOne.addMouseListener(new MouseEvents(descTextLabel, textShipOne));
		
		String textShipTwo = "Brigantine (War Ship)";
		shipButtonTwo.addMouseListener(new MouseEvents(descTextLabel, textShipTwo));

		String textShipThree = "Galleon (Merchant Ship)";
		shipButtonThree.addMouseListener(new MouseEvents(descTextLabel, textShipThree));
		
		String textShipFour = "Sloop (Fast Ship)";
		shipButtonFour.addMouseListener(new MouseEvents(descTextLabel, textShipFour));
		
		// When a button is pressed the ship number is saved and then the environment opens the main game.
		shipButtonOne.addActionListener(new ShipActionListener(SHIP_ONE, this));	
		shipButtonTwo.addActionListener(new ShipActionListener(SHIP_TWO, this));		
		shipButtonThree.addActionListener(new ShipActionListener(SHIP_THREE, this));	
		shipButtonFour.addActionListener(new ShipActionListener(SHIP_FOUR, this));	

		// Swaps to panel two.
		backButton.addActionListener(new IslandActionListener(panelThree, panelTwo));
	}
	
}