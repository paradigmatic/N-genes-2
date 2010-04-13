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

package ngenes2.breeder;
import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.population.Population;

/**
 * The ClassicalBreeder takes two parent, apply a crossover operator and mutate
 * resulting children before adding them to the population.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class ClassicalBreeder<G, I extends Individual<G, I>> implements Breeder<G, I> {

    private final static int PARENT_NUMBER = 2;
    private final int childrenNumber;;
    private final Crossover<G, I> crossover;
    private final Mutator<G, I> mutator;

    /**
     * Create a Classical breeder using a crossover and a mutator.
     * @param crossover The crossover operator
     * @param mutator The mutator operator
     */
    public ClassicalBreeder(Crossover<G, I> crossover, Mutator<G, I> mutator) {
        this.crossover = crossover;
        this.mutator = mutator;
        this.childrenNumber = crossover.childrenNumber();
    }

    public int parentsNumber() {
        return PARENT_NUMBER;
    }

    public int childrenNumber() {
        return childrenNumber;
    }

    public void breed(Population<G, I> pop, List<I> parents) {
        if (parents.size() != PARENT_NUMBER) {
            throw new IllegalArgumentException("Received " + parents.size() + " instead of " + PARENT_NUMBER + ".");
        }
        final List<I> beforeMutation = crossover.mate(parents.get(0), parents.get(1));// </editor-fold>
        for (I child : beforeMutation) {
            pop.addToNextGeneration(mutator.mutate(child));
        }
    }
}
