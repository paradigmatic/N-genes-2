package ngenes2.workflow;


import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ClassicWorkflowTest {


    @Test
    public void testEvolve() {
        final int numGen = 1;
        final Crossover co = mock( Crossover.class );
        final Selector sel = mock( Selector.class );
        final Mutator mut = mock( Mutator.class );
        final Workflow flow = new ClassicWorkflow(numGen, sel, co, mut);
        final Population pop = mock(Population.class);
        flow.evolve(pop);
    }

}