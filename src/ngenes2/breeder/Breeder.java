package ngenes2.breeder;

import java.util.List;
import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface Breeder<G, I extends Individual<G,I>> {
    public int parentNumber();
    public int childrenNumber();
    public void breed( Population<G,I> pop, List<I> parents );
}
