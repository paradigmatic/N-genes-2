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

package ngenes2.ops.selector;

import java.util.Iterator;
import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * Defines a selection operator, able to select one or many individuals
 * from a population. This selection can be deterministic or stochastic.
 * @param <I> Individual type
 */
public interface Selector<I extends Individual<?,I>> {

    /**
     * Select one individual from the population.
     * @param pop Population
     * @return Selected individual
     */
    public I select( Population<?,I> pop );

    /**
     * Select several individual from a population. The selection is backed by
     * an iterator, to allow the selection to operate lazily.
     * @param number The number of operators to select.
     * @param pop Population
     * @return Selected individuals
     */
    public Iterator<I> select( int number, Population<?,I> pop );
}
