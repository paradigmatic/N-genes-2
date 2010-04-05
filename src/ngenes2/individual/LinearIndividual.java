package ngenes2.individual;

import java.util.List;
import ngenes2.fitness.Fitness;
import ngenes2.ops.mutator.ChromosomeMutator;


public class LinearIndividual<G> implements Individual<G>,
        ChromosomeMutable<G,LinearIndividual<G>> {

    private final List<G> genes;
    private final Fitness<G> fitFunc;

    public LinearIndividual( Fitness<G> fitness, List<G> genes ) {
        this.genes = genes;
        this.fitFunc = fitness;
    }

    public double fitness() {
        return fitFunc.compute(this);
    }

    public G get(int i) {
        if( i > genes.size() ) {
            throw new IndexOutOfBoundsException();
        }
        return genes.get(i);
    }

    public int size() {
        return genes.size();
    }

    public LinearIndividual<G> mutate(ChromosomeMutator<G> mutator) {
        List<G> newGenes = mutator.mutate(genes);
        return new LinearIndividual(fitFunc, newGenes);
    }

}
