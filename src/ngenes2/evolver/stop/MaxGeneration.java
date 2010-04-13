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
import ngenes2.util.Properties;

/**
 * This stop condition allow to define a maximum number of generation. The
 * maximum number of generation is defined by the integer property <b>max_generation</b>.
 * @author falcone
 * @param <G>
 * @param <I>
 */
public class MaxGeneration<G, I extends Individual<G, I>> implements StopCondition<G, I> {

    private final int maxGeneration;

    /**
     * Sole constructor.
     * @param prop A properties object containing the integer property <b>max_generation</b>.
     */
    public MaxGeneration(Properties prop) {
        maxGeneration = prop.getInt("max_generation");
    }

    public boolean shouldStop(int generation, Population<G, I> population) {
        return generation >= maxGeneration;
    }

    public StopCondition<G, I> or(StopCondition<G, I> that) {
        return new OrCondition<G, I>(this, that);
    }

    public StopCondition<G, I> and(StopCondition<G, I> that) {
        return new AndCondition<G,I>(this, that);
    }
}
