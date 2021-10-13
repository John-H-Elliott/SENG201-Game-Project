package Testing;
/**
 * Testing for Route.
 * 
 * @author Ryan Boyd
 * @version 1.0, 26 May 2021.
 */
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MainGameLogic.Route;

class RouteTest {
private Route routeTest;

	@BeforeEach
	void init() {
		routeTest = new Route(null, null, 0);
	}

	@Test
	public void riskTest() {
	try {
		routeTest.getRisk();
	}
	catch(NullPointerException e) {
	}
	}
}
