package ngenes2.individual.generator.bool;


import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RandomBooleanGeneratorTest {



    @Test
    public void testGenerate() {
        final int n = 13;
        Random rng = mock(Random.class);
        RandomBooleanGenerator gen = new RandomBooleanGenerator(rng,n);
        List<Boolean> lst = gen.generate();
        assertEquals(n, lst.size());
        verify( rng, times(n) ).nextBoolean();
    }

}