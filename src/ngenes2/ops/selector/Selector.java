package ngenes2.ops.selector;

import java.util.Iterator;
import ngenes2.individual.Individual;
import ngenes2.population.Population;

public interface Selector<I extends Individual<?,I>> {
    public I select( Population<?,I> pop );
    public Iterator<I> select( int number, Population<?,I> pop );
}
