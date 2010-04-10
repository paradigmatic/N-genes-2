package ngenes2.population;

import ngenes2.individual.Individual;

public interface Stats<I extends Individual<?,I>> {

    public I best();
    public I worst();


}
