package MainGameLogic;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class implements a "static" class SetUp which hold the initialized information
 * for the MainGameLogic.GameEnvironment and can we accessed but not initialized itself.
 *
 * @author John Elliott
 * @version 1.1, 16 April 2021.
 */
public final class SetUp {
	
    /**
     * A constant that represents a hard route to an island.
     */
	public final static int HARD = 1;
	
    /**
     * A constant that represents a easy route to an island.
     */
	public final static int EASY = 0;
	
    /**
     * A constant that represents a weather event.
     */
	public final static String WEATHER = "Weather";
	
    /**
     * A constant that represents a sailor event.
     */
	public final static String SAILORS = "Sailors";
	
    /**
     * A constant that represents a pirate event.
     */
	public final static String PIRATES = "Pirates";
	
    /**
     * A constant that represents a Cargo Size Upgrade.
     */
	public final static String CARGO = "Cargo Size";
	
    /**
     * A constant that represents a Boat Sails Upgrade.
     */
	public final static String SAILS = "Boat Sails";
	
    /**
     * A constant that represents a Cannons Upgrade.
     */
	public final static String CANNONS = "Cannons";
	
	
    /**
     * So to not be initialized itself the constructor is made private.
     */
    private SetUp() {}


    /**
     * Uses default values and information to initialize all the Islands.
     * Adds each new MainGameLogic.Island to the islands ArrayList.
     * 
     * @return islands		A list of all default islands.
     */
    public static ArrayList<Island> initializeIslands(){
        ArrayList<Island> islands = new ArrayList<>();

        int[] cords1 = {1, 2};
        Island is1 = new Island("Plentiful Retreat", cords1);
        islands.add(is1);
        int[] cords2 = {3, 8};
        Island is2 = new Island("Sunken Reef", cords2);
        islands.add(is2);
        int[] cords3 = {5, 2};
        Island is3 = new Island("The Jeweled Isles", cords3);
        islands.add(is3);
        int[] cords4 = {7, 8};
        Island is4 = new Island("Craft-masters Enclave", cords4);
        islands.add(is4);
        int[] cords5 = {9, 4};
        Island is5 = new Island("Affluential Atoll", cords5);
        islands.add(is5);

        int[] risk = {5, 2};
        initializeAllRoutes(risk, islands);

        return islands;
    }


    /**
     * Is given a list of risk and will make a Route between each of the Islands
     * with each value of risk. This is so there can be multiple path with different benefits.
     *
     * @param risk     	 	A list of values that affect the difficulty of the Route.
     * @param islands		A list of all islands that need route initialized.
     */
    private static void initializeAllRoutes(int[] risk, ArrayList<Island> islands) {
        ArrayList<Island> tempIslands = new ArrayList<>(islands);
        for (Island currentIsland : islands) {
            tempIslands.remove(currentIsland);
            for(Island destinationIsland : tempIslands){
                for(int currentRisk : risk){
                    Route newToRoute = new Route(currentIsland, destinationIsland, currentRisk);
                    Route newFromRoute = new Route(destinationIsland, currentIsland, currentRisk);
                    currentIsland.addRoute(newToRoute);
                    destinationIsland.addRoute(newFromRoute);
                }
            }
        }
    }


    /**
     * Makes a Store for each MainGameLogic.Island within the islands list. These Store are per-determined.
     *
     * @param islands      A list of Islands currently in the MainGameLogic.GameEnvironment.
     */
    public static void initializeStores(ArrayList<Island> islands){
    	
        ArrayList<Item> storeOneSelling = initializeItems(1);
        String[] storeOneBuying = {"Blue Diamond", "Ruby", "Salt", "Marble Dresser", "Antique Writing Desk", "Unknown Technology"};
        Store storeOne = new Store(Arrays.asList(storeOneBuying), storeOneSelling, "Bountiful Pantry");
        islands.get(0).setStore(storeOne);

        ArrayList<Item> storeTwoSelling = initializeItems(2);
        String[] storeTwoBuying = {"Saffron", "Silk", "Incense", "Canopy Bed",};
        Store storeTwo = new Store(Arrays.asList(storeTwoBuying), storeTwoSelling, "Secure Vault");
        islands.get(1).setStore(storeTwo);

        ArrayList<Item> storeThreeSelling = initializeItems(3);
        String[] storeThreeBuying = {"Truffles", "Coffee Beans", "Treasure Map", "Mysterious Key", "Grandfather Clock"};
        Store storeThree = new Store(Arrays.asList(storeThreeBuying), storeThreeSelling, "Crystal Treasury");
        islands.get(2).setStore(storeThree);

        ArrayList<Item> storeFourSelling = initializeItems(4);
        String[] storeFourBuying = {"Faberge Eggs", "Ancient Scepter", "Emerald", "Obsidian", "Sugar", "Ivory"};
        Store storeFour = new Store(Arrays.asList(storeFourBuying), storeFourSelling, "Guild Workshop");
        islands.get(3).setStore(storeFour);

        ArrayList<Item> storeFiveSelling = initializeItems(5);
        String[] storeFiveBuying = {"Embroidered Carpet", "Caviar", "Oysters", "Alexandrite"};
        Store storeFive = new Store(Arrays.asList(storeFiveBuying), storeFiveSelling, "Traders Depot");
        islands.get(4).setStore(storeFive);

    }


    /**
     * Makes a Ship for the MainGameLogic.GameLogic game. These Ships are per-determined.
     *
     * @param shipNum      The value of the ship.
     * 
     * @return ship		   The ship that the player will use.
     */
    public static Ship initializeShip(int shipNum){
        Ship newShip;
        ArrayList<Upgrade> upgrades = initializeUpgrades();
        switch (shipNum){
            case 1:
                newShip = new Ship("Barquentine", 100, 350, 0.8, upgrades);
                break;
            case 2:
                newShip = new Ship("Brigantine", 125, 400, 0.9, upgrades);
                break;
            case 3:
                newShip = new Ship("Galleon", 150, 500, 0.9, upgrades);
                break;
            case 4:
                newShip = new Ship("Sloop", 80, 250, 0.5, upgrades);
                break;
            default:
                System.out.println("An Error has occurred while creating the Ship.");
                newShip = new Ship("Barquentine (Error)", 100, 350, 0.8, upgrades);
        }
        return newShip;
    }


    /**
     * Given the current Store, determined by itemToGet, a list of Items are initialized that
     * the Store will sell.
     *
     * @param itemToGet         Determines which list of Items will be created and returned.
     *
     * @return itemsToSell      A list of Items that will be sold at a given Store.
     */
    private static ArrayList<Item> initializeItems(int itemToGet){
        ArrayList<Item> itemsToSell = new ArrayList<>();
        itemLoop:
        switch (itemToGet){
            case 1:
                itemsToSell.add(new Item("Saffron", "a crimson stigma found within the crocus flowers.", 125, 75));
                itemsToSell.add(new Item("Caviar", "a picked roe of the sturgeon fish.", 100, 75));
                itemsToSell.add(new Item("Oysters", "a sea-water molluscs.", 100, 80));
                itemsToSell.add(new Item("Truffles", "a fungus that grows near the roots of trees.", 120, 70));
                itemsToSell.add(new Item("Coffee Beans", "Seed of the coffea plant.", 80, 80));
                break itemLoop;
            case 2:
                itemsToSell.add(new Item("Faberge Eggs", "a jewelled egg created by the House of Faberge.", 150, 70));
                itemsToSell.add(new Item("Treasure Map", "a map which may lead to buried treasure.", 90, 80));
                itemsToSell.add(new Item("Mysterious Key", "a golden key with a unknown lock.", 95, 85));
                itemsToSell.add(new Item("Unknown Technology", "a strange piece of technology which flashes colours.", 130, 100));
                itemsToSell.add(new Item("Ancient Scepter", "a staff once held in the hand of a ruling monarch.", 120, 90));
                break itemLoop;
            case 3:
                itemsToSell.add(new Item("Blue Diamond", "a diamond colored blue by trace amounts of boron within its crystal.", 135, 85));
                itemsToSell.add(new Item("Ruby", "a blood-red coloured gemstone.", 90, 70));
                itemsToSell.add(new Item("Emerald", "a variety of the mineral beryl colored green by trace amounts of chromium.", 90, 80));
                itemsToSell.add(new Item("Alexandrite", "a colour changing gem that swaps between red and green.", 105, 85));
                itemsToSell.add(new Item("Obsidian", "a naturally occurring volcanic glass.", 70, 80));
                break itemLoop;
            case 4:
                itemsToSell.add(new Item("Grandfather Clock", "a tall pendulum clock.", 120, 100));
                itemsToSell.add(new Item("Canopy Bed", "a large bed with curations surrounding it.", 90, 90));
                itemsToSell.add(new Item("Marble Dresser", "a white marble dresser.", 80, 85));
                itemsToSell.add(new Item("Antique Writing Desk", "a old wooden desk.", 90, 85));
                itemsToSell.add(new Item("Embroidered Carpet", "a handmade carpet with a unique pattern.", 100, 95));
                break itemLoop;
            case 5:
                itemsToSell.add(new Item("Incense", "a aromatic biotic material that releases fragrant smoke when burned.", 100, 95));
                itemsToSell.add(new Item("Ivory", "a hard, white material from the tusks and teeth of animals.", 125, 95));
                itemsToSell.add(new Item("Salt", "a ingredient used in cooking to flavour food.", 90, 90));
                itemsToSell.add(new Item("Silk", "a natural fiber  which can be woven into textiles.", 70, 80));
                itemsToSell.add(new Item("Sugar", "a ingredient used to add sweetness to food.", 80, 85));
                break itemLoop;
            default:
                System.out.println("An Error has occurred while creating the items for a store.");
        }
        return itemsToSell;
    }
    
    
    /**
     * Initializes all the upgrades for the ship. They start with zero of all.
     * 
     * @return upgrades		A list of all upgrades the ship will have.
     */
    private static ArrayList<Upgrade> initializeUpgrades(){
    	ArrayList<Upgrade> upgrades = new ArrayList<>();
    	upgrades.add(new Upgrade("Cannons", "Increases your chances of winning a fight.", 500, 30));
    	upgrades.add(new Upgrade("Boat Sails", "Increases your speed.", 500, 0));
    	upgrades.add(new Upgrade("Cargo Size", "Increases your cargo size.", 750, 0));
    	return upgrades;
    }
    
    
    /**
     * Gets the text used to explain the game rules.
     * 
     * @return tutText		Explains how to play the game.
     */
    public static String getTutorialText() {
    	String tutText = "-- How To Play --\r\n"
    			+ "\r\n"
    			+ "Welcome to Island trader, heres some information and a few tips to help get you sailing!\r\n"
    			+ "is to collect as high a profit as you can from buying and selling unique items between different islands. You have a limited amount of days\r\n"
    			+ "on your journey and you will need to watch out, the seas can be dangerous! Running out of money means game over, the game also ends when the \"Days Left\"\r\n"
    			+ "count reaches 0.\r\n"
    			+ "\r\n"
    			+ "The first thing to look at are the main actions. To the left you will see some buttons marked with text, each of these is valuable to helping you on your journey.\r\n"
    			+ "\r\n"
    			+ "- View Cargo -\r\n"
    			+ "The \"View Cargo\" button lets you see all the treasures your trading ship has bought on its journey and how much for. Clicking this button\r\n"
    			+ "will display a list of every item currently in your possession and how much you paid for it, if you have sold the item it will also show you\r\n"
    			+ "how much you sold it for and what island it was sold to.\r\n"
    			+ "\r\n"
    			+ "- Go To Store -\r\n"
    			+ "This button brings up the currently docked islands store, here you can choose to buy or sell items, clicking either of these options brings up a table\r\n"
    			+ "with item names and descriptions, keep in mind that some stores buy certain items at 2x the price! There is also a \"Get Upgrades\" option, more on that later.\r\n"
    			+ "\r\n"
    			+ "- View Ship Info - \r\n"
    			+ "Here you can see the status of your ship, any upgrades that have been purchased and if your ship has sustained any damage that needs to be repaired.\r\n"
    			+ "\r\n"
    			+ "-- The Map --\r\n"
    			+ "\r\n"
    			+ "The next thing to look at is the map, there are always five islands on the map and each one is different.\r\n"
    			+ "To view an islands name hover over the island with your mouse. clicking on the island brings up a few options where your previous actions were, you can go back\r\n"
    			+ "if you still wish to do anything on the current island, if not take a look at the new options.\r\n"
    			+ "\r\n"
    			+ "- Items Sold By The Shop -\r\n"
    			+ "Exactly what it says on the tin. clicking this brings up a preview of the store so you can know what you can expect to be able to purchase before you arrive.\r\n"
    			+ "\r\n"
    			+ "- Items Bought By The Shop -\r\n"
    			+ "Here you can see which items the selected islands store is willing to pay a premium for! all the items on this list will be purchased for double the amount you paid, guaranteed!\r\n"
    			+ "\r\n"
    			+ "- Set Sail To Island -\r\n"
    			+ "Finally the most exciting option. clicking this will allow you to embark on a voyage to the selected island. Once on the high seas anything can happen!\r\n"
    			+ "\r\n"
    			+ "-- Other things to know and helpful tips --\r\n"
    			+ "\r\n"
    			+ "Random events can occur at sea. the chances of these occuring depends on how risky the route is between each island. some of these events are good while others can incur damages\r\n"
    			+ "for the player. to minimize risk its best to invest in upgrades which can help the ship do better at certain things, these are purchased through Go To Store > Get Upgrades, here you\r\n"
    			+ "can choose to upgrade a certain part of your ship, each of these have different effects which all help in some way.\r\n"
    			+ "Your crew doesnt work for free! sailing to an island means you will have to pay the wages of your crewmates, this amount depends on how big a ship you are running. Be careful not to run out\r\n"
    			+ "of money on wages otherwise your journey will come to an end.\r\n"
    			+ "Not all routes are equal, you will find that the more dangerous routes are also quicker meaning you can trade more in the time you have. make decisions wisely\r\n"
    			+ "The best way to turn a profit is by looking for what islands are willing to pay extra for, any good trader should always be looking for the best deal!";
    	return tutText;
    }
    
}
