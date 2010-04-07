package ngenes2.population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ngenes2.individual.Individual;

public class Stats<I extends Individual<?,I>> {

    final private List<I> list;
    private boolean sorted = false;

    public Stats( Population<?,I> pop ) {
        list = new ArrayList<I>(pop.size());
        for( I i: pop ){
            list.add(i);
        }
    }

    public I best() {
        sort();
        return list.get(0);
    }

    public I worst() {
        sort();
        return list.get(list.size()-1);
    }

    private void sort() {
        if(! sorted ) {
            Collections.sort(list, new Comparator<I>() {
                public int compare(I ind0, I ind1) {
                    if( ind0.fitness() == ind1.fitness() ) {
                        return 0;
                    }
                    if( ind0.fitness() < ind1.fitness() ) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

}
