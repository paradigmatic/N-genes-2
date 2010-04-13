package ngenes2.individual.generator.integer;

import java.util.List;
import java.util.Random;
import ngenes2.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RandomIntegerGeneratorTest {
  
  private final int size = 100;

  @Test
  public void testGenerate() {

    Random rng = mock(Random.class);
    when( rng.nextInt(anyInt()) ).thenReturn(3);
    Properties props = mock(Properties.class);
    when( props.getInt("chromosome_size")).thenReturn(size);
    RandomIntegerGenerator gen = new RandomIntegerGenerator(rng, props);
    List<Integer> lst = gen.generate();
    assertEquals(size, lst.size());
    verify( rng, times(size) ).nextInt(anyInt());
  }

  @Test
  public void testGenerateConstrained() {
    Random rng = mock(Random.class);
    final int min = -3;
    final int max = 100;
    when( rng.nextInt(max-min) ).thenReturn((min+max)/2);
    Properties props = mock(Properties.class);
    when( props.getInt("chromosome_size")).thenReturn(size);
    when( props.getInt("min_gene_value")).thenReturn(min);
    when( props.getInt("max_gene_value")).thenReturn(max);
    when( props.contains(anyString())).thenReturn(Boolean.TRUE);
    RandomIntegerGenerator gen = new RandomIntegerGenerator(rng, props);
    List<Integer> lst = gen.generate();
    assertEquals(size, lst.size());
    verify( rng, times(size) ).nextInt(max-min);
  }

}