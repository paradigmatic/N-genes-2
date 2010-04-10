package ngenes2.ops.mutator.genes.integer;


import java.util.Random;
import ngenes2.util.Properties;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlusOrMinusTest {

  private Random rng;
  private Properties props;

  private PlusOrMinus mutator;

  @Before
  public void setup() {
    rng = mock(Random.class);
    when(rng.nextBoolean() ).thenReturn(false).thenReturn(true);
    props = mock(Properties.class);
    when( props.contains(anyString())).thenReturn(false);
    mutator = new PlusOrMinus(rng, props);
  }

  @Test
  public void testMutate() {
    assertEquals(new Integer(3), mutator.mutate(4));
    assertEquals(new Integer(5), mutator.mutate(4));
  }

  @Test
  public void testMutateLowerBound() {
    assertEquals(new Integer(1), mutator.mutate(0));
    assertEquals(new Integer(1), mutator.mutate(0));
  }

  @Test
  public void testMutateUpperBound() {
    final int max = Integer.MAX_VALUE;
    assertEquals(new Integer(Integer.MAX_VALUE-1), mutator.mutate(Integer.MAX_VALUE));
    assertEquals(new Integer(Integer.MAX_VALUE-1), mutator.mutate(Integer.MAX_VALUE));
  }

}