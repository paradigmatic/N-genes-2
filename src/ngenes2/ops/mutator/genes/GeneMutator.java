package ngenes2.ops.mutator.genes;


/**
 * Define a single gene mutation process. The gene is not modified but a new
 * one is created.
 * @param <G> Gene type
 */
public interface GeneMutator<G> {

    /**
     * Mutates a gene.
     * @param before The gene before mutation (not modified by the process)
     * @return A new gene
     */
    public G mutate( G before );
}
