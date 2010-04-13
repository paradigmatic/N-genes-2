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
 * <b>min_gene_value</b> and <b>max_gene_value</b>.
 */
public class PlusOrMinus implements GeneMutator<Integer> {

  private final Random rng;
  private final int min;
  private final int max;

  /**
   * Sole constructor.
   * @param rng A random number generator
   * @param props A properties instance which could hold the optional integer
   * properties  <b>min_gene_value</b> and <b>max_gene_value</b>.
   */
  public PlusOrMinus(Random rng, Properties props) {
    this.rng = rng;
    min = findBoundary(props, "min_gene_value", 0);
    max = findBoundary(props, "max_gene_value", Integer.MAX_VALUE);
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
