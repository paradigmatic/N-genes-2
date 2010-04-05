package ngenes2.individual.generator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import ngenes2.fitness.Fitness;
import ngenes2.individual.Individual;
import ngenes2.individual.IndividualFactory;


public class Generator<G,I extends Individual<G,I>> {

    private final IndividualFactory<G,I> factory;
    private final Fitness<G> fitFunc;
    private final ChromosomeGenerator<G> chromeGen;

    public Generator(IndividualFactory<G, I> factory, Fitness<G> fitFunc, ChromosomeGenerator<G> chromeGen) {
        this.factory = factory;
        this.fitFunc = fitFunc;
        this.chromeGen = chromeGen;
    }

    public I generate() {
        return factory.makeIndividual(fitFunc, chromeGen.generate() );
    }

    public Iterator<I> generate( int number ) {
        return new FreshIndividuals( this, number );
    }

    public static class FreshIndividuals<G,I extends Individual<G,I>> implements Iterator<I> {

        private final Generator<G,I> generator;
        private final int size;
        private int count = 0;

        public FreshIndividuals(Generator<G, I> generator, int size) {
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
