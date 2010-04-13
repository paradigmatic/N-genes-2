/*
 * This file is part of n-genes2.
 *
 * n-genes2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * n-genes2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with n-genes2.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010, Paradigmatic <paradigmatic@streum.org>
 *
 */

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
