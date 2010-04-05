package ngenes2.individual;

import ngenes2.ops.mutator.ChromosomeMutator;


public interface WritableIndividual<G> extends Individual<G> {

    public G set( int i, G value );
    public WritableIndividual<G> mutate( ChromosomeMutator<G> mutator );

}
