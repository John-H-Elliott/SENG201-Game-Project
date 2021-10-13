package MainGameLogic;


/**
 * A MainGameLogic.Item that can be brought and sold in order to gain a profit during the game.
 *
 * @author John Elliott
 * @version 1.1, 22 April 2021.
 */
public class Item extends Purchasable {

    /**
     * Stores the items sold location.
     */
    private String islandSoldTo;
    
    /**
     * The price the item was sold for.
     */
    private int soldPrice;

    
    /**
     * Initializes the MainGameLogic.Item object which can be brought and sold at Stores during the game.
     * Calls its parent class Purchasable.
     *
     * @param name          The objects name.
     * @param itemInfo      Information relating to the object.
     * @param price         The price of the object.
     * @param scale         The size of the object.
     */
    public Item(String name, String itemInfo, int price, double scale){
        super(name, itemInfo, price, scale);
        islandSoldTo = "None";
    }


    /**
     * A method that returns the String version of the MainGameLogic.Item class.
     *
     * @return information      A String which represents the MainGameLogic.Item object as a String object.
     */
    public String toString(){
        String itemSizeString = Double.toString(getSize());
        String information = (getName() + ", " + getInfo() + " It is being sold for "+ getPrice() +" and its size is " + itemSizeString + ".\n");
        return information;
    }

    
    /**
     * A get method for the price the item was sold for.
     * 
     * @return soldPrice		Items sold price.
     */
    public int getSoldPrice() { 
    	return soldPrice; 
    }

    
    /**
     * A get method for the island the item was sold to. If the item wasn't sold yet returns "None".
     * 
     * @return islandSoldTo		The island the item was sold to.
     */
    public String getIslandSoldTo() { 
    	return islandSoldTo; 
    }

    
    /**
     * A set method for which island the item was sold to.
     * 
     * @param newIsland		The island the item was sold at.
     */
    public void setIslandSoldTo(String newIsland) {
        islandSoldTo = newIsland;
    }

    
    /**
     * A set method for the price the item was sold for.
     * 
     * @param price		The price the item was sold for.
     */
    public void setSoldPrice(int price) {
        soldPrice = price;
    }

}
