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

package ngenes2.individual;

import java.util.List;
import ngenes2.fitness.Fitness;


/**
 * Factory that can create a new individual using a chromosome and a fitness
 * function. Individual factories should be implemented as nested static class
 * in Individual implementations.
 * @param <G> Gene type
 * @param <I> individual type
 */
public interface IndividualFactory<G,I extends Individual<G,I>> {

    /**
     * Create a new individual.
     * @param fitFunc A fitness function
     * @param chromosome A chromosome as a list of genes.
     * @return A new individual.
     */
    I makeIndividual( Fitness<G> fitFunc, List<G> chromosome );
}
