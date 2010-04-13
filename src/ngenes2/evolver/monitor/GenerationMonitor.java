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

package ngenes2.evolver.monitor;

import ngenes2.individual.Individual;
import ngenes2.population.Population;


/**
 * The generation monitor is ususally called by an {@link ngenes2.evolver.Evolver} at the end of
 * each iteration. It is usually used for logging, check pointing or performing
 * additional tasks on the population.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface GenerationMonitor<G,I extends Individual<G,I>> {
  /**
   * Perform an action which is triggered after each generation.
   * @param generationNumber The generation number.
   * @param pop The population to monitor.
   */
    public void newGeneration( int generationNumber, Population<G,I> pop);
}
