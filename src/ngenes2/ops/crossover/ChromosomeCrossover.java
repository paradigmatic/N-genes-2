package ngenes2.ops.crossover;

import java.util.List;

public interface ChromosomeCrossover<G> {
    public List<List<G>> mate( List<G> chrome1, List<G> chrome2 );
}
