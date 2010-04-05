package ngenes2.population;

import ngenes2.individual.Individual;

public interface Population<G,I extends Individual<G>> {
    public int size();
    public I get(int i);
    public void addToNextGeneration( I newIndividual );
    public void nextGeneration();
}
