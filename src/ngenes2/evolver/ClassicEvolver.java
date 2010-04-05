package ngenes2.evolver;

import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassicEvolver<G,I extends Individual<G,I>> implements Evolver <G,I>{

    private final Logger logger = LoggerFactory.getLogger(ClassicEvolver.class);

    final int numberOfGeneration;
    final Selector<I> selector;
    final Crossover<G,I> crossover;
    final Mutator<G,I> mutator;

    public ClassicEvolver(int numberOfGeneration, Selector<I> selector, Crossover<G, I> crossover, Mutator<G, I> mutator) {
        this.numberOfGeneration = numberOfGeneration;
        this.selector = selector;
        this.crossover = crossover;
        this.mutator = mutator;
    }

    //TODO: write tests
    public void evolve(Population<G,I> population) {
        logger.info("Starting evolution");
        for( int t=0; t<numberOfGeneration; t++ ) {
            logger.debug("Generation: " + t);
            int count = 0;
            while( count < population.size() ) {
                final I i1 = selector.select(population);
                final I i2 = selector.select(population);
                final List<I> lst = crossover.mate(i1, i2);
                for( I child: lst ) {
                    population.addToNextGeneration( mutator.mutate(child) );
                }
                count += lst.size();
            }
            population.nextGeneration();

        }
        logger.info("Evolution finished");
    }


}
