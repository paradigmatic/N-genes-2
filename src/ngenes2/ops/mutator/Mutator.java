package ngenes2.ops.mutator;

import ngenes2.components.WritableIndividual;

public interface Mutator<I extends WritableIndividual<G>,G> {
    public I mutate( I before );
}
