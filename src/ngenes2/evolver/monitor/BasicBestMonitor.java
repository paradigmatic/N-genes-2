package ngenes2.evolver.monitor;

import ngenes2.individual.Individual;
import ngenes2.population.Population;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Logs the best individual fitness each generation.
 */
public class BasicBestMonitor<G,I extends Individual<G,I>> implements GenerationMonitor<G,I> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void newGeneration(int generationNumber, Population<G, I> pop) {
        logger.info("Best individual fitness: {}", pop.stats().best().fitness() );
    }
}
