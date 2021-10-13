package Testing;

/**
 * Testing for Ship.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import MainGameLogic.Ship;

class ShipTest {
	private Ship testShip;
	
	@BeforeEach
	void init() {
		testShip = new Ship("Barquentine", 100, 350, 0.8,null);
	}
	
	@Test
	public void testWages() {
		testShip.getWagesToPay(10);
		equals(250);
	}
	@Test
	public void cargoTest(){
		testShip.getCargoSize();
		equals(350);
	}
	@Test
	public void addItemTest(){
		testShip.addItem(null);
		}
	@Test
	public void wagesTest() {
		testShip.canPayWages(1000, 2);
		equals(true);
	}
	@Test
	public void wagesTest2t() {
		testShip.canPayWages(400, 5);
		equals(false);
	}
	@Test
	public void repairDamageTest() {
		testShip.canRepair(0);
		equals(false);
	}
	@Test
	public void repairDamageTest2() {
		testShip.repairDamage();
		equals(0);
	}
}

