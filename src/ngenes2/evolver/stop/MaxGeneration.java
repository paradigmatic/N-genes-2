package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;
import ngenes2.util.Properties;

/**
 * This stop condition allow to define a maximum number of generation. The
 * maximum number of generation is defined by the integer property <b>max_generation</b>.
 * @author falcone
 * @param <G>
 * @param <I>
 */
public class MaxGeneration<G, I extends Individual<G, I>> implements StopCondition<G, I> {

    private final int maxGeneration;

    /**
     * Sole constructor.
     * @param prop A properties object containing the integer property <b>max_generation</b>.
     */
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
        return new AndCondition<G,I>(this, that);
    }
}
