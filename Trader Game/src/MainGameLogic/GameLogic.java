package MainGameLogic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class implements an the gameEnvironment which is used to run the main logic.
 * Such as allowing the player to choose what to do like sailing or buying/selling items.
 * It also determines when the game is over.
 *
 * @author John Elliott
 * @version 1.1, 16 April 2021.
 */
public class GameLogic {

    /**
     * The name of the player.
     */
    private String playerName;

    /**
     * The current wealth of the player.
     */
    private int wealth;

    /**
     * The number of days left until the game is over.
     */
    private int daysLeft;

    /**
     * The current Ship being used.
     */
    private Ship operatingShip;

    /**
     * The MainGameLogic.Island the player is currently on.
     */
    private Island currentIsland;

    /**
     * A list of all the Islands within the game.
     */
    private final ArrayList<Island> islands;

    /**
     * A boolean that determine if the game is over which keeps the main game loop running.
     */
    private boolean gameActive;

    /**
     * The number of days selected by the player.
     */
	private int selectedDays;
	
	
    /**
     * Initializes an main MainGameLogic.GameEnvironment which initializes all the Islands, Routes and Stores that will be in the game.
     * Along with the initial condition for the player.
     */
    public GameLogic(){
        gameActive = true;
        wealth = 2000;
        islands = SetUp.initializeIslands();
        SetUp.initializeStores(islands);
        // Default island
        currentIsland = islands.get(0);	
    }


    /**
     * A set method for the players name.
     * 
     * @param name		The players name.
     */
	public void setName(String name) {
		playerName = name;
	}
	
	
    /**
     * A set method for the number of days being played.
     * Also sets daysLeft which decrease during the game.
     * 
     * @param length		The number of game days.
     */
	public void setDays(int length) {
		selectedDays = daysLeft = length;
		daysLeft = selectedDays;
	}
	
	
    /**
     * A get method for the number of days selected to be played.
     * 
     * @return selectedDays		The number of selected days.
     */
	public int getSelectedDays() {
		return selectedDays;
	}
	
	
    /**
     * A set method for the type of ship that player will use. Take a ship number and initializes the given ship.
     * 
     * @param shipNum		The ship number.
     */
	public void setShip(int shipNum) {
		operatingShip = SetUp.initializeShip(shipNum);
	}
    
    
    /**
     * Takes a players given name.
     * Then checks to make sure its not using special characters,
     * is longer than 3 but less than 15 characters and has only single white spaces.
     *
     * @param name		  The name entered by the user.
     * @return result     A boolean of true if the names valid.
     */
    public boolean checkName(String name){
    	boolean result = false;
        char[] nameList =  name.toCharArray();
        boolean seenSpace = false;

        if (!((name.length() < 3) || (name.length() > 15))){
        	for (char cha : nameList){
        		// This means the char value isn't a standard value.
        		if (!Character.isLetter(cha) && !Character.isWhitespace(cha)){
        			result = false;
    	            break;
    	            
        		} else if (Character.isWhitespace(cha)) {
        			// If seenSpace is already true then there are two spaces in a row.
        			if (seenSpace){
        				result = false;
    	                break;
        			} else {
        				seenSpace = true;
        				result = true;
    	            }
    	            
        		} else {
        			result = true;
    	            seenSpace = false;    
        		}
        	}
        }
        
        return result;
    }


    /**
     * A get method for the players current wealth.
     * 
     * @return wealth		The players wealth.
     */
	public int getWealth() {
		return wealth;
	}


    /**
     * A get method for the number of game left to be played.
     * 
     * @return daysLeft		The days left in the game.
     */
	public int getDays() {
		return daysLeft;
	}


    /**
     * A get method for the players name.
     * 
     * @return playerName		The players name.
     */
	public String getName() {
		return playerName;
	}


    /**
     * Given the island index find the name of that island.
     * 
     * @param islandNum		The island index.
     * @return islandName	The name of the given island.
     */
	public String getIslandName(int islandNum) {
		return islands.get(islandNum).getName();
	}


    /**
     * Gets the information related to the current island.
     * 
     * @return islandInfo	Information about the island.
     */
	public String getIslandInfo() {
		return currentIsland.toString(currentIsland);
	}


    /**
     * Gets the information related to the current and past cargo brought by the user.
     * 
     * @return cargoInfo	Information about the current and past cargo.
     */
	public String getCargo() {
		return operatingShip.getAllCargoString();
	}


    /**
     * Gets the information related to all the items currently being sold at the Store.
     * 
     * @param islandNum			The index of the island thats used to get the current store.
     * 
     * @return sellingInfo		Information about the items being sold.
     */
	public String soldAtStore(int islandNum) {
		Store currentStore = islands.get(islandNum).getStore();
		return currentStore.sellingString();
	}


    /**
     * Gets the information related to all the items currently being brought at the Store.
     * 
     * @param islandNum			The index of the island thats used to get the current store.
     * 
     * @return buyingInfo	Information about the items being brought.
     */
	public String broughtAtStore(int islandNum) {
		Store currentStore = islands.get(islandNum).getStore();
		return currentStore.buyingString();
	}


    /**
     * Gets the information related to the users ship.
     * 
     * @return shipInfo		Information about the ship.
     */
	public String getShipInfo() {
		return operatingShip.toString();
	}


    /**
     * Gets the index of the current Island the player is at.
     * 
     * @return islandIndex		Index of the current island.
     */
	public int getIslandIndex() {
		return islands.indexOf(currentIsland);
	}


    /**
     * Gets the a list of all items currently being sold at the Store.
     * 
     * @return items	List of all items being sold.
     */
	public ArrayList<Item> getStoreItems() {
		Store currentStore = currentIsland.getStore();
		ArrayList<Item> items = currentStore.getItemsToSell();
		return items;
	}


    /**
     * Gets the a list of index which are to be brought by the player.
     * 
     * @param items		List index which are to be brought.
     */
	public void buyItems(int[] items) {
		Store currentStore = currentIsland.getStore();
		List<Integer> indexList = new ArrayList<Integer>(items.length);
		for (int item : items)
		{
			indexList.add(item);
		}
		// Reverses the index's as while the Items are brought the list size is reduced. 
		// So needs to start from the largest index so it doesn't have an index error.
		Collections.reverse(indexList);
		for (int item : indexList) {
			int price = currentStore.buyItem(item, operatingShip);
			wealth -= price;
		}
		
	}

	
    /**
     * Gets the ship being used by the player.
     * 
     * @return ship		The players ship.
     */
	public Ship getShip() {
		return operatingShip;
	}


    /**
     * Gets the a list of all items which have not been sold or stolen yet.
     * 
     * @return items	List of all items currently in the cargo.
     */
	public ArrayList<Item> getCargoItems() {
		return operatingShip.getItemWithinCargo();
	}


    /**
     * Gets the a list of index which are to be sold by the player.
     * 
     * @param items		List index which are to be sold.
     */
	public void sellItems(int[] items) {
		Store currentStore = currentIsland.getStore();
		List<Integer> indexList = new ArrayList<Integer>(items.length);
		for (int item : items)
		{
			indexList.add(item);
		}
		// Reverses the index's as while the Items are brought the list size is reduced. 
		// So needs to start from the largest index so it doesn't have an index error.
		Collections.reverse(indexList);
		for (int item : indexList) {
			int price = currentStore.sellItem(item, operatingShip, currentIsland);
			wealth += price;
		}
		
	}


    /**
     * Determines if the player can currently pay to repair damage to their boat.
     * 
     * @return result	A boolean that true if they can repair the boat.
     */
	public boolean canRepair() {
		return operatingShip.canRepair(wealth);
	}


    /**
     * Repairs all damage to the boat.
     */
	public void repair() {
		int cost = operatingShip.repairDamage();
		wealth -= cost;
	}

	
    /**
     * Determines if the player can currently afford to sail to the new island.
     * 
     * @param newIslandIndex	The index of the new island.
     * @param routeRisk			The index of the route.
     * @return result			A boolean that true if they can pay the wages.
     */
	public boolean canSail(int newIslandIndex, int routeRisk) {
		Island newIsland = islands.get(newIslandIndex);
		Route currentRoutes = currentIsland.getRoutes(newIsland).get(routeRisk);
		int duration = currentRoutes.findDuration(operatingShip.getSpeed());
		// Can pay wages.
		boolean canPay = operatingShip.canPayWages(wealth, duration);
		// Can reach there in the number of days left.
		boolean canReach = (duration < daysLeft);
		if (canPay && canReach) {
	        	return true;
	    	}
		return false;
	}

		
    /**
     * Determines if the player can currently afford to sail to island on any route between two islands.
     * 
     * @param islandIndex		The index of the new island.
     * @return result			A boolean that true if they can pay the wages.
     */
	public boolean canSail(int islandIndex) {
		Island newIsland = islands.get(islandIndex);
		ArrayList<Route> currentRoutes = currentIsland.getRoutes(newIsland);
		for (Route route : currentRoutes) {
	        int duration = route.findDuration(operatingShip.getSpeed());
	        if (operatingShip.canPayWages(wealth, duration)) {
	        	return true;
	        }
		}

		return false;
	}


    /**
     * Sails the player to a new island but also determines the event that occurs during the journey.
     * 
     * @param newIslandIndex	The index of the new island.
     * @param routeRisk			The index of the route.
     * @return event			The event that occurred.
     */
	public RandomEvents sail(int newIslandIndex, int routeRisk) {
		Island newIsland = islands.get(newIslandIndex);
		Route currentRoute = currentIsland.getRoutes(newIsland).get(routeRisk);
		RandomEvents event = new RandomEvents(currentRoute.getRisk());
		
		// If the event was nothing then there isn't an event to be resolved.
		if (event.getType() != "None") {
			handelEvent(event);
		} else {
			String island = currentRoute.getDestination().getName();
			event.setOutcome("Nothing happened and you are able to reach " + island + " safely.");
		}
		
		// Resolves the new game information.
		int duration = currentRoute.findDuration(operatingShip.getSpeed());
		daysLeft -= duration;
		wealth -= operatingShip.getWagesToPay(duration);
        currentIsland = currentRoute.getDestination();
        
        // Returns the event so the outcome can be show to the player.
        return event;
	}

	
	/**
     * When an event has occurred this method is called to determine the outcome of the event.
     * 
     * @param event		The event that occurred.
     */
	private void handelEvent(RandomEvents event) {
		switch (event.getType()){
			case "Pirates":
				event.eventPirates(operatingShip.getUpgrade("Cannons"));
				break;
			case "Weather":
				int damage = event.eventWeather();
				operatingShip.addDamage(damage);
				break;
			case "Sailors":
				int reward = event.eventSailors();
				wealth += reward;
				break;
		}
	}

	
	/**
     * Determines if the ship has had any damage done to it.
     * 
     * @return result		A boolean true if the ship is damaged.
     */
	public boolean isShipDamaged() {
		return operatingShip.isDamaged();
	}


    /**
     * Gets the items currently being brought at the Store.
     * 
     * @return items	List of items that are being brought.
     */
	public ArrayList<String> getStoreBuying() {
		Store currentStore = currentIsland.getStore();
		ArrayList<String> items = currentStore.getItemsToBuy();
		return items;
	}


    /**
     * Given a type get the upgrade currently on the ship.
     * 
     * @param type		The upgrade type.
     * @return items	List of items that are being brought.
     */
	public Upgrade getShipUpgrades(String type) {
		return operatingShip.getUpgrade(type);
	}


    /**
     * Given a type will buy a given amount of upgrades and add them to the ship.
     * 
     * @param type		The upgrade type.
     * @param amount	The amount of new upgrades.
     */
	public void buyShipUpgrades(String type, int amount) {
		Upgrade upgrade = getShipUpgrades(type);
		wealth -= (upgrade.getPrice() * amount);
		upgrade.addQuantity(amount);
	}
	
	
    /**
     * Given a islands index will return that island.
     * 
     * @param islandIndex		The islands index.
     * @return island			The island for the given index.
     */
	public Island getIsland(int islandIndex) {
		return islands.get(islandIndex);
	}


    /**
     * Returns the current game status.
     * 
     * @return gameActive		Current game status.
     */
	public boolean getGameActive() {
		return gameActive;
	}


    /**
     * During the pirate event if the player loses this method is used to determine if the player is allowed to live or not.
     * Meaning if the player doesn't have enough cargo value it is game over.
     * 
     * @param event		Current game status.
     */
	public void determinePirateGameActive(RandomEvents event) {
		// A random value that the cargo value must beat.
		int value = RandomEvents.getRandomNumber(500);
		if (operatingShip.getCargoValue() >= value) {
			operatingShip.stealCargo();
			event.setOutcome("The Pirates board your ship and steal all your cargo on board but let you go.");
		} else {
			gameActive = false;
			event.setOutcome("The Pirates board your ship and after seeing you small supple of cargo send you off the plank.");
		}
	}


    /**
     * Gets information about the a given route.
     * 
     * @param newIsland			The index of the new island.
     * @param risk				The index of the route.
     * @return routeInfo		The event that occurred.
     */
	public String getRouteInfo(int newIsland, int risk) {
		Route route = islands.get(newIsland).getRoutes().get(risk);
		int duration = route.findDuration(operatingShip.getSpeed());
		String name = route.getDestination().getName();
		return "The duration is " + duration + " days to travelling to "+name+".\n";
	}


    /**
     * This method checks if the player can from the current island sail to a new island.
     * Checks if the no route is able to be completed before the games over or they all cost to much to be sailed.
     * 
     * @return active		A boolean thats true if games still active.
     */
	public boolean canContiune() {
		// This is used to make sure that the player can't just sell items to continue.
		int profit = currentIsland.getStore().findProfit(operatingShip.getCargo());
		double speed = operatingShip.getSpeed();
		for (Route route : currentIsland.getRoutes()) {
			int wages = operatingShip.getWagesToPay(route.findDuration(speed));
			// Checks if either they number of days left is to little to sail or they can't afford to sail.
			if ((daysLeft > route.findDuration(speed)) && ((profit + wealth) >= wages)) {
				gameActive = true;
				return true;
			}
		}
		// No route can be taken.
		gameActive = false;
		return false;
	}

	
	/**
	 * Sells all remaining cargo in the ship and updates the wealth of the player.
	 * This is used when the game is over to allow the player to get the max score they could.
	 */
	public void sellRemainingCargo() {
		ArrayList<Item> items = operatingShip.getCargo();
		Store store = currentIsland.getStore();
		int value = store.sellItems(items);
		wealth += value;
	}
}