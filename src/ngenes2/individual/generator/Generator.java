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

package ngenes2.individual.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import ngenes2.fitness.Fitness;
import ngenes2.individual.Individual;
import ngenes2.individual.IndividualFactory;

/**
 * Indidvidual generator. This generic class creates individuals using a {@link Fitness} function,
 * an {@link IndividualFactory} and a {@link ChromosomeGenerator}.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class Generator<G,I extends Individual<G,I>> {

    private final IndividualFactory<G,I> factory;
    private final Fitness<G> fitFunc;
    private final ChromosomeGenerator<G> chromeGen;

    /**
     * Sole constructor.
     * @param factory An indivdidual factory
     * @param fitFunc A fitness function
     * @param chromeGen A chromosome generator
     */
    public Generator(IndividualFactory<G, I> factory, Fitness<G> fitFunc, ChromosomeGenerator<G> chromeGen) {
        this.factory = factory;
        this.fitFunc = fitFunc;
        this.chromeGen = chromeGen;
    }

    /**
     * Generates a single individual.
     * @return A new individual
     */
    public I generate() {
        return factory.makeIndividual(fitFunc, chromeGen.generate() );
    }

    /**
     * Generates an arbitrary number of individual. The method returns an iterator
     * that will lazily instanciate the desired number of individuals.
     * @param number The number of individuals to be generated.
     * @return An iterator of new individuals
     */
    public Iterator<I> generate( int number ) {
        return new FreshIndividuals<G,I>( this, number );
    }

    private static class FreshIndividuals<G,I extends Individual<G,I>> implements Iterator<I> {

        private final Generator<G,I> generator;
        private final int size;
        private int count = 0;

        private FreshIndividuals(Generator<G, I> generator, int size) {
            this.generator = generator;
            this.size = size;
        }

        public boolean hasNext() {
            return count < size;
        }

        public I next() {
            if( hasNext() ) {
                count++;
                return generator.generate();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }

}
