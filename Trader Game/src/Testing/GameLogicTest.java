package Testing;
/**
 * Testing for GameLogic.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MainGameLogic.GameLogic;

class GameLogicTest {
	private GameLogic logicTest;

	@BeforeEach
	public void init() {
		logicTest = new GameLogic();
	}
	@Test
	public void setNameTest() {
		logicTest.setName("testname");
		assertEquals("testname", logicTest.getName());
	}
	@Test
	public void setDaysTest() {
		logicTest.setDays(30);
		assertEquals(30, logicTest.getDays());
		assertEquals(30, logicTest.getSelectedDays());
	}

	@Test
	public void setShipTest() {
		try {
		logicTest.setShip(4);
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void checkNameTest() {
		try {
			logicTest.checkName("123");
			logicTest.checkName("  ");
			logicTest.checkName("a   b");
		}
		catch(IllegalArgumentException e) {
		}
	}
	@Test
	public void getIslandNameTest() { //checks name is = to index
		logicTest.getIslandName(1);
		equals("Plentiful Retreat");
	
	}
	
	@Test
	public void broughtAtStoreTest() {
		try {
			logicTest.soldAtStore(1);
		}
			catch(IllegalArgumentException e) {
		}
	}
	
	@Test 
	public void getShipTest(){
		logicTest.setShip(1);
		equals(1);
	}
	
	@Test
	public void canRepairTest() {
		int wealth = logicTest.getWealth();
		equals(true);
	}
	@Test
	public void getIslandInfo() {
		logicTest.getIslandInfo();
		equals(toString());
	}
	
	@Test
	public void buyItemsTest() {
	}
	
	@Test
	public void CanSailTest() {
		logicTest.canSail(3,5);
		equals(false);
	}
	/**
	 * Random events testing.
	 */
	//random event private
	//TODO 
	/**
	 * Upgrade testing.
	 */
	@Test
	public void sellRemainingCargoTest() {
		logicTest.sellRemainingCargo();
		logicTest.getCargoItems();
		equals(null);
	}

	
	
	
	
	
	
	
	
	
}