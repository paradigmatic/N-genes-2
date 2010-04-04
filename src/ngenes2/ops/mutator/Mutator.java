package ngenes2.ops.mutator;

import ngenes2.components.WritableIndividual;

public interface Mutator<G,I extends WritableIndividual<G>> {
    public I mutate( I before );
}
