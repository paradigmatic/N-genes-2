package ngenes2.fitness;

import java.util.List;


public interface Fitness<G> {

    public double compute(List<G> chromosome );

}
