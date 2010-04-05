package ngenes2.individual;

import java.util.Collections;
import java.util.List;
import ngenes2.fitness.Fitness;
import ngenes2.ops.mutator.ChromosomeMutator;


public class LinearIndividual<G> implements Individual<G,LinearIndividual<G>> {

    private final List<G> genes;
    private final Fitness<G> fitFunc;
    private boolean fitnessOK = false;
    private double fitnessValue = 0.0;

    public LinearIndividual( Fitness<G> fitness, List<G> genes ) {
        this.genes = Collections.unmodifiableList(genes);
        this.fitFunc = fitness;
    }

    public double fitness() {
        if( ! fitnessOK ) {
            fitnessValue = fitFunc.compute(this);
        }
        return fitnessValue;
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

    public List<G> chromosome() {
        return genes;
    }

    public LinearIndividual<G> makeSibling(List<G> newChromosome) {
        return new LinearIndividual<G>( fitFunc, newChromosome );
    }

    public Fitness<G> fitnessFunction() {
        return fitFunc;
    }

}
