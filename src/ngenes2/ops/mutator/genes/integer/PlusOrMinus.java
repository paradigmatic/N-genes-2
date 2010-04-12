
package ngenes2.ops.mutator.genes.integer;

import java.util.Random;
import ngenes2.ops.mutator.genes.GeneMutator;
import ngenes2.util.Properties;

/**
 * Mutates a single integer gene by incrementing or decrementing by 1
 * with an equal probability. This operator takes care of keeping the gene
 * inside boundaries. If a gene is equal to the upper boundary it will be
 * decremented and it is is equal to the lower boundary, it will be incremented.
 * By default, the boundaries are 0 and {@link Integer}.MAX_VALUE. However
 * both boundaries can be cofigured using the optional integer properties
 * <b>gene-min</b> and <b>gene-max</b>.
 */
public class PlusOrMinus implements GeneMutator<Integer> {

  private final Random rng;
  private final int min;
  private final int max;

  /**
   * Sole constructor.
   * @param rng A random number generator
   * @param props A properties instance which could hold the optional integer
   * properties <b>gene-min</b> and <b>gene-max</b>.
   */
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
