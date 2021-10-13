package Testing;

/**
 * Testing for Island.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MainGameLogic.Island;

class IslandTest {
	private Island islTest;

	@Test
	public void init() {
		islTest = new Island("Plentiful Retreat",null);
	}
	

}
