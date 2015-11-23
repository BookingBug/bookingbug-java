package bookingbugAPI.api;

import static org.junit.Assert.*;

import org.junit.Test;
import main.java.bookingbugAPI.api.PublicURLS;

public class SessionStatusTest {

	@Test
	public void testSessionStatus(){
		assertEquals("A test for sessionStatus", "Testing", PublicURLS.sessionStatus());
	}
}
