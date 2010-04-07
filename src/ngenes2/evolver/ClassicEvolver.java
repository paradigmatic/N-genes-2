package ngenes2.evolver;

import ngenes2.evolver.monitor.GenerationMonitor;
import java.util.List;
import ngenes2.evolver.stop.StopCondition;
import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;

import ngenes2.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassicEvolver<G,I extends Individual<G,I>> implements Evolver <G,I>{

    private final Logger logger = LoggerFactory.getLogger(ClassicEvolver.class);

    private final Selector<I> selector;
    private final Crossover<G,I> crossover;
    private final Mutator<G,I> mutator;
    private final GenerationMonitor<G,I> monitor;
    private final StopCondition<G,I> stopCondition;

    public ClassicEvolver(Selector<I> selector, Crossover<G, I> crossover,
            Mutator<G, I> mutator, GenerationMonitor<G,I> monitor,
            StopCondition<G,I> stopCondition)
    {
        this.selector = selector;
        this.crossover = crossover;
        this.mutator = mutator;
        this.monitor = monitor;
        this.stopCondition = stopCondition;
    }

    //TODO: write tests
    public void evolve(Population<G,I> population) {
        logger.info("Starting evolution");
        int t=0;
        while( ! stopCondition.shouldStop(t, population) ) {
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
            monitor.newGeneration(t, population);
            t++;
        }
        logger.info("Evolution finished");
    }


}
