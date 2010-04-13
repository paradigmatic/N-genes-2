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

package ngenes2.builder;

import ngenes2.evolver.Evolver;
import ngenes2.population.Population;

/**
 * Builders allow to easily instanciate GA simulations components. Builders
 * are convenient for trials and experimentation because they take care of all the
 * tedious manual instanciate of components. However they ignore generic parameters
 * and thus are not type-safe. For critical or production code, we strongly advise
 * building all components manually.
 */
public interface Builder {

  /**
   * Builds a population which includes the initial generation.
   * @return A population
   */
  public Population<?,?> population();

  /**
   * Builds an evolver.
   * @return Evolver
   */
  public Evolver<?,?> evolver();

}