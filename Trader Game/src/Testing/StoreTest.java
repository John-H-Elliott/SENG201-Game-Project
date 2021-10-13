package Testing;
/**
 * Testing for Store.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MainGameLogic.Item;
import MainGameLogic.Store;

class StoreTest {
	private Store testStore;
	private ArrayList<Item> itemsToSell;
	
	@BeforeEach
	void init() {
		testStore = new Store(null, itemsToSell, null);
		itemsToSell.add(new Item("Saffron", "a crimson stigma found within the crocus flowers.", 125, 75));
        itemsToSell.add(new Item("Caviar", "a picked roe of the sturgeon fish.", 100, 75));
        itemsToSell.add(new Item("Oysters", "a sea-water molluscs.", 100, 80));
        itemsToSell.add(new Item("Truffles", "a fungus that grows near the roots of trees.", 120, 70));
        itemsToSell.add(new Item("Coffee Beans", "Seed of the coffea plant.", 80, 80));
	}
	@Test
	public void buyItemTest() {
		try{
			testStore.buyItem(1, null);
			equals(null);
			}
		catch(java.lang.NullPointerException e){
		}
		
	}
	

}
