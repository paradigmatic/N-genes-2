package ngenes2.individual;

import java.util.List;
import ngenes2.fitness.Fitness;


public interface Individual<G,I extends Individual<G,I>> {

    public double fitness();

    public Fitness<G> fitnessFunction();

    public int size();

    public G get( int i );

    public List<G> chromosome();

    public I makeSibling( List<G> newChromosome );

}
