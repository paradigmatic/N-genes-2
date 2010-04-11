package ngenes2.individual;

import java.util.List;
import ngenes2.fitness.Fitness;


/**
 * Factory that can create a new individual using a chromosome and a fitness
 * function. Individual factories should be implemented as nested static class
 * in Individual implementations.
 * @param <G> Gene type
 * @param <I> individual type
 */
public interface IndividualFactory<G,I extends Individual<G,I>> {

    /**
     * Create a new individual.
     * @param fitFunc A fitness function
     * @param chromosome A chromosome as a list of genes.
     * @return A new individual.
     */
    I makeIndividual( Fitness<G> fitFunc, List<G> chromosome );
}
