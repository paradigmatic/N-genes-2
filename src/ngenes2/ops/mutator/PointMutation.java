package ngenes2.ops.mutator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ngenes2.ops.mutator.genes.GeneMutator;


/**
 * This {@link ChromosomeMutator} mutates a single gene in a chromosome.
 * The gene is chosen at random using a continuous probability distribution.
 * The actual mutation of the gene is delegated to a {@link GeneMutator}.
 * @author falcone
 * @param <G> Gene type.
 */
public class PointMutation<G> implements ChromosomeMutator<G> {

    private final Random rng;
    private final GeneMutator<G> geneMutator;

    /**
     * Sole constructor.
     * @param rng A random number generator
     * @param geneMutator A gene mutator
     */
    public PointMutation(Random rng, GeneMutator<G> geneMutator ) {
        this.rng = rng;
        this.geneMutator = geneMutator;
    }

    public List<G> mutate(List<G> chromosome) {
        final int position = rng.nextInt( chromosome.size() );
        final List<G> newChromosome = new ArrayList<G>( chromosome );
        final G oldValue = chromosome.get(position);
        final G newValue = geneMutator.mutate(oldValue);
        newChromosome.set(position, newValue);
        return newChromosome;
    }

}
