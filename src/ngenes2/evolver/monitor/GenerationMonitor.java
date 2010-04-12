package ngenes2.evolver.monitor;

import ngenes2.individual.Individual;
import ngenes2.population.Population;


/**
 * The generation monitor is ususally called by an {@link ngenes2.evolver.Evolver} at the end of
 * each iteration. It is usually used for logging, check pointing or performing
 * additional tasks on the population.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface GenerationMonitor<G,I extends Individual<G,I>> {
  /**
   * Perform an action which is triggered after each generation.
   * @param generationNumber The generation number.
   * @param pop The population to monitor.
   */
    public void newGeneration( int generationNumber, Population<G,I> pop);
}
