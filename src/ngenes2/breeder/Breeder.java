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

package ngenes2.breeder;

import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * A Breeder is responsible for producing children individuals from parents
 * individuals. It is usually called by an {@link ngenes2.evolver.Evolver} instance several to
 * build the population next generation. It usually calls crossover and mutation
 * operators.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface Breeder<G, I extends Individual<G,I>> {

    /**
     * Return the number of parents needed by the breeder to produce
     * children.
     * @return The number of parents
     */
    public int parentsNumber();

    /**
     * Return the number of children produced by the breeder.
     * @return The number of children
     */
    public int childrenNumber();

    /**
     * Performs the breeder operation. The resulting children are added to the population.
     * If the number of parents does not match the require parents number, an exception
     * will be thrown.
     * @param pop The population where the children will be added
     * @param parents The parents
     */
    public void breed( Population<G,I> pop, List<I> parents );
}
