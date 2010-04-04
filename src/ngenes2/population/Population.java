package ngenes2.population;

import ngenes2.components.WritableIndividual;

public interface Population<G,I extends WritableIndividual<G>> {
    public int size();
    public I get(int i);
    public void addToNextGeneration( I newIndividual );
    public void nextGeneration();
}
