package ngenes2.evolver.stop;

import ngenes2.individual.Individual;
import ngenes2.population.Population;
import ngenes2.population.Stats;
import ngenes2.util.Properties;

public class FitnessTarget<G,I extends Individual<G,I>> implements StopCondition<G,I> {

    private final double target;

    public FitnessTarget( Properties props) {
        target = props.getDouble("fitness_target");
    }

    public boolean shouldStop(int generation, Population<G, I> population) {
        Stats<I> stats = new Stats<I>(population);
        return stats.best().fitness() <= target;
    }

    public StopCondition<G, I> or(StopCondition<G, I> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public StopCondition<G, I> and(StopCondition<G, I> that) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
