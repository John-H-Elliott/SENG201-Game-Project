package MainGameLogic;
import java.util.concurrent.ThreadLocalRandom;


/**
 * This class implements RandomEvents. This allows for events that can occur to be determined and then for their outcome to be determined.
 * These events can occur during sailing. There is a 50% chance that an event will occur and the type of event is based on the players chosen routes risk.
 *
 * @author John Elliott
 * @version 1.1, 16 April 2021.
 */
public class RandomEvents {

    /**
     * The type of event this is.
     */
	private String type;
	
    /**
     * The outcome of the event.
     */
	private String outcome;
	
    /**
     * The effect of the players cannons Upgrade.
     */
	private double effect;
	
    /**
     * A value used to keep track of the pirate rolling game.
     */
	private int won;
	
	
    /**
     * Initialized a event and determines if an event has actually occurred or not, this is a 50% chance.
     * 
     * @param diffculty		A value used to determine the likelihood of a pirate event.
     */
    public RandomEvents(int diffculty) {
    	int number = getRandomNumber(100);
    	won = 0;
    	if (number <= 50) {
    		determineRandomEvent(diffculty);
    	} else {
    		// No event has occurred.
    		type = "None";
    	}
    };
    
    
    /**
     * Given a event has occurred this method is used to determine what type of event it is.
     * The level of difficulties are 2 (HARD) and 5 (EASY). This means that the pirateBound is either 50% or 20%.
     * 
     * @param diffculty		A value used to determine the likelihood of a pirate event.
     */
    private void determineRandomEvent(int diffculty) {
    	int pirateBound = (int) 100/diffculty;
    	// The other bounds are determined by taking the remaining values and diving by the 2 remaining events and adding the pirateBound.
    	int weatherBound = ((int) (100 - pirateBound) / 2) + pirateBound;
    	int number = getRandomNumber(100);
    	
    	// Uses the constant event types defined in the SetUp class.
    	if (number < pirateBound) {
    		type = SetUp.PIRATES;
    	} else if (number < (weatherBound)) {
    		type = SetUp.WEATHER;
    	} else {
    		type = SetUp.SAILORS;
    	}
    }
    
    
    /**
     * A static method used to find a random integer value within a bound, 0 - bound.
     * 
     * @param bound			The upper bound of the random number.
     * 
     * @return number		The random number generated.
     */
    public static int getRandomNumber(int bound) {
    	return ThreadLocalRandom.current().nextInt(bound);
    }
    
    
    /**
     * The method used to determine the level of damage to the ship during a weather event.
     * 
     * @return damage		The level of damage to the ship.
     */
    public int eventWeather() {
    	if (type != SetUp.WEATHER) {
    		// Wrong event type so no damage.
    		return 0;
    	}
    	int damage = getRandomNumber(50);
    	outcome = "Bad weather has stuck your ship! It has taken " + damage + " points of damage. It will have to be repaired before you can sail again.";
    	return damage;
    }
    
    
    /**
     * The method used to determine the amount of money received from saving sailors during a sailor event.
     * 
     * @return wealth		The amount of money received.
     */
    public int eventSailors() {
    	if (type != SetUp.SAILORS) {
    		// Wrong event type so no extra wealth.
    		return 0;
    	}
    	int wealth = getRandomNumber(500);
    	outcome = "You spot stuck on a small remote island a group of stranded sailors. You offer them a ship back to the docks and in exchange they give you a small purse of " + wealth + " coins.";
    	return wealth;
    }
    
    
    /**
     * A method used to find if a player won the dice roll. The pirates roll a ten sided dice while the player rolls a 5 sided dice.
     * If the players number beats the pirates they win. The number of cannons they have affects their roll.
     * 
     * @return result		A boolean value thats true if the player wins the roll.
     */
    private boolean rollResultsPirates() {
    	double roll = getRandomNumber(5) + effect;
    	int level = getRandomNumber(10);
    	if (roll > level) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    /**
     * This method allows the player to roll the dice and keeps track of the current games won. 
     * This method resets the value of the winner if the opponent wins.
     */
    public void rollDice() {
    	if (rollResultsPirates()) {
    		outcome = "You Win";
    		if (won < 1) {
    			won = 1;
    		} else {
    			won += 1;
    		}
    	} else {
    		outcome = "You Lose";
       		if (won >= 0) {
    			won = -1;
    		} else {
    			won -= 1;
    		}
    	}
    }
    
    
    /**
     * The method used to setup the pirate event they player need to win to escape.
     * 
     * @param cannons		The cannon upgrade on the ship.
     */
    public void eventPirates(Upgrade cannons) {
    	if (type != SetUp.PIRATES) {
    		// Wrong event type so nothing happens.
    		return;
    	}
    	outcome = "Some Pirates notice your cargo ship and aim to capture it and its loot. But the pirate captin out of bordom has offered you a game to be free. Roll and a dice and win three in a row. (Your Cannons effect your rolls)";
    	effect = cannons.getCannonEffect();
    }

    
    /**
     * A get method for the type of event this is.
     * 
     * @return type		The type of event.
     */
	public String getType() {
		return type;
	}
	
	
    /**
     * A get method for the outcome of the event.
     * 
     * @return outcome		The event outcome.
     */
	public String getOutCome() {
		return outcome;
	}
	
	
    /**
     * A set method used to update the outcome of the event.
     * 
     * @param newOutcome		The new outcome of the event.
     */
	public void setOutcome(String newOutcome) {
		outcome = newOutcome;
	}
	
	
    /**
     * A get method for the current winnings of the pirate game.
     * 
     * @return won		The current winnings of the pirate game.
     */
	public int getWon() {
		return won;
	}
	
}
