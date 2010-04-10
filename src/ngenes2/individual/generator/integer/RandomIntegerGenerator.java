package ngenes2.individual.generator.integer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.individual.generator.ChromosomeGenerator;
import ngenes2.util.Properties;

/**
 * Lower boundaryis 0 by default
 * @author falcone
 */
public class RandomIntegerGenerator implements ChromosomeGenerator<Integer> {

  private final Random rng;
  private final int size;
  private final int min;
  private final int max;
  private final int spread;

  public RandomIntegerGenerator(Random rng, Properties prop) {
    this.rng = rng;
    size = prop.getInt("chromosome_size");
    min = findBoundary(prop,"gene-min", 0);
    max = findBoundary(prop,"gene-max",Integer.MAX_VALUE);
    if(  max < min ) {
      throw new IllegalArgumentException("Lower boundary for gene ("+min+
              ") is greater than higher boundary("+max+")");
    }
    spread =max-min;
  }

  public List<Integer> generate() {
    List<Integer> chrome = new ArrayList<Integer>(size);
    for( int i=0; i<size; i++ ) {
      chrome.add(drawGene());
    }
    return chrome;
  }

  private int drawGene() {
    return rng.nextInt(spread)+min;
  }

  private static int findBoundary(Properties prop, String key, int defaultValue) {
    if (prop.contains(key)) {
      return prop.getInt(key);
    } else {
      return defaultValue;
    }
  }
}
