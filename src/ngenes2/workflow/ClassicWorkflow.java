package ngenes2.workflow;

import java.util.List;
import ngenes2.components.WritableIndividual;
import ngenes2.ops.crossover.Crossover;
import ngenes2.ops.mutator.Mutator;
import ngenes2.ops.selector.Selector;
import ngenes2.population.Population;

public class ClassicWorkflow<G,I extends WritableIndividual<G>> implements Workflow <G,I>{

    final int numberOfGeneration;
    final Selector<G,I> selector;
    final Crossover<G,I> crossover;
    final Mutator<G,I> mutator;

    public ClassicWorkflow(int numberOfGeneration, Selector<G,I> selector, Crossover<G, I> crossover, Mutator<G, I> mutator) {
        this.numberOfGeneration = numberOfGeneration;
        this.selector = selector;
        this.crossover = crossover;
        this.mutator = mutator;
    }

    //TODO: write tests
    public void evolve(Population<G,I> population) {
        for( int t=0; t<numberOfGeneration; t++ ) {
            int count = 0;
            while( count < population.size() ) {
                final I i1 = selector.select(population);
                final I i2 = selector.select(population);
                final List<I> lst = crossover.mate(i1, i2);
                for( I child: lst ) {
                    population.addToNextGeneration( mutator.mutate(child) );
                }
                count += lst.size();
            }
        }
    }


}
