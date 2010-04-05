package ngenes2.ops.selector;

import java.util.Random;
import ngenes2.individual.Individual;
import ngenes2.population.Population;


public class KTournament<G, I extends Individual<G>> implements Selector<G,I> {

    private final int K;
    private final Random rng;

    public KTournament(Random rng, int K) {
        this.K = K;
        this.rng = rng;
    }

    private I getRandom( Population<G, I> pop ) {
       return pop.get( rng.nextInt( pop.size() ) );
    }

    public I select(Population<G, I> pop) {
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

}
