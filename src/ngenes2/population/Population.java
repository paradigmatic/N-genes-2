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

/**
 * A population of individuals. The population is composed of two groups of individual
 * the current generation and the next generation. Individual are read from the current
 * generation using the get() method. They can only be added to the next generation using
 * the addToNextGeneration() method. The next generation becomes the current generation
 * after a call to the nextGeneration() method.<br/>
 * Populations are {@link Iterable} and thus can be traversed easily using iterators.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface Population<G, I extends Individual<G, I>> extends Iterable<I> {

  /**
   * Get the current generation size.
   * @return the current generation size
   */
  public int size();

  /**
   * Get the ith indivdiual from the current generation.
   * @param i
   * @return The ith individual
   */
  public I get(int i);

  /**
   * Adds a new individual to the generation.
   * @param newIndividual
   */
  public void addToNextGeneration(I newIndividual);

  /**
   * Commits the nextGeneration. After a call to this method, the current generation
   * is discarded. The next generation becomes the current generation, and a new
   * next generation is instanciated (empty).
   */
  public void nextGeneration();

  /**
   * Get the {@link Stats} object associated with the population
   * @return Stats for the current population
   */
  public Stats<I> stats();
}
