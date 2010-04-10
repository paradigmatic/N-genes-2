
package ngenes2.breeder;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.population.Population;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class ClassicalBreederTest {

    @Test
    public void testBreed() {
        final Individual ind = mock(Individual.class);
        final List<Individual> lst = new ArrayList(2);
        lst.add(ind);
        lst.add(ind);
        final Crossover co = mock(Crossover.class);
        when(co.mate(any(Individual.class), any(Individual.class))).thenReturn(lst);
        final Mutator mut = mock(Mutator.class);
        when( mut.mutate( any(Individual.class)) ).thenReturn( ind );
        final Breeder breeder = new ClassicalBreeder(co, mut);
        final Population pop = mock(Population.class);
        breeder.breed(pop, lst);
        verify( co, times( 1 )).mate(any(Individual.class), any(Individual.class));
        verify( mut, times( 2 )).mutate( any(Individual.class) );
        verify( pop, times(2)).addToNextGeneration( any(Individual.class));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNotEnoughParents() {
        final Individual ind = mock(Individual.class);
        final Breeder breeder = new ClassicalBreeder(null, null);
        breeder.breed(null, new ArrayList());
    }

}