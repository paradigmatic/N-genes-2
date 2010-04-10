package ngenes2.ops.selector;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import ngenes2.individual.Individual;
import ngenes2.population.Population;
import ngenes2.util.Properties;


public class KTournament<I extends Individual<?,I>> implements Selector<I> {

    private final int K;
    private final Random rng;

    public KTournament(Random rng, Properties props) {
        this.K = props.getInt("tournament_size");
        this.rng = rng;
    }

    private I getRandom( Population<?,I> pop ) {
       return pop.get( rng.nextInt( pop.size() ) );
    }

    public I select(Population<?,I> pop) {
        I winner = getRandom(pop);
        double bestFitness = winner.fitness();
        for( int i=1; i<K; i++ ) {
            I challenger = getRandom(pop);
            double fit = challenger.fitness();
            if( fit < bestFitness ) {
                winner = challenger;
                bestFitness = fit;
            }
        }
        return winner;
    }

    public Iterator<I> select(int number, Population<?, I> pop) {
        return new KTIterator<I>(pop,this,number);
    }

    public static class KTIterator<I extends Individual<?,I>>  implements Iterator<I>
    {
        private final Population<?,I> pop;
        private final KTournament<I> KTournament;
        private final int size;
        private int count = 0;

        private KTIterator(Population<?, I> pop, KTournament<I> KTournament, int size) {
            this.pop = pop;
            this.KTournament = KTournament;
            this.size = size;
        }

        public boolean hasNext() {
            return count < size;
        }

        public I next() {
            if( ! hasNext() ) {
                throw new NoSuchElementException();
            }
            count++;
            return KTournament.select(pop);
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }


    }

}
