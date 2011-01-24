package ngenes2.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

public class PropertiesTest {

    private Properties prop;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test(expected = NoSuchElementException.class)
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

    @Test(expected = NoSuchElementException.class)
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

    @Test(expected = NoSuchElementException.class)
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

    @Test(expected = NoSuchElementException.class)
    public void testStringsException() {
        prop.getString("FOO");
    }

    @Test
    public void parseInt() {
        final String key = "machin";
        final int value = 12;
        Properties props = new Properties().put(key, String.valueOf(value));
        assertEquals(value, props.getInt(key));
    }

    @Test(expected = NumberFormatException.class)
    public void parseBogusInt() {
        final String key = "machin";
        final double value = 12.145;
        Properties props = new Properties().put(key, value);
        props.getInt(key);
    }

    @Test
    public void parseDouble() {
        final String key = "machin";
        final double value = 12.34;
        Properties props = new Properties().put(key, String.valueOf(value));
        assertEquals(value, props.getDouble(key), 1e-18);
    }

    @Test(expected = NumberFormatException.class)
    public void parseBogusDouble() {
        final String key = "machin";
        final String value = "1b-3";
        Properties props = new Properties().put(key, value);
        props.getInt(key);
    }

    @Test
    public void parseBoolean() {
        final String key = "machin";
        final boolean value = false;
        Properties props = new Properties().put(key, String.valueOf(value));
        assertEquals(value, props.getBoolean(key));
    }

    @Test(expected = NumberFormatException.class)
    public void parseBogusBoolean() {
        final String key = "machin";
        final String value = "faaaalse";
        Properties props = new Properties().put(key, value);
        assertEquals(value, props.getBoolean(key));
    }

    @Test
    public void parseString() {
        final String key = "machin";
        final String value = "truc";
        Properties props = new Properties().put(key, value);
        assertEquals(value, props.getString(key));
    }

    @Test
    public void putAllFromProperties() {
        Properties p1 = new Properties();
        p1.put("A", "a").put("B", "b").put("C", "c");
        Properties p2 = new Properties();
        p2.put("C", "c2").put("D", "d");
        Properties p3 = p1.putAll(p2);
        assertTrue(p3 == p1);
        assertEquals("c2", p1.getString("C"));
        assertEquals("d", p1.getString("D"));
        assertEquals("c2", p2.getString("C"));
        assertFalse(p2.contains("B"));
    }

    @Test
    public void putAllFromJProperties() {
        Properties p1 = new Properties();
        p1.put("A", "a").put("B", "b").put("C", "c");
        java.util.Properties p2 = new java.util.Properties();
        p2.put("C", "c2");
        p2.put("D", "d");
        Properties p3 = p1.putAll(p2);
        assertTrue(p3 == p1);
        assertEquals("c2", p1.getString("C"));
        assertEquals("d", p1.getString("D"));
        assertEquals("c2", p2.get("C"));
        assertFalse(p2.contains("B"));
    }

    @Test
    public void keySet() {
        Properties p = new Properties();
        p.put("A", "a").put("B", "b").put("C", "c");
        Set<String> keys = p.keySet();
        assertEquals(3, keys.size());
        assertTrue(keys.contains("A"));
        assertTrue(keys.contains("B"));
        assertTrue(keys.contains("C"));
    }

    @Test
    public void propertiesFromFile() throws IOException {
        String filename = "test.props";
        File propFile = folder.newFile(filename);
        PrintWriter out = new PrintWriter(propFile);
        out.println("A = true");
        out.println("B = 12");
        out.println("C = 0.123");
        out.println("D = dada");
        out.close();
        Properties p = Properties.load( propFile.getAbsolutePath() );
        assertEquals(true, p.getBoolean("A"));
        assertEquals(12, p.getInt("B"));
        assertEquals(0.123, p.getDouble("C"), 0.0);
        assertEquals("dada", p.getString("D"));
    }
}
