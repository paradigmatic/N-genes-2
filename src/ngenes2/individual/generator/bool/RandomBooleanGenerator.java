package ngenes2.individual.generator.bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.individual.generator.ChromosomeGenerator;
import ngenes2.util.Properties;

/**
 * Generates randomly boolean chromosome of a given size. True and false values
 * appears with the same probability. The size is read from the integer property
 * <b>chromosome_size</b>.
 * @author falcone
 */
public class RandomBooleanGenerator implements ChromosomeGenerator<Boolean> {

  final private Random rng;
  final private int size;

  /**
   * Sole constructor.
   * @param rng A random number generator.
   * @param props A property object holding the integer property
   * <b>chromosome_size</b>
   */
  public RandomBooleanGenerator(Random rng, Properties props) {
    this.rng = rng;
    this.size = props.getInt("chromosome_size");
  }

  private Boolean randomValue() {
    return rng.nextBoolean() ? Boolean.FALSE : Boolean.TRUE;
  }

  public List<Boolean> generate() {
    List<Boolean> chrome = new ArrayList<Boolean>(size);
    for (int i = 0; i < size; i++) {
      chrome.add(randomValue());
    }
    return chrome;
  }
}
