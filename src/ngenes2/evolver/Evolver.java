package ngenes2.evolver;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * Evolver make the population evolve during several generation. This interface
 * is really simple, because all relevant features (operators, stop conditions,
 * monitors, etc.) are supposed to be provided by other components.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface Evolver<G,I extends Individual<G,I>> {

    /**
     * Make a population evolve.
     * @param population A population
     */
    public void evolve( Population<G,I> population );
}
