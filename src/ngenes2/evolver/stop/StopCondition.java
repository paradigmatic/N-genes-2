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

package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * This class represents a stop condition which, when fulfilled, stop the
 * evolution. Stop conditions can be chained using the logical operators <i>or</i> and <i>and</i>
 * to produce richer conditions.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface StopCondition<G, I extends Individual<G, I>> {

  /**
   * This method returns true when the condition is fulfilled.
   * @param generation The generation count. The first generation is 0.
   * @param population The population being evolved.
   * @return True if and only if the evolution should stop.
   */
  public boolean shouldStop(int generation, Population<G, I> population);

  /**
   * Chains the current condition with an other one, using the or logical
   * operator. The resultingcondition returns true if one of the condition are true.
   * There are no warranties than the second condition will be actually executed if the first
   * is true.
   * @param that Another condition.
   * @return A stop condition which will return <pre>(this or that)</pre>.
   */
  public StopCondition<G, I> or(StopCondition<G, I> that);

  /**
   * Chains the current condition with an other one, using the and logical operator. The resulting
   * condition returns true if both conditions are true. There are no
   * warranties than the second condition will be actually executed if the first
   * is false.
   * @param that Another condition.
   * @return A stop condition which will return <pre>(this and that)</pre>.
   */
  public StopCondition<G, I> and(StopCondition<G, I> that);
}
