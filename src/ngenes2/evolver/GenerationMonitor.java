package ngenes2.evolver;

import ngenes2.individual.Individual;
import ngenes2.population.Population;


public interface GenerationMonitor<G,I extends Individual<G,I>> {
    public void newGeneration( int generationNumber, Population<G,I> pop);
}
