package ngenes2.population;

import ngenes2.individual.Individual;
import ngenes2.individual.generator.Generator;

public interface PopulationFactory<G, I extends Individual<G,I>,P extends Population<G,I>> {

    public P create( Generator<G,I> gen, int popSize );

}
