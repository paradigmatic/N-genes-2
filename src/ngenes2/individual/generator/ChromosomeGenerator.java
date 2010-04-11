package ngenes2.individual.generator;

import java.util.List;

/**
 * Interface for object which generates chromosomes. They are used by the individual
 * {@link Generator} to create new individuals.
 * @param <G> Gene type
 */
public interface ChromosomeGenerator<G> {

    /**
     * Generates a new chromosome as a list of genes.
     * @return A new chromosome
     */
    public List<G> generate();
}
