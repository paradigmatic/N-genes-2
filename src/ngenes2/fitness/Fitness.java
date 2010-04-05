package ngenes2.fitness;

import ngenes2.individual.Individual;


public interface Fitness<G> {

    public double compute( Individual<G,?> individual );

}
