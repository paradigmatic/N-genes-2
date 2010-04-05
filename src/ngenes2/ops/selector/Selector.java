package ngenes2.ops.selector;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface Selector<G, I extends Individual<G>> {
    public I select( Population<G,I> pop );
}
