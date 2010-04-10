package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;

public class Crossover<G, I extends Individual<G, I>> {

    private final ChromosomeCrossover<G> chromosomeCrossover;

    public Crossover(ChromosomeCrossover<G> chromosomeCrossover) {
        this.chromosomeCrossover = chromosomeCrossover;
    }

    public List<I> mate(I individual1, I individual2) {
        final List<List<G>> chromos =
                chromosomeCrossover.mate(individual1.chromosome(),
                individual2.chromosome());
        final List<I> offsprings = new ArrayList<I>( chromos.size() );
        for( List<G> chrome: chromos ) {
            offsprings.add( individual1.makeSibling(chrome) );
        }
        return offsprings;
    }

    public int childrenNumber() {
        return chromosomeCrossover.childrenNumber();
    }
}
