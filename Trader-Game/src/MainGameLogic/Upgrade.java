package MainGameLogic;


/**
 * A MainGameLogic.Upgrade that can be brought to increase the states of the players ship.
 *
 * @author John Elliott
 * @version 1.1, 22 April 2021.
 */
public class Upgrade extends Purchasable {

    /**
     * Is the number of that Upgrade the ship currently has.
     */
    private int quantity;


    /**
     * Initializes the Upgrade object which can be brought at Stores during the game.
     * It effects events like pirate boarding the players ship.
     * Calls its parent class Purchasable.
     *
     * @param name          The objects name.
     * @param itemInfo      Information relating to the object.
     * @param price         The price of the object.
     * @param scale         The size of the object.
     */
    public Upgrade(String name, String itemInfo, int price, double scale){
        super(name, itemInfo, price, scale);
        // The ship always starts with no upgrades.
        quantity = 0;
    }


    /**
     * A method that increase the current amount of a given upgrade a player has.
     * 
     * @param amount		The new added amount of that upgrade.
     */
	public void addQuantity(int amount) {
		quantity += amount;
	}
    
	
    /**
     * A method that increase the current amount of a given upgrade a player has.
     * 
     * @return upgradeInfo		Information about the upgrade.
     */
	public String toString() {
		String upgradeInfo = getName() + ": (Amount) " + quantity + " (" + getInfo() + ")";
		return upgradeInfo;
	}


    /**
     * A method that determines the extra cargo got from this upgrade.
     * Zero if it isn't a cargo upgrade.
     * 
     * @return effect		The given effects ability.
     */
	public double getCargoEffect() {
		if (getName() == SetUp.CARGO) {
			return (quantity * 50.0);
		} else {
			return 0;
		}
	}
	
	
    /**
     * A method that determines the extra speed got from this upgrade.
     * Zero if it isn't a speed upgrade.
     * 
     * @return effect		The given effects ability.
     */
	public double getSpeedEffect() {
		if (getName() == SetUp.SAILS) {
			return (quantity * 0.1);
		} else {
			return 0;
		}
	}
	
	
    /**
     * A method that determines the ability to increase your odds at fighting pirates.
     * Zero if it isn't a pirate upgrade.
     * 
     * @return effect		The given effects ability.
     */
	public double getCannonEffect() {
		if (getName() == SetUp.CANNONS) {
			return (quantity * 0.5);
		} else {
			return 0;
		}
	}


	
    /**
     * A get method that returns the current upgrade quantity.
     * 
     * @return quantity		The number of the given upgrade currently owned.
     */
	public int getQuantity() {
		return quantity;
	}
}
