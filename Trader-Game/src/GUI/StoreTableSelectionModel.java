package GUI;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import MainGameLogic.Ship;

/**
 * Defines the selection model for selecting Items from the Store during the game.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class StoreTableSelectionModel extends DefaultListSelectionModel {

	/**
	 * An error message for when the player can afford to add an item.
	 */
	public static final String ERROR_PAYMENT = "You can only select Items you can pay for.";
	
	/**
	 * An error message for when the player dosen't have enough cargo space to buy the item.
	 */
	public static final String ERROR_WEIGHT = "You can only select Items you can fit in the cargo.";
	
	/**
	 * The model the table uses.
	 */
	private StoreTableModel model;
	
	/**
	 * The frame the table is in.
	 */
	private JFrame screen;
	
	/**
	 * The environment that the windows are using.
	 */
	private GameEnvironment environment;
	
	/**
	 * The table that this selectionModel is applied to.
	 */
	private JTable table;
	
	/**
	 * The ship that they player currently has.
	 */
	private Ship ship;
	
	
	/**
	 * Initializes an A selectionModel for a table. This checks to make sure a player can afford and fit an item within their cargo.
	 * 
	 * @param newTable					The table that is using this selectionModel.
	 * @param tableModel				The model the table is using.		
	 * @param window					The frame the table is placed in.
	 * @param gameEnvironment			The environment that is running the windows and game.
	 */
	public StoreTableSelectionModel(JTable newTable, StoreTableModel tableModel, JFrame window, GameEnvironment gameEnvironment) {
		super();
		model = tableModel;
		screen = window;
		environment = gameEnvironment;
		table = newTable;
		ship = gameEnvironment.getGame().getShip();
	}

	/**
	 * Overrides DefaultListSelectionModel method setSelectionInterval.
	 * That prevent the user from selecting items they can pay for or that they can't fit in their ships cargo.
	 *
	 * @param indexStart 	The start of the selection interval.
	 * @param indexEnd	 	The end of the selection interval.
	 */
	@Override
	public void setSelectionInterval(int indexStart, int indexEnd) {
		// Finds the price currently selected by the player.
		int priorPrice = model.getPriceTotal(table.getSelectedRows());
		// Gets the weight of the new item.
		double weight = model.getWeightTotal(table.getSelectedRows(), indexStart);
		// Checks if that current price of items plus the newly selected item is greater than the players wealth.
		if ((priorPrice + (int) table.getValueAt(indexStart, 1)) > environment.getGame().getWealth()) {
			// Stops them from buying it as it costs too much.
			JOptionPane.showMessageDialog(screen, ERROR_PAYMENT);
			return;
		} else if (weight > ship.findCargoLeft()) {
			// Stops them from buying it as its too heavy.
			JOptionPane.showMessageDialog(screen, ERROR_WEIGHT);
			return;
		}
		// Sets the item to the selected items.
		super.setSelectionInterval(indexStart, indexEnd);
	}

	/**
	 * Overrides DefaultListSelectionModel method addSelectionInterval.
	 * That prevent the user from selecting items they can pay for or that they can't fit in their ships cargo.
	 *
	 * @param indexStart 	The start of the selection interval.
	 * @param indexEnd	 	The end of the selection interval.
	 */
	@Override
	public void addSelectionInterval(int indexStart, int indexEnd) {
		// Finds the price currently selected by the player.
		int priorPrice = model.getPriceTotal(table.getSelectedRows());
		// Gets the weight of the new item.
		double weight = model.getWeightTotal(table.getSelectedRows(), indexStart);
		// Checks if that current price of items plus the newly selected item is greater than the players wealth.
		if ((priorPrice + (int) table.getValueAt(indexStart, 1)) > environment.getGame().getWealth()) {
			// Stops them from buying it as it costs too much.
			JOptionPane.showMessageDialog(screen, ERROR_PAYMENT);
			return;
		} else if (weight > ship.findCargoLeft()) {
			// Stops them from buying it as its too heavy.
			JOptionPane.showMessageDialog(screen, ERROR_WEIGHT);
			return;
		}
		// Adds the item to the selected items.
		super.addSelectionInterval(indexStart, indexEnd);
	}
}