package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CrossoverTest {

    @Test
    public void testMate() {
        Individual<Integer, ? extends Individual<Integer, ?>> individual =
                mock(Individual.class);
        when(individual.chromosome()).thenReturn(new ArrayList(0));
        final List<Integer> l1 = new ArrayList<Integer>(0);
        final List<Integer> l2 = new ArrayList<Integer>(0);
        final List<List<Integer>> result = new ArrayList<List<Integer>>(2);
        result.add(l1);
        result.add(l2);
        ChromosomeCrossover<Integer> chromeCO = mock(ChromosomeCrossover.class);
        when( chromeCO.mate(anyList(), anyList() ) ).thenReturn(result);
        Crossover co =
                new Crossover(chromeCO);
        co.mate(individual, individual);
        verify(individual, times(2)).makeSibling( anyList() );
        verify(chromeCO).mate(anyList(), anyList());

    }
}
