package ngenes2.ops.mutator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.ops.mutator.genes.GeneMutator;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class PointMutationTest {

    @Test
    public void testMutate() {
        Random rng = mock(Random.class);
        when( rng.nextInt( anyInt()) ).thenReturn(1);
        GeneMutator<Integer> geneMut= mock(GeneMutator.class);
        List<Integer> chrome = new ArrayList<Integer>(2);
        chrome.add(12);
        chrome.add(24);
        PointMutation<Integer> mut = new PointMutation<Integer>(rng,geneMut);
        List<Integer> newList = mut.mutate(chrome);
        verify( rng ).nextInt( anyInt() );
        verify(geneMut).mutate( anyInt() );
        assertEquals(new Integer(12), newList.get(0));
        assertFalse( newList.get(1) == new Integer(24) );
    }

}