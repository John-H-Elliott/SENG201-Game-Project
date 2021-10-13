package Testing;
/**
 * Testing for RandomEvents.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import MainGameLogic.RandomEvents;

class RandomEventsTest {
	private RandomEvents randomTest;
	
	@Test
	public void TestEventDifficulty() {
		randomTest = new RandomEvents(100);
	}
	
}
