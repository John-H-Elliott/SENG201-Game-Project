package GUI;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import MainGameLogic.GameLogic;
import MainGameLogic.Item;


/**
 * Defines the table model for the displaying the Items from the Store during the game.
 * 
 * @author John Elliott
 * @version 1.2, 20 May 2021.
 */
public class StoreTableModel extends AbstractTableModel{
	
	/**
	 * The list of items to display in the table.
	 */
	private ArrayList<Item> itemsList;
	
	/**
	 * The columns that will display partial parts of the information about the items.
	 */
	private final String[] columnNames = {"Name", "Price", "Sold For", "Weight", "Description"};
	
	/**
	 * The GameLogic that checks if a stores buying items.
	 */
	private GameLogic game;
	
	
	/**
	 * Initializes an A TableModel for a table. This displays items in stores or the players cargo.
	 * 
	 * @param items				The items that are being displayed.
	 * @param currentGame		The game information.
	 */
	public StoreTableModel(ArrayList<Item> items, GameLogic currentGame) {
		itemsList = items;
		game = currentGame;
	}
	
	
	/**
	 * Overrides the default TableModels method and gets the names of the columns.
	 * 
	 * @param col		Gets the name of a given column.
	 * 
	 * @return name		The given column name.
	 */
	@Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
	
	
	/**
	 * Overrides the default TableModels method and gets the number of rows being displayed.
	 * 
	 * @return size		The number of rows in the table. 
	 */
	@Override
	public int getRowCount() {
		return itemsList.size();
	}

	
	/**
	 * Overrides the default TableModels method and gets the number of columns being displayed.
	 * 
	 * @return size		The number of columns in the table.
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	
	/**
	 * Gets the list of items currently being shown in the table.
	 * 
	 * @return itemsList		A list of items being shown in the table.
	 */
	public ArrayList<Item> getList(){
		return itemsList;
	}
	
	
	/**
	 * Overrides the default TableModels method and gets the values of a given cell.
	 * 
	 * @param row			The row of the cell.
	 * @param col 			The column of the cell.
	 * 
	 * @return value		The number of columns in the table.
	 */
	@Override
	public Object getValueAt(int row, int col) {
        Item item = itemsList.get(row);
        ArrayList<String> items = game.getStoreBuying();
        switch (col) {
            case 0:
                return item.getName();
            case 1:
                return item.getPrice();
            case 2:
            	// Checks to see if the item is being bought for a higher amount.
            	if (items.contains(item.getName())) {
            		return(item.getPrice() * 10);
            	} else {
            		return((int) item.getPrice() / 2);
            	}
            case 3:
                return item.getSize();
            case 4:
                return item.getInfo();
            default:
                throw new IllegalStateException("No column defined for index " + col);
        }
    }

	
    /**
     * Get a list of the currently selected items in the table.
     *
     * @param selectedRows		An list of the indexes of the selected items.
     * 
     * @return list 			A list of items selected.
     */
    public ArrayList<Item> getSelectedItems(int[] selectedRows) {
    	ArrayList<Item> list = new ArrayList<>();
        for (int row : selectedRows) {
            list.add(itemsList.get(row));
        }
        return list;
    }

    
    /**
     * Get a the price total of the currently selected items in the table.
     *
     * @param selectedRows		An list of the indexes of the selected items.
     * 
     * @return list 			The value of the selected items.
     */
	public int getPriceTotal(int[] selectedRows) {
		int total = 0;
		for (int row : selectedRows) {
			total += itemsList.get(row).getPrice();
		}
		return total;
	}

	
    /**
     * Get a the weight of the currently selected items in the table.
     *
     * @param selectedRows		An list of the indexes of the selected items.
     * @param index				The index of a newly selected item.
     * 
     * @return total 			A total weight.
     */
	public double getWeightTotal(int[] selectedRows, int index) {
		// Finds the weight of a newly selected item.
		double total = itemsList.get(index).getSize();
		for (int row : selectedRows) {
			total += itemsList.get(row).getSize();
		}
		return total;
	}  
	
	
    /**
     * Updates the table items to show new items.
     *
     * @param items		The new items to be shown in the table.
     */
	public void updateItems(ArrayList<Item> items){
		itemsList = items;
		// Resets the table.
		this.fireTableDataChanged();
	}
	
}