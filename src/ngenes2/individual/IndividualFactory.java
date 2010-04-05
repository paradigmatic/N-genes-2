package ngenes2.individual;

import java.util.List;
import ngenes2.fitness.Fitness;

public interface IndividualFactory<G,I extends Individual<G,I>> {
    I makeIndividual( Fitness<G> fitFunc, List<G> chromosome );
}
