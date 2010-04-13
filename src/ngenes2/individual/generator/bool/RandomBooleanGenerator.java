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
