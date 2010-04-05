
package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;


public class MidBreakCrossoverTest {

   @Test
    public void testMate() {
       final Integer ONE = new Integer(1);
       final Integer TWO = new Integer(2);
       final int size = 4;
       List<Integer> l1 = new ArrayList<Integer>(size);
       List<Integer> l2 = new ArrayList<Integer>(size);
       for( int i = 0; i<size; i++ ) {
           l1.add(ONE);
           l2.add(TWO);
       }
       MidBreakCrossover<Integer> co = new MidBreakCrossover<Integer>();
       List<List<Integer>> result = co.mate(l1, l2);
       assertEquals(2, result.size());
       for( List<Integer> chrome: result ) {
           if( chrome.get(0) == ONE ) {
               assertSame( ONE, chrome.get(1) );
               assertSame( TWO, chrome.get(2) );
               assertSame( TWO, chrome.get(3) );
           } else {
               assertSame( TWO, chrome.get(0) );
               assertSame( TWO, chrome.get(1) );
               assertSame( ONE, chrome.get(2) );
               assertSame( ONE, chrome.get(3) );
           }
       }
       assertSame( result.get(0).get(0), result.get(1).get(3) );
    }

}