package ngenes2.ops.mutator;


import java.util.ArrayList;
import ngenes2.individual.Individual;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MutatorTest {



    @Test
    public void testMutate() {
        Individual<Integer,? extends Individual<Integer,?>> individual =
                mock(Individual.class);
        when( individual.chromosome() ).thenReturn(new ArrayList(0));
        ChromosomeMutator<Integer> chromMutator = mock( ChromosomeMutator.class );
        Mutator mutator = new Mutator(chromMutator);
        mutator.mutate(individual);
        verify(individual).makeSibling(new ArrayList(0) );
    }

}