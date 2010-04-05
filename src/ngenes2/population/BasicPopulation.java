
package ngenes2.population;

import java.util.ArrayList;
import java.util.List;
import ngenes2.individual.Individual;

public class BasicPopulation<G,I extends Individual<G,I>> implements Population<G,I> {

    private List<I> current;
    private List<I> next;

    public BasicPopulation( List<I> initialInidviduals ) {
        current = new ArrayList<I>( initialInidviduals );
        next = new ArrayList<I>( initialInidviduals.size() );
    }

    public int size() {
        return current.size();
    }

    public I get(int i) {
        return current.get(i);
    }

    public void addToNextGeneration(I newIndividual) {
        next.add( newIndividual );
    }

    public void nextGeneration() {
        List<I> tmp = next;
        next = current;
        current = tmp;
        next.clear();
    }



}
