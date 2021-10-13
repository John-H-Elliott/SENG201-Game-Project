package MainGameLogic;
import java.util.ArrayList;


/**
 * This class implements a Ship which is used for traveling between Islands
 * and stores the current goods and upgrades the player has brought.
 *
 * @author Ryan Boyd
 * @author John Elliott
 * @version 1.2, 16 April 2021.
 */
public class Ship {

    /**
     * Name of the Ship.
     */
    private String name;

    /**
     * Size of the crew.
     */
    private int crewSize;

    /**
     * Amount of Ship damage.
     */
    private int damageStatus;

    /**
     * Size of the cargo capacity.
     */
    private double cargoCapacity;

    /**
     * Base speed of the Ship.
     */
    private double sailSpeed;

    /**
     * Total cost to pay the crews wages.
     */
    private int wageTotal;

    /**
     * Base cost to repair 1% of the Ship multiplied by damageStatus to find total cost.
     */
    private int repairBase;

    /**
     * Holds the list of all Items currently being carried by the Ship.
     */
    private ArrayList<Item> itemsInCargo = new ArrayList<Item>();
    
    /**
     * Holds the list of all Items that have been sold by the player.
     */
    private ArrayList<Item> itemsSold = new ArrayList<Item>();

    /**
     * Holds the list of all Upgrades currently being carried by the Ship.
     */
    private ArrayList<Upgrade> upgrades = new ArrayList<>();


    /**
     * Initializes a Ship object.
     *
     * @param shipName      	Name of the Ship.
     * @param crewNum       	Number of crew on-board.
     * @param capacity      	The capacity to hold items and upgrades.
     * @param speed         	The Speed of the Ship.
     * @param allUpgrades		All the upgrades on the ship.
     */
    public Ship(String shipName, int crewNum, double capacity, double speed, ArrayList<Upgrade> allUpgrades){
        name = shipName;
        crewSize = crewNum;
        cargoCapacity = capacity;
        sailSpeed = speed;
        upgrades = allUpgrades;
        damageStatus = 0;
        // Determines the wageTotal and repair costs used when sailing
        wageTotal = (int) (crewSize / 4);
        repairBase = 5;
    }


    /**
     * A get method that returns the total wages cost.
     *
     * @param duration			The length of time to sail.
     * @return wageTotal        Cost of all the wages.
     */
    public int getWagesToPay(int duration) {
        return (wageTotal * duration);
    }

    
    /**
     * A get method that returns the total cargo size.
     *
     * @return cargoCapacity        The cargo size of the ship.
     */
    public double getCargoSize(){
        return cargoCapacity;
    }

    
    /**
     * Determines the size of the cargo left.
     *
     * @return cargoLeft        The cargo size remaining on the ship.
     */
    public double findCargoLeft(){
        double size = 0;
        for(Item item : itemsInCargo){
        	// Checks to make sure the item hasn't been sold.
            if (item.getIslandSoldTo() == "None") {
                size += item.getSize();
            }
        }
        // Add the cargo-upgrade and total then removes the current size of the cargo.
        return (cargoCapacity + getUpgrade(SetUp.CARGO).getCargoEffect()) - size;
    }

    
    /**
     * Add an item to the ships cargo.
     *
     * @param newitem        The item to be added to the ship cargo.
     */
    public void addItem(Item newitem){
        itemsInCargo.add(newitem);
    }

    
    /**
     * Returns a boolean that is true if the player can pay the wages for a given trip.
     *
     * @param currentFunds      The current wealth of the player.
     * @param duration          The duration of the planned travel.
     * @return canPay           Expresses if the player can pay the cost.
     */
    public boolean canPayWages(int currentFunds, int duration){
        return ((wageTotal * duration) <= currentFunds);
    }


    /**
     * Fixes the Ship and returns damageStatus to 0.
     * Then returns the cost of fixing the damage.
     *
     * @return repairTotal        Cost of total repair.
     */
    public int repairDamage() {
        int repairTotal = repairBase * damageStatus;
        damageStatus = 0;
        return repairTotal;
    }


    /**
     * Returns a boolean that is true if the player can pay the cost of the repairs needed.
     *
     * @param currentFunds      The current wealth of the player.
     * @return canPay           Expresses if the player can pay the cost.
     */
    public boolean canRepair(int currentFunds) {
        int repairTotal = repairBase * damageStatus;
        return (repairTotal <= currentFunds);
    }


    /**
     * Returns a boolean that is true if the Ship is currently damaged.
     *
     * @return isDamaged        Expresses if the Ship is currently damaged.
     */
    public boolean isDamaged(){
        return (damageStatus > 0);
    }


    /**
     * Determines the speed the ship is currently going at.
     *
     * @return sailSpeed        The speed of the Ship.
     */
    public double getSpeed() {
    	// Add the sailing upgrade to the ship. 
        return (sailSpeed - getUpgrade(SetUp.SAILS).getSpeedEffect());
    }

    
    /**
     * A get method for returning the item currently in the cargo.
     *
     * @return itemsInCargo        The items in the ship cargo.
     */
    public ArrayList<Item> getCargo() {
        return itemsInCargo;
    }

    
    /**
     * Returns the string representation of the ship currently being used.
     *
     * @return shipInfo        Information about the ship.
     */
    public String toString(){
        double cargoLeft = findCargoLeft();
        String shipInfo = "Currently Operating: " + name + "\nWage Cost (per day): " + wageTotal + "\nCargo Capacity Remaining: "
                + cargoLeft + "\nCurrent Ship Status: " + damageStatus + " (cost to repair " + (repairBase * damageStatus) + ")\n" + getUpgradesInfo();
        return shipInfo;
    }

    
    /**
     * Returns information of all current upgrades on the ship.
     *
     * @return upgradeInfo        Information about the current ship upgrades.
     */
    private String getUpgradesInfo() {
    	String upgradeInfo = "\n";
		for (Upgrade upgrade : upgrades) {
			upgradeInfo += upgrade.toString() + "\n";
		}
		return upgradeInfo;
	}


    /**
     * Returns information of all the current cargo in the ship. Both sold and not sold.
     *
     * @return cargo        Information about all the players cargo.
     */
	public String getAllCargoString() {
    	String cargo =  "";
    	// Checks if there are any items currently in the cargo or have been sold.
        if ((itemsInCargo.size() + itemsSold.size()) == 0){
            cargo = "No items in cargo.";
            return cargo;
        }
        // Makes one list of all items sold and not sold.
        ArrayList<Item> allItems = new ArrayList<Item>();
        allItems.addAll(itemsInCargo);
        allItems.addAll(itemsSold);
        
        for(Item item : allItems){
            int price = item.getPrice();
            cargo += item.getName() + " was purchased for " + price + ".";
            // Checks if the item has been sold.
            if (!(item.getIslandSoldTo() == "None")){
            	cargo += " It was sold for " + item.getSoldPrice() + " at " + item.getIslandSoldTo() + ".\n";
            } else {
            	cargo += "\n";
            }
        }
        return cargo;
    }

	
    /**
     * A get method that returns the items current in the cargo.
     *
     * @return itemsInCargo        A list of items in the cargo.
     */
    public ArrayList<Item> getItemWithinCargo() {
        return itemsInCargo;
    }


    /**
     * Sells an item in the ships cargo. Adds it to the itemsSold list.
     *
     * @param itemIndex        The index of the item to remove from the cargo.
     */
	public void itemSold(int itemIndex) {
		Item item = itemsInCargo.remove(itemIndex);
		itemsSold.add(item);
	}


    /**
     * A get method that finds a ships upgrade of a given type and returns that.
     *
     * @param  type			The type of ship upgrade being searched for.
     * @return upgrade      The Upgrade that matches the type.
     */
	public Upgrade getUpgrade(String type) {
		for (Upgrade upgrade : upgrades) {
			if (upgrade.getName() == type) {
				return upgrade;
			}
		}
		// No upgrade exists.
		return null;
	}


    /**
     * Returns the value of all items currently in the ships cargo.
     *
     * @return value      The value of all items in the cargo.
     */
	public int getCargoValue() {
		int value = 0;
		for (Item item : itemsInCargo) {
			value += item.getPrice();
		}
		return value;
	}


    /**
     * During the pirate event if lost this method is used to "steal" all the players cargo without giving them any increases in their wealth.
     */
	public void stealCargo() {
		// Makes a duplicate list as items will removed while iterating over the list.
		ArrayList<Item> items = new ArrayList<Item>();
		items.addAll(itemsInCargo);
		
		for (Item item : items) {
			item.setSoldPrice(0);
			item.setIslandSoldTo("THE PIRATES!");
			itemSold(itemsInCargo.indexOf(item));
		}	
	}


	/**
	 * Add damage to the ship.
	 * 
	 * @param damage		The damage to be added to the ship.
	 */
	public void addDamage(int damage) {
		damageStatus += damage;
	}
	
}