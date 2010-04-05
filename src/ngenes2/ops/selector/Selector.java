package ngenes2.ops.selector;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface Selector<I extends Individual<?,I>> {
    public I select( Population<?,I> pop );
}
