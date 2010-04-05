package ngenes2.ops.mutator;

import ngenes2.individual.ChromosomeMutable;


public class Mutator<G,I extends ChromosomeMutable<G,I>> {

    private final ChromosomeMutator<G> chromosomeMutator;

    public Mutator(ChromosomeMutator<G> chromosomeMutator) {
        this.chromosomeMutator = chromosomeMutator;
    }

    public I mutate( I before ) {
        return before.mutate(chromosomeMutator);
    }
}
