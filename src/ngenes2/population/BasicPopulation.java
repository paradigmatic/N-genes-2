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

package ngenes2.population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.individual.generator.Generator;

/**
 * Basic implementation of {@link Population} interface. Stats are cached
 * to allow fast computations. This class is not thread safe.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class BasicPopulation<G, I extends Individual<G, I>> implements Population<G, I> {

    private List<I> current;
    private List<I> next;
    private boolean sorted = false;

    /**
     * Builds a population using an {@link Iterable} instance such as a collection.
     * @param initialIndividuals Initial generation
     */
    public BasicPopulation(Iterable<I> initialIndividuals) {
        this(initialIndividuals.iterator());
    }

    /**
     * Builds a population using an {@link Iterator} of individuals.
     * @param initialIndividuals Initial generation
     */
    public BasicPopulation(Iterator<I> initialIndividuals) {
        current = new ArrayList<I>();
        while (initialIndividuals.hasNext()) {
            current.add(initialIndividuals.next());
        }
        next = new ArrayList<I>(current.size());
    }

    public int size() {
        return current.size();
    }

    public I get(int i) {
        return current.get(i);
    }

    public void addToNextGeneration(I newIndividual) {
        next.add(newIndividual);
    }

    public void nextGeneration() {
        List<I> tmp = next;
        next = current;
        current = tmp;
        next.clear();
        sorted = false;
    }

    /**
     * BasicPopulation factory
     * @param <G> Gene type
     * @param <I> Population type
     */
    public static class Factory<G, I extends Individual<G, I>>
            implements PopulationFactory<G, I, BasicPopulation<G, I>> {

        public BasicPopulation<G, I> create(Generator<G, I> gen, int popSize) {
            return new BasicPopulation<G, I>(gen.generate(popSize));
        }
    }

    public Iterator<I> iterator() {
        return Collections.unmodifiableList(current).iterator();
    }

    private void sort() {
        if (!sorted) {
            Collections.sort(current, new Comparator<I>() {

                public int compare(I ind0, I ind1) {
                    if (ind0.fitness() == ind1.fitness()) {
                        return 0;
                    }
                    if (ind0.fitness() < ind1.fitness()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
        }
        sorted = true;
    }

    public Stats<I> stats() {
        return new BasicPopulationStats();
    }

    /**
     * Stats for a basic population.
     */
    public class BasicPopulationStats implements Stats<I> {

        public I best() {
            sort();
            return current.get(0);
        }

        public I worst() {
            sort();
            return current.get(current.size() - 1);
        }
    }
}
