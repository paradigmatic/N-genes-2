package ngenes2.util;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertiesTest {

    private Properties prop;

    @Before
    public void setup() {
        prop = new Properties();
    }
    
    @Test
    public void testDoubles() {
        final double FIRST = 1.0;
        final double SECOND = 2.0;
        prop.put("F", FIRST);
        prop.put("S", SECOND);
        assertEquals(FIRST, prop.getDouble("F"), 1e-18);
        assertEquals(SECOND, prop.getDouble("S"), 1e-18);
    }

    @Test(expected=NoSuchElementException.class)
    public void testDoublesException() {
        prop.getDouble("FOO");
    }

    @Test
    public void testIntegers() {
        final int FIRST = 195;
        final int SECOND = 12;
        prop.put("F", FIRST);
        prop.put("S", SECOND);
        assertEquals(FIRST, prop.getInt("F"));
        assertEquals(SECOND, prop.getInt("S"));
    }

    @Test(expected=NoSuchElementException.class)
    public void testIntegersException() {
        prop.getInt("FOO");
    }

    @Test
    public void testBooleans() {
        final boolean FIRST = true;
        final boolean SECOND = false;
        prop.put("F", FIRST);
        prop.put("S", SECOND);
        assertEquals(FIRST, prop.getBoolean("F"));
        assertEquals(SECOND, prop.getBoolean("S"));
    }

    @Test(expected=NoSuchElementException.class)
    public void testBooleansException() {
        prop.getBoolean("FOO");
    }

    @Test
    public void testStrings() {
        final String FIRST = "Machin";
        final String SECOND = "Truc";
        prop.put("F", FIRST);
        prop.put("S", SECOND);
        assertEquals(FIRST, prop.getString("F"));
        assertEquals(SECOND, prop.getString("S"));
    }

    @Test(expected=NoSuchElementException.class)
    public void testStringsException() {
        prop.getString("FOO");
    }
}
