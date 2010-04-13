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

package ngenes2.ops.mutator.genes.bool;

import ngenes2.ops.mutator.genes.*;
import java.util.Random;

//TODO: tests

/**
 * Mutates a single boolean gene at random using an equal probability. Resulting
 * gene has 50% probability of being true.
 */
public class BooleanRandomMutator implements GeneMutator<Boolean> {

    final private Random rng;

    /**
     * Sole constructor.
     * @param rng A random number generator
     */
    public BooleanRandomMutator(Random rng) {
        this.rng = rng;
    }

    public Boolean mutate(Boolean before) {
        return rng.nextDouble() > 0.5 ? Boolean.TRUE : Boolean.FALSE;
    }



}
