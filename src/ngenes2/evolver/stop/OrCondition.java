package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

/**
 * This condition represent the or logical operator between two conditions.
 * Normaly, it should not be created directly by the user but it is built
 * using {@link StopCondition} or() method.
 * @author falcone
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class OrCondition<G, I extends Individual<G, I>> implements StopCondition<G, I> {

    private final StopCondition<G, I> left;
    private final StopCondition<G, I> right;


    /**
     * Sole constructor
     * @param left left operand
     * @param right right operand
     */
    public OrCondition(StopCondition<G, I> left, StopCondition<G, I> right) {
        this.left = left;
        this.right = right;
    }

    public boolean shouldStop(int generation, Population<G, I> population) {
        return left.shouldStop(generation, population) || right.shouldStop(generation, population);
    }

    public StopCondition<G, I> or(StopCondition<G, I> that) {
        return new OrCondition<G,I>(this, that);
    }

    public StopCondition<G, I> and(StopCondition<G, I> that) {
        return new AndCondition<G,I>(this, that);
    }
}
