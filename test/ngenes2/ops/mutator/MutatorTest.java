package ngenes2.ops.mutator;


import ngenes2.individual.ChromosomeMutable;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MutatorTest {



    @Test
    public void testMutate() {
        ChromosomeMutable<Integer,? extends ChromosomeMutable<Integer,?>> mutable =
                mock(ChromosomeMutable.class);
        ChromosomeMutator<Integer> chromMutator = mock( ChromosomeMutator.class );
        Mutator mutator = new Mutator(chromMutator);
        mutator.mutate(mutable);
        verify(mutable).mutate(chromMutator);
    }

}