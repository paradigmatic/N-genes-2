package ngenes2.evolver;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface Evolver<G,I extends Individual<G,I>> {
    public void evolve( Population<G,I> population );
}
