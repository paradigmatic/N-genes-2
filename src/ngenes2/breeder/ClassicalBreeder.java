package ngenes2.breeder;
import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.population.Population;

/**
 * Takes two parents, crossover them and mutates the children.
 * @author falcone
 */
public class ClassicalBreeder<G, I extends Individual<G, I>> implements Breeder<G, I> {

    private final static int PARENT_NUMBER = 2;
    private final int childrenNumber;;
    private final Crossover<G, I> crossover;
    private final Mutator<G, I> mutator;

    public ClassicalBreeder(Crossover<G, I> crossover, Mutator<G, I> mutator) {
        this.crossover = crossover;
        this.mutator = mutator;
        this.childrenNumber = crossover.childrenNumber();
    }

    public int parentNumber() {
        return PARENT_NUMBER;
    }

    public int childrenNumber() {
        return childrenNumber;
    }

    public void breed(Population<G, I> pop, List<I> parents) {
        if (parents.size() != PARENT_NUMBER) {
            throw new IllegalArgumentException("Received " + parents.size() + " instead of " + PARENT_NUMBER + ".");
        }
        final List<I> beforeMutation = crossover.mate(parents.get(0), parents.get(1));// </editor-fold>
        for (I child : beforeMutation) {
            pop.addToNextGeneration(mutator.mutate(child));
        }
    }
}
