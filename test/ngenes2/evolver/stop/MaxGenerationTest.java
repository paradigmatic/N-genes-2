package ngenes2.evolver.stop;

import ngenes2.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxGenerationTest {

    private final int max = 13;
    private final Properties props = new Properties().put("max_generation", max);
    private MaxGeneration condition;


    @Before
    public void setup() throws Exception {
        condition = new MaxGeneration(props);
    }

    @Test
    public void testBeforeMax() {
        assertEquals( false, condition.shouldStop(max/2, null));
    }

    @Test
    public void testAfterMax() {
        assertEquals( true, condition.shouldStop(max, null));
    }


}