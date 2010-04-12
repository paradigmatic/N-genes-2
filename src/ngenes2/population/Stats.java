package ngenes2.population;

import ngenes2.individual.Individual;

/**
 * Statistics over a population
 * @param <I> Individual type
 */
public interface Stats<I extends Individual<?,I>> {

    /**
     * Get the individual with the best fitness in a population.
     * @return Best individual
     */
    public I best();

    /**
     * Get the individual with the worst fitness in a population
     * @return Worst individual
     */
    public I worst();


}
