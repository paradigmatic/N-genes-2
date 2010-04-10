
package ngenes2.ops.mutator.genes.integer;

import java.util.Random;
import ngenes2.ops.mutator.genes.GeneMutator;
import ngenes2.util.Properties;

/**
 * Lower boundary is 0 by default.
 * @author falcone
 */
public class PlusOrMinus implements GeneMutator<Integer> {

  private final Random rng;
  private final int min;
  private final int max;

  public PlusOrMinus(Random rng, Properties props) {
    this.rng = rng;
    min = findBoundary(props, "gene-min", 0);
    max = findBoundary(props, "gene-max", Integer.MAX_VALUE);
  }



  public Integer mutate(Integer before) {
    if( before <= min ) {
      return before + 1;
    }
    if( before >= max ) {
      return before - 1;
    }
    if( rng.nextBoolean() ) {
      return before + 1;
    } else {
      return before - 1;
    }
  }


 private static int findBoundary(Properties prop, String key, int defaultValue) {
    if (prop.contains(key)) {
      return prop.getInt(key);
    } else {
      return defaultValue;
    }
  }
}
