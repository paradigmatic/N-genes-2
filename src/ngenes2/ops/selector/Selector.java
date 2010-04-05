package ngenes2.ops.selector;

import ngenes2.individual.WritableIndividual;
import ngenes2.population.Population;

public interface Selector<G, I extends WritableIndividual<G>> {
    public I select( Population<G,I> pop );
}
