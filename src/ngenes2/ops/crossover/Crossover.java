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

package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;

/**
 * Cross over two parents individuals two produce children. The actual recombination
 * of chromosome is delegated to a {@link ChromosomeCrossover}.
 * @author falcone
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class Crossover<G, I extends Individual<G, I>> {

    private final ChromosomeCrossover<G> chromosomeCrossover;

    /**
     * Sole constructor. Takes a ChromosomeCrossover instance which will perform
     * the actual chromosome recombination.
     * @param chromosomeCrossover A chromosome crossover instance
     */
    public Crossover(ChromosomeCrossover<G> chromosomeCrossover) {
        this.chromosomeCrossover = chromosomeCrossover;
    }

    /**
     * Mates two individual using the ChromosomeCrossover passed to the constructor.
     * The number of children is equal to the value returned by the childrenNumber()
     * method.
     * @param individual1 First parent
     * @param individual2 Second parent
     * @return A list of children.
     */
    public List<I> mate(I individual1, I individual2) {
        final List<List<G>> chromos =
                chromosomeCrossover.mate(individual1.chromosome(),
                individual2.chromosome());
        final List<I> offsprings = new ArrayList<I>( chromos.size() );
        for( List<G> chrome: chromos ) {
            offsprings.add( individual1.makeSibling(chrome) );
        }
        return offsprings;
    }

    /**
     * Get number of children generated by the crossover.
     * @return Number of children
     */
    public int childrenNumber() {
        return chromosomeCrossover.childrenNumber();
    }
}
