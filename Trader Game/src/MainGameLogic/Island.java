package MainGameLogic; 
import java.util.ArrayList;


/**
 * This class implements an MainGameLogic.Island that the player can travel to and from.
 * Each MainGameLogic.Island has a Store and Routes that the player can use to travel.
 *
 * @author John Elliott
 * @version 1.1, 16 April 2021.
 */
public class Island {

    /**
     * The coordinates of the MainGameLogic.Island used to find distances.
     */
    private int[] coordinates;

    /**
     * The name of the MainGameLogic.Island.
     */
    private String name;

    /**
     * The Islands Store.
     */
    private Store islandStore;

    /**
     * The list of Routes from the MainGameLogic.Island.
     */
    private ArrayList<Route> routes = new ArrayList<>();


    /**
     * Initializes an MainGameLogic.Island.
     *
     * @param givenName     The name of the MainGameLogic.Island.
     * @param cords         The coordinates of the MainGameLogic.Island.
     */
    public Island(String givenName, int[] cords){
        name = givenName;
        coordinates = cords;
    }


    /**
     * Adds a new Route to the Islands list of Routes.
     *
     * @param newRoute     The new Route being added.
     */
    public void addRoute(Route newRoute){
        routes.add(newRoute);
    }


    /**
     * Sets the Store of the MainGameLogic.Island this is done after the initializes
     * as Store have there own initializes setup.
     *
     * @param newStore     The new Store being added.
     */
    public void setStore(Store newStore){
        islandStore = newStore;
    }


    /**
     * Is a get method for the Islands coordinates.
     *
     * @return coordinates      The Islands coordinates.
     */
    public int[] getCords(){
        return coordinates;
    }


    /**
     * Is a get method for the Islands name.
     *
     * @return name      The Islands name.
     */
    public String getName(){
        return name;
    }

    /**
     * Is a get method for the Islands store.
     *
     * @return store      The Islands store.
     */
    public Store getStore(){ 
    	return islandStore; 
    }

    
    /**
     * Returns a formatted String of all the current Islands Routes.
     *
     * @return RouteString      The Islands Routes as a String.
     */
    public String getAllRoutesString(){
        String RouteString = "";
        int i;
        for(i = 0; i < routes.size(); i++){
            Route currentRoute = routes.get(i);
            RouteString += "("+(i + 1)+") " + currentRoute.toString();
        }
        return RouteString + "\n("+(i +1)+") Quit";
    }


    /**
     * Returns a formatted String of the Islands Routes between the current island and a new island.
     *
     * @param newIsland			The Island the player is traveling to.
     * @return RouteString      The Islands Routes as a String.
     */
    public String getRoutesString(Island newIsland){
        String RouteString = "";
        for(Route currentRoute : routes){
            if (currentRoute.getDestination() == newIsland){
                RouteString += currentRoute.toString();
            }
        }

        return RouteString;
    }

    
    /**
     * Returns a formatted String of a islands information and route to another island.
     *
     * @param currentIsland		The Island the player is traveling to.
     * @return islandInfo      	Information about a given Islands.
     */
    public String toString(Island currentIsland){
        String islandInfo = name+":\n";
        String routeInfo = currentIsland.getRoutesString(this);
        if (routeInfo == ""){
            routeInfo = "Current docked.\n";
        }
        String storeInfo = islandStore.toString();
        return islandInfo + routeInfo +"\n"+ storeInfo;
    }

    
    
    /**
     * A get method for the islands routes.
     * 
     * @return routes		A list of the route from this island.
     */
    public ArrayList<Route> getRoutes() {
        return routes;
    }
    
    
    /**
     * A get method for the islands routes between the current island and a new island.
     * 
     * @param newIsland		The Island the player is traveling to.
     * @return routes		A list of the route from this island.
     */
    public ArrayList<Route> getRoutes(Island newIsland) {
    	ArrayList<Route> newRoutes = new ArrayList<Route>();
        for(Route currentRoute : routes){
            if (currentRoute.getDestination() == newIsland){
            	newRoutes.add(currentRoute);
            }
        }
        return newRoutes;
    }
    
}
