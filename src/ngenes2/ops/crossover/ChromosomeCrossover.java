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

import java.util.List;

/**
 * Defines how to cross over two chromosomes. Instances of this interface
 * are used by {@link Crossover} to cross over individuals.
 * @param <G> Gene type
 */
public interface ChromosomeCrossover<G> {
    /**
     * Cross-over two chromosomes as list of genes. The resulting list of chromosomes should
     * have the same size than the value returned by the childrenNumber() method.
     * @param chrome1 Chromosome 1
     * @param chrome2 Chromosome 2
     * @return A list of resulting children.
     */
    public List<List<G>> mate( List<G> chrome1, List<G> chrome2 );

    /**
     * Get the number of children produced by the crossover.
     * @return Number of children
     */
    public int childrenNumber();
}
