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

package ngenes2.fitness;

import java.util.List;

/**
 * Individual fitness. By convention the smallest fitness is the best.
 * @param <G> Gene type.
 */
public interface Fitness<G> {

    /**
     * Computes the fitness corresponding to a chromosome.
     * @param chromosome The chromosome to evaluate
     * @return The fitness
     */
    public double compute(List<G> chromosome );

}
