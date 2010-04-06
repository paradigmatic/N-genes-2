package ngenes2.ops.selector;

import java.util.Random;
import ngenes2.individual.Individual;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;


import static org.mockito.Mockito.*;


public class KTournamentTest {

 

    @Test
    public  void  testSelect() {
        Individual ind1 = mock(Individual.class);
        when( ind1.fitness() ).thenReturn(12.0);
        Individual ind2 = mock(Individual.class);
        when( ind2.fitness() ).thenReturn(24.0);
        Random rng = mock(Random.class);
        when( rng.nextInt( 2 ) ).thenReturn(0).thenReturn(1);
        Population pop = mock(Population.class);
        when( pop.size() ).thenReturn(2);
        when( pop.get(0) ).thenReturn(ind1);
        when( pop.get(1) ).thenReturn(ind2);
        Properties props = new Properties().put("tournament_size", 2);
        KTournament tournament =
                   new KTournament(rng,props);
        Individual winner = tournament.select(pop);
        assertSame( ind1, winner );
    }

}