package ngenes2.ops.mutator;

import java.util.List;
import ngenes2.individual.Individual;

/**
 * Mutates an individual using a {@link ChromosomeMutator}. The individual is not
 * modified but a new one is created instead.
 * @param <G>
 * @param <I>
 */
public class Mutator<G,I extends Individual<G,I>> {

    private final ChromosomeMutator<G> chromosomeMutator;

    /**
     * Sole constructor.
     * @param chromosomeMutator A ChromosomeMutator
     */
    public Mutator(ChromosomeMutator<G> chromosomeMutator) {
        this.chromosomeMutator = chromosomeMutator;
    }

    /**
     * Mutates an individual.
     * @param before The individual to mutate (not modified)
     * @return A new individual
     */
    public I mutate( I before ) {
        List<G> newChromosome = chromosomeMutator.mutate( before.chromosome() );
        return before.makeSibling(newChromosome);
    }
}
