package ngenes2.individual.generator.integer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.individual.generator.ChromosomeGenerator;
import ngenes2.util.Properties;

/**
 * Generate random chromosomes of integers. The integer are drawn at random
 * using a continuous probability distribution. By default, it draws number
 * between 0 and {@link Integer}.MAX_VALUE. However both boundaries can be
 * cofigured using the optional integer properties <b>gene-min</b> and
 * <b>gene-max</b>.
 */
public class RandomIntegerGenerator implements ChromosomeGenerator<Integer> {

  private final Random rng;
  private final int size;
  private final int min;
  private final int max;
  private final int spread;

  /**
   * Sole constructor.
   * @param rng A random number generator.
   * @param prop A properties instance which could hold the optional integer
   * properties <b>gene-min</b> and
   * <b>gene-max</b>.
   */
  public RandomIntegerGenerator(Random rng, Properties prop) {
    this.rng = rng;
    size = prop.getInt("chromosome_size");
    min = findBoundary(prop, "gene-min", 0);
    max = findBoundary(prop, "gene-max", Integer.MAX_VALUE);
    if (max < min) {
      throw new IllegalArgumentException("Lower boundary for gene (" + min +
              ") is greater than higher boundary(" + max + ")");
    }
    spread = max - min;
  }

  public List<Integer> generate() {
    List<Integer> chrome = new ArrayList<Integer>(size);
    for (int i = 0; i < size; i++) {
      chrome.add(drawGene());
    }
    return chrome;
  }

  private int drawGene() {
    return rng.nextInt(spread) + min;
  }

  private static int findBoundary(Properties prop, String key, int defaultValue) {
    if (prop.contains(key)) {
      return prop.getInt(key);
    } else {
      return defaultValue;
    }
  }
}
