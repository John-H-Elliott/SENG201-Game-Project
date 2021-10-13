package MainGameLogic;
import java.lang.Math;


/**
 * This class implements a Route that the player uses when traveling between Islands.
 * Routes store the information needed to determine distances and the players new position.
 *
 * @author John Elliott
 * @version 1.1, 16 April 2021.
 */
public class Route {

    /**
     * The distance between the two Islands.
     */
    private double distance;

    /**
     * The MainGameLogic.Island of origin the player is current on.
     */
    private Island originIsland;

    /**
     * The MainGameLogic.Island the player is currently traveling to.
     */
    private Island destination;

    /**
     * The level of risk the player takes traveling this Route.
     */
    private int risk;


    /**
     * Initializes an Route.
     *
     * @param startIsland     The MainGameLogic.Island the player will be on.
     * @param finalIsland     The MainGameLogic.Island the player will travel to.
     * @param danger          The level of risk that Route contains.
     */
    public Route(Island startIsland, Island finalIsland, int danger){
        originIsland = startIsland;
        destination = finalIsland;
        risk = danger;
        distance = calculateDistance();
    }


    /**
     * Uses the coordinates of the two Islands
     * to find the distance between them and returns it as a double.
     *
     * @return distanceTo       This is the distance between the two Islands.
     */
    private double calculateDistance(){
        int[] finalCords = destination.getCords();
        int[] originCords = originIsland.getCords();
        int distanceBetween = Math.abs(originCords[0] - finalCords[0]);
        int heightBetween = Math.abs(originCords[1] - finalCords[1]);
        double distanceTo = Math.sqrt(Math.pow(distanceBetween, 2) + Math.pow(heightBetween, 2));
        return distanceTo;

    }


    /**
     * Is a get method for the distance
     *
     * @return distance       This is the distance between the two Islands.
     */
    public double getDistance(){
        return distance;
    }


    /**
     * This calculates the duration of the trip based on
     * the speed of the current boat and the distance between the Islands.
     *
     * @param speed           The speed of the current boat.
     * @return duration       This is the number of days needed to travel.
     */
    public int findDuration(double speed){
        int duration = (int)((speed * distance) + risk);
        return duration;
    }


    /**
     * Is a get method for the MainGameLogic.Island destination.
     *
     * @return destination       This is the MainGameLogic.Island being traveled to.
     */
    public Island getDestination(){
        return destination;
    }


    /**
     * Returns a formatted String that list the relevant information about the Route.
     *
     * @return routeString       This is a formatted string with the Route information.
     */
    public String toString(){
        String routeString = "Going to "+destination.getName()+" with a risk of "+risk+" and a distance is "+distance+".\n";
        return routeString;
    }


    /**
     * Is a get method for the risk of the Route. As defined in the SetUp class.
     *
     * @return risk       The level of risk for the current route.
     */
	public int getRisk() {
		return risk;
	}

}
