package ngenes2.population;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class StatsTest<I> {

    private Individual<?,?> ind1;
    private Individual<?,?> ind2;
    private Individual<?,?> ind3;
    private Population pop;
    private Stats stats;

    @Before
    public void setup() {
        ind1 = mock( Individual.class );
        when( ind1.fitness() ).thenReturn(1.0);
        ind2 = mock( Individual.class );
        when( ind2.fitness() ).thenReturn(2.0);
        ind3 = mock( Individual.class );
        when( ind3.fitness() ).thenReturn(3.0);
        List<Individual<?,?>> list = new ArrayList<Individual<?, ?>>(3);
        list.add(ind1);
        list.add(ind2);
        list.add(ind3);
        pop = mock(Population.class);
        when( pop.iterator() ).thenReturn(list.iterator());
        stats = new Stats(pop);

    }

    @Test
    public void testBest() {
        assertSame(ind1, stats.best());
    }

    @Test
    public void testWorst() {
        assertSame(ind3, stats.worst());
    }

}