package MainGameLogic;


/**
 * This class is the parent to all class within the game that can be brought or traded
 * by the player during the game.
 *
 * @author John Elliott
 * @version 1.1, 22 April 2021.
 */
public class Purchasable {

    /**
     * The name of the object.
     */
    private String itemName;

    /**
     * Information about the object.
     */
    private String info;

    /**
     * The default price of the object.
     */
    private int basePrice;

    /**
     * The size of the object.
     */
    private double size;


    /**
     * Initializes the Purchasable object which can be brought at Stores during the game
     *
     * @param name          The objects name.
     * @param itemInfo      Information relating to the object.
     * @param price         The price of the object.
     * @param scale         The size of the object.
     */
    public Purchasable(String name, String itemInfo, int price, double scale){
        itemName = name;
        info = itemInfo;
        basePrice = price;
        size = scale;
    }

    /**
     * A get method used to get the name of the object.
     *
     * @return itemName     The objects name.
     */
    public String getName(){
        return itemName;
    }


    /**
     * A get method used to get the information of the object.
     *
     * @return info     Information about the object.
     */
    public String getInfo(){
        return info;
    }


    /**
     * A get method used to get the size of the object.
     *
     * @return size     The objects size.
     */
    public double getSize(){
        return size;
    }


    /**
     * A get method used to get the price of the object.
     *
     * @return basePrice     The objects price to buy.
     */
    public int getPrice(){
        return basePrice;
    }

}
