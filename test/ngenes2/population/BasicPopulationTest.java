package ngenes2.population;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 *
 * @author falcone
 */
public class BasicPopulationTest {

    private List<Individual> initList;
    private Individual ind1;
    private Individual ind2;
    private Population pop;

    @Before
    public void setUp() {
        ind1 = mock(Individual.class);
        ind2 = mock(Individual.class);
        initList = new ArrayList<Individual>(2);
        initList.add(ind1);
        initList.add(ind2);
        pop = new BasicPopulation(initList);
    }

    @Test
    public void testSize() {
        assertEquals(initList.size(), pop.size());
    }

    @Test
    public void testGet() {
        assertSame(ind1, pop.get(0));
        assertSame(ind2, pop.get(1));
    }

    @Test
    public void testNextGeneration() {
        pop.nextGeneration();
        assertEquals(0, pop.size());
    }

    @Test
    public void testAddToNextGeneration() {
        final int n = 5;
        for (int i = 0; i < n; i++) {
            pop.addToNextGeneration(mock(Individual.class));
        }
        pop.nextGeneration();
        assertEquals(5, pop.size());
    }
}
