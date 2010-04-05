package ngenes2.ops.mutator;

import java.util.List;

public interface ChromosomeMutator<G> {
    public List<G> mutate( List<G> chromosome );
}
