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

package ngenes2.ops.mutator;

import java.util.List;
import ngenes2.individual.Individual;

/**
 * Mutates an individual using a {@link ChromosomeMutator}. The individual is not
 * modified but a new one is created instead.
 * @param <G>
 * @param <I>
 */
public class Mutator<G,I extends Individual<G,I>> {

    private final ChromosomeMutator<G> chromosomeMutator;

    /**
     * Sole constructor.
     * @param chromosomeMutator A ChromosomeMutator
     */
    public Mutator(ChromosomeMutator<G> chromosomeMutator) {
        this.chromosomeMutator = chromosomeMutator;
    }

    /**
     * Mutates an individual.
     * @param before The individual to mutate (not modified)
     * @return A new individual
     */
    public I mutate( I before ) {
        List<G> newChromosome = chromosomeMutator.mutate( before.chromosome() );
        return before.makeSibling(newChromosome);
    }
}
