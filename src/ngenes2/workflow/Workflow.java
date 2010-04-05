package ngenes2.workflow;

import ngenes2.individual.WritableIndividual;
import ngenes2.population.Population;

public interface Workflow<G,I extends WritableIndividual<G>> {
    public void evolve( Population<G,I> population );
}
