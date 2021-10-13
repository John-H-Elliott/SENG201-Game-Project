package Testing;
/**
 * Testing for Items.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MainGameLogic.GameLogic;
import MainGameLogic.Item;


class ItemTest {
	private Item testItem;
	
	@BeforeEach
	public void init() {
	testItem = new Item("Coffee Beans", "Seed of the coffea plant.", 80, 80);
	}
	
	@Test 
	public void getSoldPriceTest() {
		testItem.getSoldPrice();
		equals(80);
	}
	@Test
	public void getIslandSoldToTest() {
		testItem.getIslandSoldTo();
	}
}
