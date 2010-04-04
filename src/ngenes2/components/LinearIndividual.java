package ngenes2.components;

import java.util.List;
import ngenes2.fitness.Fitness;


public class LinearIndividual<G> implements WritableIndividual<G> {

    private final List<G> genes;
    private final Fitness<G> fitFunc;

    public LinearIndividual( Fitness<G> fitness, List<G> genes ) {
        this.genes = genes;
        this.fitFunc = fitness;
    }

    public G set(int i, G value) {
        if( i > genes.size() ) {
            throw new IndexOutOfBoundsException();
        }
        return genes.set(i, value);
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

}
