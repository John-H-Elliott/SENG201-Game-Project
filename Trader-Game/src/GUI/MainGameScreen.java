package GUI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.SystemColor;
import MainGameLogic.GameLogic;
import MainGameLogic.Item;
import MainGameLogic.SetUp;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * MainGameScreen contains code for the applications main GUI, anything that happens that needs to be displayed
 * visually to the player during game-play is visible here and any input by the player that is required for game-play
 * is able to be input here.
 * MainGameScreen contains a map image and HUD for the player to interact with, tables and text that will display information
 * to the player, and a variety of buttons and widgets that the player can use.
 * 
 * As MainGameScreen is the main screen for the game it contains all saved data from SetUpScreen and displays based on
 * Function by GameLogic.
 * 
 * @author Ryan Boyd
 * @author John Elliot
 * @version 0.6, 22 May 2021.
 */
public class MainGameScreen {
	// All components used when creating the screen.
	// The panels, scroll planes and panel layers.
	private JLayeredPane infoAreaPanels;
	private JPanel homeInfoPanel;
	private JPanel travelInfoPanel;
	private JPanel mapAreaPanel;
	private JPanel playerInfoPanel;
	private JPanel upgradePanel;
	private JPanel shopInfoPanel;
	private JPanel selectionPanel;
	private JPanel purchasePanel;
	private JScrollPane shopScrollPanel;
	private JScrollPane playerScrollPanel;
	// Text boxes.
	private JTextPane infoBox;
	private JTextPane wealthText;
	private JTextPane daysText;
	private JTextPane nameText;
	private JTextPane infoTravelBox;
	// Labels.
	private JLabel islandInfoLabel;
	private JLabel currentIsland;
	// Buttons.
	private JButton islOneButton;
	private JButton islTwoButton;
	private JButton islThreeButton;
	private JButton islFourButton;
	private JButton islFiveButton;
	private JButton toStoreButton;
	private JButton purchaseButton;
	private JButton upgradeButton;
	// Spinners.
	private JSpinner sailSpinner;
	private JSpinner cannonSpinner;
	private JSpinner cargoSpinner;
	// Tables.
	private JTable shopTable;
	private JTable playerTable;
	// Constant values which relate to a island.
	private static final int ISLAND_ONE = 0;
	private static final int ISLAND_TWO = 1;
	private static final int ISLAND_THREE = 2;
	private static final int ISLAND_FOUR = 3;
	private static final int ISLAND_FIVE = 4;

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
	 * The newly selected island.
	 */
	private int newIsland;
	
	/**
	 * The tutorial information.
	 */
	private String tutorialInfo;
	
	/**
	 * A list of the island buttons.
	 */
	private ArrayList<JButton> islandButtons = new ArrayList<JButton>();
	
	
	/**
	 * Initializes the main screen class. Which then initializes the components within it.
	 * 
	 * @param playingEnvironment		The game environment.
	 */
	public MainGameScreen(GameEnvironment playingEnvironment) {
		environment = playingEnvironment;
		game = environment.getGame();
		// Set the current island selected as the one the player is already on.
		newIsland = game.getIslandIndex();
		tutorialInfo = SetUp.getTutorialText();
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
	 * A get method used to return the frame for this screen. 
	 * Used when exiting the main screen.
	 * 
	 * @return window		This screens frame.
	 */
	public JFrame getFrame() {
		return window;
	}
	
	
	/**
	 * Call when close the current window to get the environment to launch the new window.
	 * 
	 * @param won		The boolean the represents if the player has won or not.
	 */
	public void finishedWindow(boolean won) {
		environment.closeMainGameScreen(this, won);
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame("Trader Game");
		window.setBounds(700, 500, 900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);
		window.setVisible(true);
		// Add the panels and layered panels to this screens frame.
		buildPanels();
	}
	
	
	/**
	 * Build Panels for the window.
	 * 
	 * MainGameScreen contains 2 main sections, one being the map, and the other being the HUD. The HUD
	 * or heads up display contains menu buttons that the player can interact with to navigate available options
	 * during their visit to an island. the Map allows them to visit and view other islands. Interacting with the Map
	 * updates certain elements on the HUD panel.
	 * 
	 * The HUD panel is split into a further 2 parts one part contains buttons for inputs the player can make. and the other
	 * contains a panel within the HUD panel that is used to display data and tables to the player. These panels are contained
	 * within a second to the bottom right of the GUI. this HUD parent panel is referenced as homeInfoPanel and the map panel
	 * is referenced as mapAreaPanel, other panels fit into the display are as described above.
	 */
	private void buildPanels() {
		// Adds the top section of the screen.
		playerInfoPanel = new JPanel();
		playerInfoPanel.setBounds(0, 0, 884, 41);
		window.getContentPane().add(playerInfoPanel);
		playerInfoPanel.setLayout(null);
		
		// Adds the lower section of the screen.
		infoAreaPanels = new JLayeredPane();
		infoAreaPanels.setBounds(0, 397, 884, 164);
		window.getContentPane().add(infoAreaPanels);
		infoAreaPanels.setLayout(null);
		
		// This is acts as the "Start" screen.
		homeInfoPanel = new JPanel();
		homeInfoPanel.setBackground(Color.LIGHT_GRAY);
		homeInfoPanel.setBounds(0, 0, 884, 164);
		homeInfoPanel.setOpaque(false);
		infoAreaPanels.add(homeInfoPanel);
		homeInfoPanel.setLayout(null);
		

		shopInfoPanel = new JPanel();
		shopInfoPanel.setBackground(Color.LIGHT_GRAY);
		shopInfoPanel.setBounds(0, 0, 884, 164);
		shopInfoPanel.setOpaque(false);
		infoAreaPanels.add(shopInfoPanel);
		shopInfoPanel.setLayout(null);
		shopInfoPanel.setVisible(false);
		
		// Where players chose to buy or sell items.
		selectionPanel = new JPanel();
		selectionPanel.setBackground(Color.LIGHT_GRAY);
		selectionPanel.setBounds(0, 0, 884, 164);
		selectionPanel.setLayout(null);
		selectionPanel.setOpaque(false);
		shopInfoPanel.add(selectionPanel); 
		
		// Where players buy or sell items.
		purchasePanel = new JPanel();
		purchasePanel.setBackground(Color.LIGHT_GRAY);
		purchasePanel.setBounds(0, 0, 884, 164);
		purchasePanel.setLayout(null);
		purchasePanel.setVisible(false);
		purchasePanel.setOpaque(false);
		shopInfoPanel.add(purchasePanel); 
		
		// Allows players to see island information and sail to islands.
		travelInfoPanel = new JPanel();
		travelInfoPanel.setBackground(Color.LIGHT_GRAY);
		travelInfoPanel.setBounds(0, 0, 884, 164);
		travelInfoPanel.setOpaque(false);
		infoAreaPanels.add(travelInfoPanel);
		travelInfoPanel.setLayout(null);
		travelInfoPanel.setVisible(false);
		
		// Used when players are buying upgrades.
		upgradePanel = new JPanel();
		upgradePanel.setBackground(Color.LIGHT_GRAY);
		upgradePanel.setBounds(0, 0, 884, 164);
		upgradePanel.setLayout(null);
		upgradePanel.setVisible(false);
		upgradePanel.setOpaque(false);
		shopInfoPanel.add(upgradePanel); 
		
		// Adds the middle section of the screen. 
		mapAreaPanel = new JPanel();
		mapAreaPanel.setBounds(0, 40, 884, 359);
		window.getContentPane().add(mapAreaPanel);
		mapAreaPanel.setLayout(null);
		
		
		JLabel background = new JLabel(new ImageIcon(SetUpScreen.class.getResource("/Images/menu_bar.png")));
		background.setBounds(0, 0, 884, 164);
		infoAreaPanels.add(background);
		
		// Adds all components of the panels.
		addPlayerInfoComponents();
		addHomeInfoComponents();
		addShopInfoComponents();
		addTravelInfoComponents();
		addUpgradeComponents();
		addMapAreaComponents();
	}
	
	
	/**
	 * HUD components for navigating are present in the code below. these buttons are specifically for changing
	 * the information displayed to the player on the HUD such as the store they can currently buy and sell from, their
	 * current ships cargo, and any other appropriate information they should be able to access while docked at an island.
	 * 
	 * ActionListener methods below provide functionality for the buttons which each provide the functionality displayed
	 * in their set text.
	 */
	private void addHomeInfoComponents() {
		infoBox = new JTextPane();
		infoBox.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(infoBox);
		scrollPanel.setBounds(237, 11, 637, 142);
		homeInfoPanel.add(scrollPanel);
		
		JButton viewCargoButton = new JButton("View Cargo");
		viewCargoButton.setBounds(10, 48, 200, 30);
		homeInfoPanel.add(viewCargoButton);
		
		JButton tutorialButton = new JButton("How To Play");
		tutorialButton.setBounds(10, 11, 200, 30);
		homeInfoPanel.add(tutorialButton);
		
		toStoreButton = new JButton("Go To Store");
		toStoreButton.setBounds(10, 84, 200, 30);
		homeInfoPanel.add(toStoreButton);
		
		JButton shipInfoButton = new JButton("View Ship Info");
		shipInfoButton.setBounds(10, 123, 200, 30);
		homeInfoPanel.add(shipInfoButton);
		
		// Updates text in the infoBox.
		tutorialButton.addActionListener(getButtonListener(infoBox, "TUT"));
		shipInfoButton.addActionListener(getButtonListener(infoBox, "SHIP"));
		viewCargoButton.addActionListener(getButtonListener(infoBox, "CARGO"));
		
		// Goes to the shop panel.
		toStoreButton.addActionListener(new IslandActionListener(homeInfoPanel, shopInfoPanel));
		// Disables all island buttons.
		toStoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JButton button : islandButtons) {
					button.setEnabled(false);
				}
			}
		});
		
	}
	
	
	/**
	 * Creates a action listener that updates information in a text panel to be some given string info.
	 * 
	 * @param infoBox				The text panel that will be updated.
	 * @param type					The type of information that will be used.
	 * 
	 * @return buttonListener		The action listener.
	 */
	private ActionListener getButtonListener(JTextPane infoBox, String type) {
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String info;
				// Checks what type of info should be updated to the infoBox.
				switch (type){
					case "TUT":
						info = tutorialInfo;
						break;
					case "SHIP":
						info = game.getShipInfo();
						break;
					case "CARGO":
						info = game.getCargo();
						break;
					case "SELLING":
						info = game.soldAtStore(newIsland);
						break;
					case "BUYING":
						info = game.broughtAtStore(newIsland);
						break;
					default:
						info = "";
				}
				infoBox.setText(info);
			}
		};
		return buttonListener;
	}
	
	
	/**
	 * Code that creates all the elements for the in game store.
	 * 
	 * Creates new instances of TableSelectionModel for use in the store and adds interactivity so they can be used
	 * to buy and sell items. Also creates buttons for use in navigating the store and its various sub-menus.
	 * Store components are kept in JScrollPanes so they can be filled with items and text that would otherwise be viewed
	 * improperly by the player.
	 */
	private void addShopInfoComponents() {
		// Makes all tables.
		
		ArrayList<Item> items = game.getStoreItems();
		// Adds all items in the current store to the table.
		shopTable = makeTable(items);
		StoreTableSelectionModel selectionModel = new StoreTableSelectionModel(shopTable, (StoreTableModel) shopTable.getModel(), window, environment);
		shopTable.setSelectionModel(selectionModel);
		// Adds a method that checks if the player can now purchase the items they have selected. This is to make sure the player is on the same screen as table.
		selectionModel.addListSelectionListener(event -> checkCanContinue());
		// Remove the unneeded columns.
		TableColumnModel shopColumns = shopTable.getColumnModel();
		// Which column to remove. This one removes the sold-for column. 
		shopColumns.removeColumn(shopColumns.getColumn(shopTable.convertColumnIndexToView(2)));
		
		ArrayList<Item> cargo = game.getCargoItems();
		playerTable = makeTable(cargo);
		// Adds a method that checks if the player can now purchase the items they have selected. This is to make sure the player is on the same screen as table.
		playerTable.getSelectionModel().addListSelectionListener(event -> checkCanContinue());
		// Remove the unneeded columns.
		TableColumnModel playerColumns = playerTable.getColumnModel();
		// Which column to remove. This one removes the price column. 
		playerColumns.removeColumn(playerColumns.getColumn(playerTable.convertColumnIndexToView(1)));
		
		
		shopScrollPanel = new JScrollPane(shopTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		shopScrollPanel.setBounds(237, 11, 637, 142);
		purchasePanel.add(shopScrollPanel);
		
		playerScrollPanel = new JScrollPane(playerTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		playerScrollPanel.setBounds(237, 11, 637, 142);
		purchasePanel.add(playerScrollPanel);
		
		JButton sellButton = new JButton("Sell Items");
		sellButton.setBounds(10, 48, 200, 30);
		selectionPanel.add(sellButton);
		
		JButton buyButton = new JButton("Buy Items");
		buyButton.setBounds(10, 11, 200, 30);
		selectionPanel.add(buyButton);
		
		upgradeButton = new JButton("Get Upgrades");
		upgradeButton.setBounds(10, 84, 200, 30);
		selectionPanel.add(upgradeButton);
		
		purchaseButton = new JButton("Complete Purchase");
		purchaseButton.setBounds(10, 11, 200, 30);
		purchaseButton.setEnabled(false);
		purchasePanel.add(purchaseButton);
		
		JButton backMainButton = new JButton("Back");
		backMainButton.setBounds(10, 123, 200, 30);
		selectionPanel.add(backMainButton);
		
		JButton backShopButton = new JButton("Back");
		backShopButton.setBounds(10, 123, 200, 30);
		purchasePanel.add(backShopButton);
		
		// Checks which table the player is using and then updates it. Afterwards updates the players table as they have either bought or sold an item.
		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playerScrollPanel.isVisible()) {
					int[] items = playerTable.getSelectedRows();
					game.sellItems(items);
				} else {
					int[] items = shopTable.getSelectedRows();
					game.buyItems(items);
					((StoreTableModel) shopTable.getModel()).fireTableDataChanged();
				}
				((StoreTableModel) playerTable.getModel()).fireTableDataChanged();
				// Updates info panel to show the new change in wealth.
				updateInfoPanel();
			}
		});
		
		sellButton.addActionListener(new IslandActionListener(selectionPanel, purchasePanel));
		// Allows the player to buy an item. Also makes sure that they can buy and item.
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopScrollPanel.setVisible(false);
				playerScrollPanel.setVisible(true);
				checkCanContinue();
			}
		});
		// Allows the player to sell an item. Also makes sure that they can sell and item.
		buyButton.addActionListener(new IslandActionListener(selectionPanel, purchasePanel));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shopScrollPanel.setVisible(true);
				playerScrollPanel.setVisible(false);
				checkCanContinue();
			}
		});
		// Goes back to the selection panel.
		backShopButton.addActionListener(new IslandActionListener(purchasePanel, selectionPanel));
		// Goes back to the home panel.
		backMainButton.addActionListener(new IslandActionListener(shopInfoPanel, homeInfoPanel));
		// Allows player to again select an island to travel to. As they are disabled while shopping.
		backMainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int islandIndex = game.getIslandIndex();
				for (JButton button : islandButtons) {
					button.setEnabled(true);
				}
				// Disables the current island.
				islandButtons.get(islandIndex).setEnabled(false);
				infoBox.setText("");
				
				// Checks now if the player has somewhere to go.
				if (!game.canContiune()) {
					// If they don't alerts the player. Sells all current goods to the shop and ends the game.
					JOptionPane.showMessageDialog(window, "There is no where left to go.");
					game.sellRemainingCargo();
					// This is a game win.
					finishedWindow(true);
				}
			}
		});
		// Goes to the upgrade panel.
		upgradeButton.addActionListener(new IslandActionListener(selectionPanel, upgradePanel));
	}
	
	
	/**
	 * Creates JTable from item array list and populates it accordingly.
	 * Contains required methods for setting table rules
	 * 
	 * @param items					Array list of items to go into the table
	 * @return madeTable			returns JTable element 
	 */
	private JTable makeTable(ArrayList<Item> items) {
		// Makes the table model which tells the table what values the cells have.
		StoreTableModel model = new StoreTableModel(items, game);
		// This custom table makes sure that no text is truncated.
		JTable madeTable = new ColumnWidthsJTable(model);
		// Turns off/on required properties.
		madeTable.setRowSelectionAllowed(true);
		madeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		madeTable.getTableHeader().setReorderingAllowed(false);
		madeTable.getTableHeader().setResizingAllowed(false);
		// Gets the headers renderer and type casts it to DefaultTableCellRenderer. 
		// This is because this class extends JLabels and so setHorizontalAlignment() can be used.
		((DefaultTableCellRenderer) madeTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
		
		return madeTable;
	}
	
	
	/**
	 * This method is used to check if the purchase button can be used.
	 * The player has to have a given panel visible and at least one row selected.
	 */
	private void checkCanContinue() {
		if ((shopTable.getSelectedRowCount() >= 1) && (shopScrollPanel.isVisible())) {
			purchaseButton.setEnabled(true);
		} else if ((playerTable.getSelectedRowCount() >= 1) && (playerScrollPanel.isVisible())){
			purchaseButton.setEnabled(true);
		} else {
			purchaseButton.setEnabled(false);
		}
	}
	
	
	/**
	 * Adds window components for the upgrade menu in the islands shop.
	 * 
	 * Labeled JSpinner components are used to display
	 * what items are available on offer to the player, the player can choose a quantity of each upgrade and make a purchase once
	 * the desired quantities are selected.
	 */
	private void addUpgradeComponents() {
		JButton sailUpgradeButton = new JButton("Boat Sails");
		sailUpgradeButton.setBounds(10, 84, 200, 30);
		sailUpgradeButton.setEnabled(false);
		upgradePanel.add(sailUpgradeButton);
		
		JButton cannonUpgradeButton = new JButton("Cannons");
		cannonUpgradeButton.setBounds(10, 11, 200, 30);
		cannonUpgradeButton.setEnabled(false);
		upgradePanel.add(cannonUpgradeButton);
		
		JButton cargoUpgradeButton = new JButton("Cargo Space");
		cargoUpgradeButton.setBounds(10, 48, 200, 30);
		cargoUpgradeButton.setEnabled(false);
		upgradePanel.add(cargoUpgradeButton);
		
		JLabel cargoLabel = new JLabel("Cost: 0");
		cargoLabel.setBounds(285, 45, 200, 30);
		upgradePanel.add(cargoLabel);
		
		JLabel sailLabel = new JLabel("Cost: 0");
		sailLabel.setBounds(285, 82, 200, 30);
		upgradePanel.add(sailLabel);
		
		JLabel cannonLabel = new JLabel("Cost: 0");
		cannonLabel.setBounds(285, 8, 200, 30);
		upgradePanel.add(cannonLabel);
		
		sailSpinner = new JSpinner();
		sailSpinner.setBounds(215, 84, 60, 25);
		sailSpinner.addChangeListener(new SpinnerListener(sailUpgradeButton, game, SetUp.SAILS, sailLabel));
		upgradePanel.add(sailSpinner);
		
		cannonSpinner = new JSpinner();
		cannonSpinner.setBounds(215, 11, 60, 25);
		cannonSpinner.addChangeListener(new SpinnerListener(cannonUpgradeButton, game, SetUp.CANNONS, cannonLabel));
		upgradePanel.add(cannonSpinner);
		
		cargoSpinner = new JSpinner();
		cargoSpinner.setBounds(215, 48, 60, 25);
		cargoSpinner.addChangeListener(new SpinnerListener(cargoUpgradeButton, game, SetUp.CARGO, cargoLabel));
		upgradePanel.add(cargoSpinner);
		
		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 123, 200, 30);
		upgradePanel.add(backButton);
		
		// Goes to the selection panel.
		backButton.addActionListener(new IslandActionListener(upgradePanel, selectionPanel));
		
		// Allows the player to buy items.
		sailUpgradeButton.addActionListener(getUpgradeListener(SetUp.SAILS));
		cannonUpgradeButton.addActionListener(getUpgradeListener(SetUp.CANNONS));
		cargoUpgradeButton.addActionListener(getUpgradeListener(SetUp.CARGO));
	}
	
	
	/**
	 * Creates a action listener that updates the spinner and users information when buying upgrades.
	 * 
	 * @param type					The type of upgrade.
	 * 
	 * @return upgradeListener		The action listener.
	 */
	private ActionListener getUpgradeListener(String type) {
		ActionListener upgradeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount;
				switch (type){
					case SetUp.SAILS:
						amount = (int) sailSpinner.getValue();
						break;
					case SetUp.CANNONS:
						amount = (int) cannonSpinner.getValue();
						break;
					case SetUp.CARGO:
						amount = (int) cargoSpinner.getValue();
						break;
					default:
						amount = 0;
				}
				// Buys upgrades and resets menu and display.
				game.buyShipUpgrades(type, amount);
				sailSpinner.setValue(0);
				cannonSpinner.setValue(0);
				cargoSpinner.setValue(0);
				updateInfoPanel();
			}
		};
		return upgradeListener;
	}
	
	
	/**
	 * The below components are only visible when the player attempts to travel by interacting with the map screen.
	 * this is so the player can see information about other islands while they are not docked their but provides
	 * limited functionality when it comes to interacting with stores compared to actually be docked at the island.
	 * 
	 */
	private void addTravelInfoComponents() {
		infoTravelBox = new JTextPane();
		infoTravelBox.setEditable(false);
		JScrollPane scrollPanel = new JScrollPane(infoTravelBox);
		scrollPanel.setBounds(237, 11, 637, 142);
		travelInfoPanel.add(scrollPanel);
		
		JButton soldAtShopButton = new JButton("Items Sold By The Shop");
		soldAtShopButton.setBounds(10, 11, 200, 30);
		travelInfoPanel.add(soldAtShopButton);
		
		JButton backButton = new JButton("Go Back");
		backButton.setBounds(10, 123, 200, 30);
		travelInfoPanel.add(backButton);
		
		JButton sailButton = new JButton("Set Sail To Island");
		sailButton.setBounds(10, 82, 200, 30);
		travelInfoPanel.add(sailButton);
		
		JButton broughtAtShopButton = new JButton("Items Brough By The Shop");
		broughtAtShopButton.setBounds(10, 47, 200, 30);
		travelInfoPanel.add(broughtAtShopButton);
		
		// Goes to the home screen.
		backButton.addActionListener(new IslandActionListener(travelInfoPanel, homeInfoPanel));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int islandIndex = game.getIslandIndex();
				for (JButton button : islandButtons) {
					button.setEnabled(true);
				}
				islandButtons.get(islandIndex).setEnabled(false);
				// Resets newIsland to the currently docked island.
				newIsland = game.getIslandIndex();
				// Updates the label.
				currentIsland.setText(game.getIslandName(islandIndex));
				// Resets home menu.
				infoBox.setText("");
			}
		});
		// Goes to respected shopping screens.
		soldAtShopButton.addActionListener(getButtonListener(infoTravelBox, "SELLING"));
		broughtAtShopButton.addActionListener(getButtonListener(infoTravelBox, "BUYING"));

		// Checks that they player can sail to a new island.
		sailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks if the ship can be fixed.
				if (game.canRepair()) {
					// Checks if the ship is damaged and allows the player to fix it.
					if (confirmRepair()) {
						// Fixes the ship.
						game.repair();
					} else {
						return;
					}
				} else {
					// Player can't afford to fix the ship.
					JOptionPane.showMessageDialog(window, "You can't Sail to this island untill you can repair your ship.");
					return;
				}
				// Checks if the player can afford to sail.
				if (game.canSail(newIsland)) {
					// Confirms they want to sail.
					if (confirmSail(newIsland)) {
						// Goes to the sailing window.
						environment.launchSailScreen(newIsland);
						window.setVisible(false);
					} else {
						return;
					}
				} else {
					// Player can't afford to sail.
					JOptionPane.showMessageDialog(window, "You can't Sail to this island untill you can pay your crew.");
				}
			}	
		});
	}
	
	
	/**
	 * This allows for the screen to be reset back to the home screen after a player has sailed to a new island.
	 */
	public void updateMainScreen() {
		((StoreTableModel) shopTable.getModel()).updateItems(game.getStoreItems());
		((StoreTableModel) playerTable.getModel()).fireTableDataChanged();
		travelInfoPanel.setVisible(false);
		homeInfoPanel.setVisible(true);
		for (JButton button : islandButtons) {
			button.setEnabled(true);
		}
		islandButtons.get(newIsland).setEnabled(false);
		updateInfoPanel();
	}
	
	
	/**
	 * Confirms if repairs to the ship have been made, only if the ship has been damaged prior.
	 * 
	 * @return selection 		JOptionPane.YES_OPTION confirm pop-up boolean
	 */
    private boolean confirmRepair() {
    	if (game.isShipDamaged()) {
    		int selection = JOptionPane.showConfirmDialog(window, "Do you want to repair your Ship?",
                    "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            return selection == JOptionPane.YES_OPTION;
    	}
    	return true;  
    }
    
    
    /**
	 * Confirms if sailing is to ensue.
	 * 
	 * @return selection 		JOptionPane.YES_OPTION confirm pop-up boolean
	 */
    private boolean confirmSail(int island) {
    	int selection = JOptionPane.showConfirmDialog(window, "Do you want to sail to "+game.getIslandName(island)+"?",
    		"Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        return selection == JOptionPane.YES_OPTION;
    }
	
    
    /**
	 * Creates buttons for separate islands on the map, these buttons contain images which appear as islands, the island that the player
	 * is currently docked at will have a different displayed image indicating that they are docked there and its functionality as a button
	 * on the map will be reduced until they have sailed elsewhere. hovering over an island with the mouse cursor displays its name
	 * at the top of the screen, every island has a different name - this is controlled through a mouseListener event.
	 */
    private void addMapAreaComponents() {
		currentIsland = new JLabel(game.getIslandName(newIsland));
		currentIsland.setHorizontalAlignment(SwingConstants.CENTER);
		currentIsland.setBounds(10, 322, 200, 26);
		currentIsland.setOpaque(true);
		mapAreaPanel.add(currentIsland);
		
		// Island Buttons.
		// 1.
		islOneButton = new JButton(new ImageIcon(MainGameScreen.class.getResource("/Images/PR(plentiful retreat).png")));
		islOneButton.setBounds(275, 266, 70, 70);
		islOneButton.setEnabled(false);
		islandButtons.add(islOneButton);
		mapAreaPanel.add(islOneButton);
		// 2.
		islTwoButton = new JButton(new ImageIcon(MainGameScreen.class.getResource("/Images/SR(sunken reef).png")));
		islTwoButton.setBounds(92, 127, 70, 70);
		islandButtons.add(islTwoButton);
		mapAreaPanel.add(islTwoButton);
		// 3.
		islThreeButton = new JButton(new ImageIcon(MainGameScreen.class.getResource("/Images/JI(jeweled isle).png")));
		islThreeButton.setBounds(376, 36, 70, 70);
		islandButtons.add(islThreeButton);
		mapAreaPanel.add(islThreeButton);
		// 4.
		islFourButton = new JButton(new ImageIcon(MainGameScreen.class.getResource("/Images/CE(craftmaster's enclave).png")));
		islFourButton.setBounds(557, 266, 70, 70);
		islandButtons.add(islFourButton);
		mapAreaPanel.add(islFourButton);
		// 5.
		islFiveButton = new JButton(new ImageIcon(MainGameScreen.class.getResource("/Images/AA(affluential atoll).png")));
		islFiveButton.setBounds(709, 127, 70, 70);
		islandButtons.add(islFiveButton);
		mapAreaPanel.add(islFiveButton);
		
		// Image for island map.
		JLabel ocean = new JLabel("");
		ocean.setBounds(-164, -65, 1224, 1632);
		ocean.setIcon(new ImageIcon(MainGameScreen.class.getResource("/Images/ocean.png")));
		mapAreaPanel.add(ocean);

		
		// Display Island details.
		islOneButton.addMouseListener(new MouseEvents(islandInfoLabel, game.getIslandName(ISLAND_ONE)));
		islTwoButton.addMouseListener(new MouseEvents(islandInfoLabel, game.getIslandName(ISLAND_TWO)));
		islThreeButton.addMouseListener(new MouseEvents(islandInfoLabel, game.getIslandName(ISLAND_THREE)));
		islFourButton.addMouseListener(new MouseEvents(islandInfoLabel, game.getIslandName(ISLAND_FOUR)));
		islFiveButton.addMouseListener(new MouseEvents(islandInfoLabel, game.getIslandName(ISLAND_FIVE)));
		// Allow travel menu for that island to appear.
		islOneButton.addActionListener(new IslandActionListener(homeInfoPanel, travelInfoPanel, ISLAND_ONE, this));
		islTwoButton.addActionListener(new IslandActionListener(homeInfoPanel, travelInfoPanel, ISLAND_TWO, this));
		islThreeButton.addActionListener(new IslandActionListener(homeInfoPanel, travelInfoPanel, ISLAND_THREE, this));
		islFourButton.addActionListener(new IslandActionListener(homeInfoPanel, travelInfoPanel, ISLAND_FOUR, this));
		islFiveButton.addActionListener(new IslandActionListener(homeInfoPanel, travelInfoPanel, ISLAND_FIVE, this));
	}
	

	/**
	 * Updates the viewed map buttons and other map specific visual components based on island docked.
	 * 
	 * @param islandNum			The island that the player is currently at.	
	 */
	public void updateMap(int islandNum) {
		newIsland = islandNum;
		for (JButton button : islandButtons) {
			button.setEnabled(false);
		}
		currentIsland.setText(game.getIslandName(islandNum));
		currentIsland.setVisible(true);
		infoTravelBox.setText("");
		infoBox.setText("");
	}
	
	
	/**
	 * Build components to display player info.
	 * 
	 * The following code controls the building of window elements at the top of the playable window that display
	 * information about the current game state to the player. such as the players wealth and the amount of time the player
	 * has left in their game (in days, this is the value the player chose during set up). other information is displayed here at certain
	 * points of game-play such as the name of whatever island the cursor is currently over.
	 */
	private void addPlayerInfoComponents(){		
		wealthText = new JTextPane();
		wealthText.setBackground(SystemColor.menu);
		wealthText.setEditable(false);
		wealthText.setBounds(10, 0, 146, 41);
		wealthText.setContentType("text/html");
		playerInfoPanel.add(wealthText);
		
		daysText = new JTextPane();
		daysText.setBackground(SystemColor.menu);
		daysText.setEditable(false);
		daysText.setBounds(166, 0, 168, 41);
		daysText.setContentType("text/html");
		playerInfoPanel.add(daysText);
		
		nameText = new JTextPane();
		nameText.setBackground(SystemColor.menu);
		nameText.setEditable(false);
		nameText.setBounds(674, 0, 200, 41);
		nameText.setContentType("text/html");
		playerInfoPanel.add(nameText);
		
		islandInfoLabel = new JLabel("");
		islandInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		islandInfoLabel.setBounds(310, 0, 300, 41);
		islandInfoLabel.setVisible(false);
		playerInfoPanel.add(islandInfoLabel);
		
		updateInfoPanel();
	}
	
	
	/**
	 * updates the information displayed to the player based on the current state of the game. using methods which pull information from
	 * GameLogic. this information displayed to the player is the same information being processed by the game.
	 * 
	 * @return wealth					Players current money.
	 * @return days						Amount of days before the game ends.
	 * @return name						The players name, chosen in SetUpScreen.
	 */
	private void updateInfoPanel() {
		int wealth = game.getWealth();
		int days = game.getDays();
		String name = game.getName();
		
		wealthText.setText("<html><b><font size=3 color=rgb(1,1,1)>  "+ ("Wealth: " + wealth) +"   </font></b></html>");
		daysText.setText("<html><b><font size=3 color=rgb(1,1,1)>  "+ ("DaysLeft: " + days) +"   </font></b></html>");
		nameText.setText("<html><center><b><font size=3 color=rgb(1,1,1)>  "+ ("Captain: " + name) +"   </font></b></center></html>");
	}
}