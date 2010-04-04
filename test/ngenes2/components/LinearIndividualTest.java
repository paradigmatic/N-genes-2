
package ngenes2.components;

import java.util.ArrayList;
import ngenes2.fitness.Fitness;
import org.junit.Test;
import static org.junit.Assert.*;


public class LinearIndividualTest {

    public LinearIndividual<Integer> makeIndividual() {
        ArrayList<Integer> lst = new ArrayList<Integer>();
        lst.add(0);
        lst.add(1);
        lst.add(2);
        Fitness<Integer> fit = new Fitness<Integer>() {
            public double compute(Individual<Integer> individual) {
              return 12.0;
            }
        };
        return new LinearIndividual<Integer>(fit,lst);
    }

    @Test
    public void testSet() {
        final LinearIndividual<Integer> ind = makeIndividual();
        final int before = ind.get(1);
        ind.set(1,before+20);
        assertEquals( ind.get(1), new Integer( before+20 ) );
    }

    @Test
    public void testFitness() {
          final LinearIndividual<Integer> ind = makeIndividual();
          assertEquals(12.0, ind.fitness(), 1e-9);
    }

    @Test
    public void testGet() {
        final LinearIndividual<Integer> ind = makeIndividual();
        assertEquals( new Integer(2), ind.get(2));
    }

    @Test
    public void testSize() {
        final LinearIndividual<Integer> ind = makeIndividual();
        assertEquals( 3, ind.size());
    }

}