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

package ngenes2.ops.mutator.genes;


/**
 * Define a single gene mutation process. The gene is not modified but a new
 * one is created.
 * @param <G> Gene type
 */
public interface GeneMutator<G> {

    /**
     * Mutates a gene.
     * @param before The gene before mutation (not modified by the process)
     * @return A new gene
     */
    public G mutate( G before );
}
