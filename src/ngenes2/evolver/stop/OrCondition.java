package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;

public class OrCondition<G, I extends Individual<G, I>> implements StopCondition<G, I> {

    private final StopCondition<G, I> left;
    private final StopCondition<G, I> right;

    protected OrCondition(StopCondition<G, I> left, StopCondition<G, I> right) {
        this.left = left;
        this.right = right;
    }

    public boolean shouldStop(int generation, Population<G, I> population) {
        return left.shouldStop(generation, population) || right.shouldStop(generation, population);
    }

    public StopCondition<G, I> or(StopCondition<G, I> that) {
        return new OrCondition(this, that);
    }

    public StopCondition<G, I> and(StopCondition<G, I> that) {
        return new AndCondition(this, that);
    }
}
