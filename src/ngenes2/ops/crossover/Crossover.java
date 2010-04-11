package ngenes2.ops.crossover;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;

/**
 * Cross over two parents individuals two produce children. The actual recombination
 * of chromosome is delegated to a {@link ChromosomeCrossover}.
 * @author falcone
 * @param <G> Gene type
 * @param <I> Individual type
 */
public class Crossover<G, I extends Individual<G, I>> {

    private final ChromosomeCrossover<G> chromosomeCrossover;

    /**
     * Sole constructor. Takes a ChromosomeCrossover instance which will perform
     * the actual chromosome recombination.
     * @param chromosomeCrossover A chromosome crossover instance
     */
    public Crossover(ChromosomeCrossover<G> chromosomeCrossover) {
        this.chromosomeCrossover = chromosomeCrossover;
    }

    /**
     * Mates two individual using the ChromosomeCrossover passed to the constructor.
     * The number of children is equal to the value returned by the childrenNumber()
     * method.
     * @param individual1 First parent
     * @param individual2 Second parent
     * @return A list of children.
     */
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

    /**
     * 
     * @return
     */
    public int childrenNumber() {
        return chromosomeCrossover.childrenNumber();
    }
}
