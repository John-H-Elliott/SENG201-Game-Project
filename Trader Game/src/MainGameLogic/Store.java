package MainGameLogic;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents the Stores in game and holds the items each have along with
 * the code needed to buy and sell items to the Store.
 *
 * @author John Elliott
 * @version 1.1, 22 April 2021.
 */
public class Store {

    /**
     * The Stores name.
     */
    private String name;

    /**
     * List of Items the Store is currently selling.
     */
    private  ArrayList<Item> itemsToSell;

    /**
     * A list of Items names the Store will buy at a higher price.
     */
    private  List<String> itemsToBuy;


    /**
     * Initializes the Store object which is used when the player wishes to buy or sell items
     * during their visit to the Store on the current MainGameLogic.Island.
     *
     * @param buying        A list of Strings they Store will buy.
     * @param selling       A list of Items the Store will sell.
     * @param shopName		The name of the store.
     */
    public Store(List<String> buying, ArrayList<Item> selling, String shopName){
        itemsToBuy = buying;
        itemsToSell = selling;
        name = shopName;
    }


    /**
     * A method used buy a given MainGameLogic.Item from the Store. Adds the item to the ship and removes it from the Store.
     * Returns the price of the Item so its price can be removed from the players wealth.
     *
     * @param index     	The index of the MainGameLogic.Item within the Store, MainGameLogic.Item list.
     * @param ship      	The current Ship the player is using, to determine if the MainGameLogic.Item can fit.
     *
     * @return itemPrice   	The price it cost to buy the item.
     */
    public int buyItem(int index, Ship ship){
        Item currentItem = itemsToSell.get(index);
        int itemPrice = currentItem.getPrice();
        ship.addItem(currentItem);
        itemsToSell.remove(index);
        return itemPrice;
    }


    /**
     * Returns a string with information about the items being bought at the Store.
     * 
     * @return buying   	Information about the items being bought.
     */
    public String buyingString(){
        String buying = "Items currently being brought at higher prices at " + name + ":\n";
        for (String item : itemsToBuy){
            buying += item + " will be brought for 10x the orginal price!\n";
        }
        return buying;
    }
    
    
    /**
     * Returns a string with information about the items being sold at the Store.
     * 
     * @return selling   	Information about the items being sold.
     */
    public String sellingString(){
        String selling = "Items currently being sold at " + name + ":\n";
        for (Item item : itemsToSell){
           selling += item;
        }
        return selling;
    }


    /**
     * A get method used to get the list of items being sold.
     * 
     * @return itemsToSell   	A list of items being sold.
     */
    public ArrayList<Item> getItemsToSell() { return itemsToSell; }

    
    /**
     * Get an item from the ships cargo and finds its base price. Then determines if the price is increases or decreased.
     * Finally removes the item from the Ship and adds its price to the players wealth.
     *
     * @param itemIndex     	The index of the MainGameLogic.Item within the ships cargo.
     * @param ship      		The current Ship the player is using so the item can be removed.
     * @param currentIsland		The Island the player is on so the Store can be checked if the items price is increased. 
     *
     * @return price   			The price it was sold at so it can be added to the players wealth.
     */
    public int sellItem(int itemIndex, Ship ship, Island currentIsland) {
        ArrayList<Item> items = ship.getItemWithinCargo();
        Item item = items.get(itemIndex);
        // Determines the price that it will be sold at. Either 10x or 0.5x
        int price = item.getPrice();
        if (itemsToBuy.contains(item.getName())){
            price *= 10;
        } else {
        	price = (int) price / 2;
        }
        
        item.setIslandSoldTo(currentIsland.getName());
        item.setSoldPrice(price);
        ship.itemSold(itemIndex);
        return price;
    }


    /**
     * A get method used to get the list of items names that are being bought.
     * 
     * @return buying   	A list of items being bought.
     */
	public ArrayList<String> getItemsToBuy() {
		// Converts the String[] to a ArrayList<String>.
		ArrayList<String> buying = new ArrayList<String>();
		for (String item : itemsToBuy) {
			buying.add(item);
		}
		return buying;
	}


    /**
     * Find the maximum profit that could be made at the Store with a given list of items.
     * 
     * @param cargo			A list of items.
     * 
     * @return profit   	The profit that can be made.
     */
	public int findProfit(ArrayList<Item> cargo) {
		int profit = 0;
		for (Item item : cargo) {
			if (itemsToBuy.contains(item.getName())) {
				profit += item.getSoldPrice() * 10;
			} else {
				profit += item.getSoldPrice() / 2;
			}
		}
		return profit;
	}


	/**
	 * Sells all items in a list to the store.
	 * 
	 * @param items				A list of items to sell.
	 * @return totalPrice		The total price gained.
	 */
	public int sellItems(ArrayList<Item> items) {
		int totalPrice = 0;
		for (Item item : items) {
			int price = item.getPrice();
			if (itemsToBuy.contains(item.getName())) {
				price *= 10;
			} else {
				price = (int) price / 2;
			}
			totalPrice += price;
		}
		return totalPrice;
	}
    
}