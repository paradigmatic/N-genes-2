package ngenes2.ops.mutator.genes;

public interface GeneMutator<G> {
    public G mutate( G before );
}
