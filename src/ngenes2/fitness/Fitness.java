package ngenes2.fitness;

import ngenes2.components.Individual;


public interface Fitness<G> {

    public double compute( Individual<G> individual );

}
