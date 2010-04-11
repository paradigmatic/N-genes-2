package ngenes2.individual;

import java.util.Collections;
import java.util.List;
import ngenes2.fitness.Fitness;

/**
 * This class represents the classic basic individual used in most GA settings.
 * They are immutable and cache their fitness value.
 * @param <G> Gene type
 */
public class LinearIndividual<G> implements Individual<G,LinearIndividual<G>> {

    private final List<G> genes;
    private final Fitness<G> fitFunc;
    private boolean fitnessOK = false;
    private double fitnessValue = 0.0;

    /**
     * Sole constructor.
     * @param fitness A fitness function
     * @param genes A list of genes
     */
    public LinearIndividual( Fitness<G> fitness, List<G> genes ) {
        this.genes = Collections.unmodifiableList(genes);
        this.fitFunc = fitness;
    }

    public double fitness() {
        if( ! fitnessOK ) {
            fitnessValue = fitFunc.compute(genes);
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

  @Override
  public String toString() {
    return "Individual(fitness="+fitness()+", chromosom="+chromosome().toString()+")";
  }

  /**
   * An {@link IndividualFactory} which creates linear individuals.
   * @param <G> Gene tyoe
   */
    public static class Factory<G> implements IndividualFactory<G,LinearIndividual<G>> {
        public LinearIndividual<G> makeIndividual(Fitness<G> fitFunc, List<G> chromosome) {
            return new LinearIndividual<G>(fitFunc,chromosome);
        }
    }
}
