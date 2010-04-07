package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface StopCondition<G,I extends Individual<G,I>> {

    public boolean shouldStop( int generation, Population<G,I> population );
    public StopCondition<G,I> or( StopCondition<G,I> that );
    public StopCondition<G,I> and( StopCondition<G,I> that );

}
