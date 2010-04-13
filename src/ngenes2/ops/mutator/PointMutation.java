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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.ops.mutator.genes.GeneMutator;


/**
 * This {@link ChromosomeMutator} mutates a single gene in a chromosome.
 * The gene is chosen at random using a continuous probability distribution.
 * The actual mutation of the gene is delegated to a {@link GeneMutator}.
 * @author falcone
 * @param <G> Gene type.
 */
public class PointMutation<G> implements ChromosomeMutator<G> {

    private final Random rng;
    private final GeneMutator<G> geneMutator;

    /**
     * Sole constructor.
     * @param rng A random number generator
     * @param geneMutator A gene mutator
     */
    public PointMutation(Random rng, GeneMutator<G> geneMutator ) {
        this.rng = rng;
        this.geneMutator = geneMutator;
    }

    public List<G> mutate(List<G> chromosome) {
        final int position = rng.nextInt( chromosome.size() );
        final List<G> newChromosome = new ArrayList<G>( chromosome );
        final G oldValue = chromosome.get(position);
        final G newValue = geneMutator.mutate(oldValue);
        newChromosome.set(position, newValue);
        return newChromosome;
    }

}
