package ngenes2.individual;

import java.util.List;
import ngenes2.fitness.Fitness;

/**
 * Interface defines GA individuals. To reduce side effects and allow
 * easy parallelization of evolution, Individuals should be immutable.
 * @param <G> Gene type
 * @param <I> Individual type
 */
public interface Individual<G,I extends Individual<G,I>> {

    /**
     * Evaluate and return the individual fitness value. If the individual is immutable,
     * the fitness value is cached and successive calls to this method are inexpensive.
     * @return The fitness value.
     */
    public double fitness();

    /**
     * Get the fitness function used by the individual.
     * @return The fitness function
     */
    public Fitness<G> fitnessFunction();

    /**
     * Returns the individual size. Ususally it is a short cut to the chromosome size.
     * @return The individual size.
     */
    public int size();

    /**
     * Return the ith gene in the chromosome.
     * @param i gene index
     * @return gene value
     */
    public G get( int i );

    /**
     * Get the individual chromosome as list of genes. The list should be unmodifiable
     * to limit side effects.
     * @return The chromosome
     */
    public List<G> chromosome();


    /**
     * Build and return an individual of the same base class. This method allow
     * individuals to act as factories. Created individual share the same fitness
     * function instance.
     * @param newChromosome The chromosome of the individual to be created
     * @return A new indivdiual of the same base class
     */
    public I makeSibling( List<G> newChromosome );
    

}
