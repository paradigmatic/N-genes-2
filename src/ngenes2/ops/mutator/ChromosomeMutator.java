package ngenes2.ops.mutator;

import java.util.List;

/**
 * Mutates a chromosome (represented as a list of genes) to produce a new chromosover.
 * The input chromosome should not be changed. A new list must be created and return.
 * @author falcone
 * @param <G>
 */
public interface ChromosomeMutator<G> {

    /**
     * Mutates a chromosome take as argument. Implementation should not
     * attempt to directly change the chromosome, but must create a new one.
     * @param chromosome The chromosome to be mutated
     * @return The result of the mutation
     */
    public List<G> mutate( List<G> chromosome );
}
