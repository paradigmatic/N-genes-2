package ngenes2.ops.mutator.genes.bool;


import org.junit.Test;
import static org.junit.Assert.*;


public class BooleanFlipperTest {

    @Test
    public void testMutate() {
        final BooleanFlipper bf = new BooleanFlipper();
        assertSame(bf.mutate(Boolean.TRUE), Boolean.FALSE);
        assertSame(bf.mutate(Boolean.FALSE), Boolean.TRUE);
    }

}