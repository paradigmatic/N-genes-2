package ngenes2.individual;

import ngenes2.ops.mutator.ChromosomeMutator;


public interface ChromosomeMutable<G, I extends ChromosomeMutable<G,I>> {
    public I mutate(ChromosomeMutator<G> mutator);
}
