package ngenes2.ops.selector;

import java.util.Iterator;
import java.util.Random;
import ngenes2.individual.Individual;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import static org.mockito.Mockito.*;

public class KTournamentTest {

    private Individual ind1;
    private Individual ind2;
    private Random rng;
    private Population pop;
    private Properties props;
    private KTournament tournament;

    @Before
    public void setup() {
        ind1 = mock(Individual.class);
        when(ind1.fitness()).thenReturn(12.0);
        ind2 = mock(Individual.class);
        when(ind2.fitness()).thenReturn(24.0);
        rng = mock(Random.class);
        when(rng.nextInt(2)).thenReturn(0).thenReturn(1);
        pop = mock(Population.class);
        when(pop.size()).thenReturn(2);
        when(pop.get(0)).thenReturn(ind1);
        when(pop.get(1)).thenReturn(ind2);
        props = new Properties().put("tournament_size", 2);
        tournament = new KTournament(rng, props);
    }

    @Test
    public void testSelect() {
        Individual winner = tournament.select(pop);
        assertSame(ind1, winner);
    }

    @Test
    public void testMultiSelection() {
        final int number = 100;
        Iterator it = tournament.select(number, pop);
        for (int i = 0; i < number; i++) {
            assertTrue(it.hasNext());
            it.next();
        }
        assertFalse(it.hasNext());
    }
}
