package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;
import ngenes2.util.Properties;

public class MaxGeneration<G, I extends Individual<G, I>> implements StopCondition<G, I> {

    private final int maxGeneration;

    public MaxGeneration(Properties prop) {
        maxGeneration = prop.getInt("max_generation");
    }

    public boolean shouldStop(int generation, Population<G, I> population) {
        return generation >= maxGeneration;
    }

    public StopCondition<G, I> or(StopCondition<G, I> that) {
        return new OrCondition<G, I>(this, that);
    }

    public StopCondition<G, I> and(StopCondition<G, I> that) {
        return new AndCondition(this, that);
    }
}
