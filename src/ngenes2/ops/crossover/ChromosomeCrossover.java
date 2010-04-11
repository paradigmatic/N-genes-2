package ngenes2.ops.crossover;

import java.util.List;

/**
 * Defines how to cross over two chromosomes. Instances of this interface
 * are used by {@link Crossover} to cross over individuals.
 * @param <G> Gene type
 */
public interface ChromosomeCrossover<G> {
    /**
     * Cross-over two chromosomes as list of genes. The resulting list of chromosomes should
     * have the same size than the value returned by the childrenNumber() method.
     * @param chrome1 Chromosome 1
     * @param chrome2 Chromosome 2
     * @return A list of resulting children.
     */
    public List<List<G>> mate( List<G> chrome1, List<G> chrome2 );

    /**
     * Get the number of children produced by the crossover.
     * @return Number of children
     */
    public int childrenNumber();
}
