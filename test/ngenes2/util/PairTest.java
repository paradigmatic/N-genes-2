package ngenes2.util;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class PairTest {

    private Pair<Integer,String> p;

    @Before
    public void setup() {
        p = Pair.make(2, "blah");
    }

    @Test
    public void testGetters() {
        assertEquals( new Integer(2), p.first() );
        assertEquals( "blah", p.second());
    }

    @Test
    public void testEquals() {
        Pair<Integer,String> p2 = Pair.make(2, "blah");
        Pair<Integer,String> p3 = Pair.make(3, "blah");
        assertTrue(p.equals(p));
        assertTrue(p.equals(p2));
        assertTrue(p2.equals(p));
        assertFalse(p.equals(p3));
        assertFalse(p2.equals(p3));
        assertFalse( p.equals( new Object()) );
    }

    @Test
    public void testHashCode() {
        Pair<Integer,String> p2 = Pair.make(2, "blah");
        assertEquals(p.hashCode(), p2.hashCode());
    }

    @Test
    public void testToList() {
        List<?> lst = p.toList();
        assertEquals(2, lst.size());
        assertEquals(new Integer(2), lst.get(0));
        assertEquals("blah", lst.get(1));
    }

    @Test(expected=NullPointerException.class)
    public void testNullFirst() {
        Pair<?,?> p = Pair.make(null, new Object());
    }

    @Test(expected=NullPointerException.class)
    public void testNullSecond() {
        Pair<?,?> p = Pair.make(new Object(), null);
    }


}