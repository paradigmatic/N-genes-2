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
import ngenes2.population.Stats;
import ngenes2.util.Properties;

/**
 * This stop condition terminates evolution when the fitness goes below an ideal
 * threshold. This threshold is defined through the double property <b>fitness_target</b>.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class FitnessTarget<G, I extends Individual<G, I>> implements StopCondition<G, I> {

    private final double target;

    /**
     * Sole constructor.
     * @param props a properties instance containing the double property <b>fitness_target</b>.
     */
    public FitnessTarget(Properties props) {
        target = props.getDouble("fitness_target");
    }

    public boolean shouldStop(int generation, Population<G, I> population) {
        Stats<I> stats = population.stats();
        return stats.best().fitness() <= target;
    }

    public StopCondition<G, I> or(StopCondition<G, I> that) {
        return new OrCondition<G, I>(this, that);
    }

    public StopCondition<G, I> and(StopCondition<G, I> that) {
        return new AndCondition<G,I>(this, that);
    }
}
