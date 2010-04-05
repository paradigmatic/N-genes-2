package ngenes2.workflow;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface Workflow<G,I extends Individual<G>> {
    public void evolve( Population<G,I> population );
}
