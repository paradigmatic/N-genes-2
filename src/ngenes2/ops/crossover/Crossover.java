package ngenes2.ops.crossover;

import java.util.List;
import ngenes2.individual.WritableIndividual;

public interface Crossover<G, I extends WritableIndividual<G>> {
    public List<I> mate( I individual1, I individual2 );
}
