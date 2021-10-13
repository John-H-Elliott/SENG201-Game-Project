package Testing;
/**
 * Testing for Upgrade.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import MainGameLogic.Upgrade;

class UpgradeTest {
	private Upgrade testUp;
	
	@BeforeEach
	void init() {
		testUp = new Upgrade("Cannons", "Increases your chances of winning a fight.", 500, 30);
	}
	@Test
	public void quantityTest() {
		testUp.getQuantity();
		testUp.addQuantity(5);
		equals(5);
	}
	
	@Test
	public void cargoEffectTest(){
		var uname = testUp.getName();
		testUp.getCargoEffect();
		equals(0);
	}
	@Test
	public void speedEffectTest(){
		var uname = testUp.getName();
		testUp.getSpeedEffect();
		equals(0);
	}
	@Test
	public void CannonEffectTest(){
		var uname = testUp.getName();
		var quantity = testUp.getQuantity();
		testUp.getCannonEffect();
		equals(0.5);
	}
}
