package ngenes2.fitness;

import java.util.List;

/**
 * Individual fitness. By convention the smallest fitness is the best.
 * @param <G> Gene type.
 */
public interface Fitness<G> {

    /**
     * Computes the fitness corresponding to a chromosome.
     * @param chromosome The chromosome to evaluate
     * @return The fitness
     */
    public double compute(List<G> chromosome );

}
