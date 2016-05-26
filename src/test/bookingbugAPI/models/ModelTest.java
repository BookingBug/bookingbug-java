package bookingbugAPI.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;

/**
 * Abstract ModelTest class. Must be inherited by all tests for bb models
 * Has setUp, tearDown and modelInit methods
 */
@Ignore
public abstract class ModelTest {

    @Before
    public abstract void setUp();

    @Test
    public abstract void modelInit() throws ParseException;

    @After
    public abstract void tearDown();
}
