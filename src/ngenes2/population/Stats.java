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
 * Statistics over a population
 * @param <I> Individual type
 */
public interface Stats<I extends Individual<?,I>> {

    /**
     * Get the individual with the best fitness in a population.
     * @return Best individual
     */
    public I best();

    /**
     * Get the individual with the worst fitness in a population
     * @return Worst individual
     */
    public I worst();


}
