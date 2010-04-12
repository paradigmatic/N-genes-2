package ngenes2.ops.selector;

import java.util.Iterator;
import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * Defines a selection operator, able to select one or many individuals
 * from a population. This selection can be deterministic or stochastic.
 * @param <I> Individual type
 */
public interface Selector<I extends Individual<?,I>> {

    /**
     * Select one individual from the population.
     * @param pop Population
     * @return Selected individual
     */
    public I select( Population<?,I> pop );

    /**
     * Select several individual from a population. The selection is backed by
     * an iterator, to allow the selection to operate lazily.
     * @param number The number of operators to select.
     * @param pop Population
     * @return Selected individuals
     */
    public Iterator<I> select( int number, Population<?,I> pop );
}
