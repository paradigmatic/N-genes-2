package ngenes2.ops.mutator;

import java.util.List;
import ngenes2.individual.Individual;


public class Mutator<G,I extends Individual<G,I>> {

    private final ChromosomeMutator<G> chromosomeMutator;

    public Mutator(ChromosomeMutator<G> chromosomeMutator) {
        this.chromosomeMutator = chromosomeMutator;
    }

    public I mutate( I before ) {
        List<G> newChromosome = chromosomeMutator.mutate( before.chromosome() );
        return before.makeSibling(newChromosome);
    }
}
