package ngenes2.evolver;

import ngenes2.evolver.monitor.GenerationMonitor;
import java.util.ArrayList;
import java.util.List;
import ngenes2.evolver.stop.MaxGeneration;
import ngenes2.evolver.stop.StopCondition;
import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;
import ngenes2.util.Properties;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.Mockito.*;

public class ClassicEvolverTest {

    private static Individual anyIndividual() {
        return argThat(new ArgumentMatcher<Individual>() {
            @Override
            public boolean matches(Object argument) {
                return argument instanceof Individual;
            }
        });
    }

    private static Population anyPopulation() {
        return argThat(new ArgumentMatcher<Population>() {
            @Override
            public boolean matches(Object argument) {
                return argument instanceof Population;
            }
        });
    }

    @Test
    public void testEvolve() {
        final Individual ind = mock(Individual.class);
        final int numGen = 3;
        final int popSize = 100;
        final List<Individual> lst = new ArrayList(2);
        lst.add(ind);
        lst.add(ind);
        final Crossover co = mock(Crossover.class);
        when(co.mate(anyIndividual(), anyIndividual())).thenReturn(lst);
        final Selector sel = mock(Selector.class);
        when(sel.select(anyPopulation())).thenReturn( ind );
        final Mutator mut = mock(Mutator.class);
        when( mut.mutate( anyIndividual() ) ).thenReturn( ind );
        GenerationMonitor monitor = mock( GenerationMonitor.class );
        Properties props = new Properties().put("max_generation",numGen);
        StopCondition stop = new MaxGeneration(props);
        final Evolver flow = new ClassicEvolver( sel, co, mut, monitor,stop);
        final Population pop = mock(Population.class);
        when( pop.size() ).thenReturn( popSize );
        when( pop.get( anyInt() ) ).thenReturn( ind );
        flow.evolve(pop);
        verify( co, times( popSize / 2 * numGen )).mate(anyIndividual(), anyIndividual());
        verify( sel, times( popSize*numGen )).select( anyPopulation() );
        verify( mut, times( popSize*numGen )).mutate( anyIndividual() );
        verify( monitor, times(numGen) ).newGeneration(anyInt(), anyPopulation());
    }
}
