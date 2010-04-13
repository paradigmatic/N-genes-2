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
 * Interface defines GA individuals. To reduce side effects and allow
 * easy parallelization of evolution, Individuals should be immutable.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface Individual<G,I extends Individual<G,I>> {

    /**
     * Evaluate and return the individual fitness value. If the individual is immutable,
     * the fitness value is cached and successive calls to this method are inexpensive.
     * @return The fitness value.
     */
    public double fitness();

    /**
     * Get the fitness function used by the individual.
     * @return The fitness function
     */
    public Fitness<G> fitnessFunction();

    /**
     * Returns the individual size. Ususally it is a short cut to the chromosome size.
     * @return The individual size.
     */
    public int size();

    /**
     * Return the ith gene in the chromosome.
     * @param i gene index
     * @return gene value
     */
    public G get( int i );

    /**
     * Get the individual chromosome as list of genes. The list should be unmodifiable
     * to limit side effects.
     * @return The chromosome
     */
    public List<G> chromosome();


    /**
     * Build and return an individual of the same base class. This method allow
     * individuals to act as factories. Created individual share the same fitness
     * function instance.
     * @param newChromosome The chromosome of the individual to be created
     * @return A new indivdiual of the same base class
     */
    public I makeSibling( List<G> newChromosome );
    

}
