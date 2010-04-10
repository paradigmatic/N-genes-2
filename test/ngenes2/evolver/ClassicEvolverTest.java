package ngenes2.evolver;

import ngenes2.evolver.monitor.GenerationMonitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ngenes2.breeder.Breeder;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
        final List<Individual> selectedLst = new ArrayList<Individual>(popSize);
        for( int i=0; i<popSize; i++ ) {
            selectedLst.add(ind);
        }
        final Selector sel = mock(Selector.class);
        when(sel.select(anyInt(),anyPopulation())).thenAnswer( new Answer<Iterator>() {
            public Iterator answer(InvocationOnMock invocation) throws Throwable {
                return selectedLst.iterator();
            }
        });
        Breeder breeder = mock(Breeder.class);
        when( breeder.childrenNumber() ).thenReturn(2);
        GenerationMonitor monitor = mock( GenerationMonitor.class );
        Properties props = new Properties().put("max_generation",numGen);
        StopCondition stop = new MaxGeneration(props);
        final Evolver flow = new ClassicEvolver( sel, breeder, monitor,stop);
        final Population pop = mock(Population.class);
        when( pop.size() ).thenReturn( popSize );
        when( pop.get( anyInt() ) ).thenReturn( ind );
        flow.evolve(pop);
        verify( sel, times( numGen )).select( anyInt(), anyPopulation() );
        verify( breeder, times(numGen*popSize/2) ).breed(anyPopulation(), anyList());
        verify( monitor, times(numGen) ).newGeneration(anyInt(), anyPopulation());

    }
}
