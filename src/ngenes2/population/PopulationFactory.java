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

package ngenes2.population;

import ngenes2.individual.Individual;
import ngenes2.individual.generator.Generator;

/**
 * Creates a new non-empty population using a indivdiual {@link Generator}.
 * @param <G> Gene type
 * @param <I> Individual type
 * @param <P> Population type
 */
public interface PopulationFactory<G, I extends Individual<G, I>, P extends Population<G, I>> {

  /**
   * Create a new population of a given size. The population is filled with
   * indivdiual created by an individual generator.
   * @param gen Individual generator
   * @param popSize Desired population size
   * @return A new population
   */
  public P create(Generator<G, I> gen, int popSize);

}
