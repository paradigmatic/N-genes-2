package ngenes2.evolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ngenes2.evolver.monitor.GenerationMonitor;
import ngenes2.evolver.stop.StopCondition;
import ngenes2.individual.Individual;
import ngenes2.breeder.Breeder;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassicEvolver<G,I extends Individual<G,I>> implements Evolver <G,I>{

    private final Logger logger = LoggerFactory.getLogger(ClassicEvolver.class);

    private final Selector<I> selector;
    private final Breeder<G,I> breeder;
    private final GenerationMonitor<G,I> monitor;
    private final StopCondition<G,I> stopCondition;

    public ClassicEvolver(Selector<I> selector, Breeder<G, I> breeder, GenerationMonitor<G, I> monitor, StopCondition<G, I> stopCondition) {
        this.selector = selector;
        this.breeder = breeder;
        this.monitor = monitor;
        this.stopCondition = stopCondition;
    }

    public void evolve(Population<G,I> population) {
        logger.info("Starting evolution");
        int t=0;
        while( ! stopCondition.shouldStop(t, population) ) {
            logger.debug("Generation: {}", t);
            int count = 0;
            final int toDraw = population.size()/breeder.childrenNumber() * breeder.parentNumber();
            Iterator<I> selected = selector.select(toDraw, population);
            while( count < population.size() ) {
                final List<I> parents = new ArrayList<I>(breeder.parentNumber());
                for( int i=0; i < breeder.parentNumber(); i++ ) {
                    parents.add( selected.next() );
                }
                breeder.breed(population, parents);
                count += breeder.childrenNumber();
            }
            population.nextGeneration();
            monitor.newGeneration(t, population);
            t++;
        }
        logger.info("Evolution finished");
    }


}
