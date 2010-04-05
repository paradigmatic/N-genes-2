package ngenes2.ops.selector;

import java.util.Random;
import ngenes2.individual.Individual;
import ngenes2.population.Population;
import org.junit.Test;
import static org.junit.Assert.*;


import static org.mockito.Mockito.*;


public class KTournamentTest {

 

    @Test
    public void testSelect() {
        Individual<Integer> ind1 = mock(Individual.class);
        when( ind1.fitness() ).thenReturn(12.0);
        Individual<Integer> ind2 = mock(Individual.class);
        when( ind2.fitness() ).thenReturn(24.0);
        Random rng = mock(Random.class);
        when( rng.nextInt( 2 ) ).thenReturn(0).thenReturn(1);
        Population<Integer,Individual<Integer>> pop = mock(Population.class);
        when( pop.size() ).thenReturn(2);
        when( pop.get(0) ).thenReturn(ind1);
        when( pop.get(1) ).thenReturn(ind2);
        KTournament<Integer,Individual<Integer>> tournament =
                   new KTournament<Integer, Individual<Integer>>(rng,2);
        Individual<Integer> winner = tournament.select(pop);
        assertSame( ind1, winner );
    }

}